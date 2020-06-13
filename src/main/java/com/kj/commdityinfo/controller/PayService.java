package com.kj.commdityinfo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.kj.commdityinfo.bean.UserOrder;
import com.kj.commdityinfo.exception.SystemException;
import com.kj.commdityinfo.service.UserOrderService;
import com.kj.commdityinfo.utils.AlipayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author kj
 * @Date 2020/6/10 16:46
 * @Version 1.0
 */
@Api(tags = "支付管理")
@RestController
public class PayService {

    @Autowired
    private UserOrderService userOrderService;

    @ApiOperation(value="手机端为指定订单执行付款操作:此接口不能通过swagger看到访问支付页面的效果，需要根据此处的参数信息在地址栏中发送请求", notes="已付款订单会提示已付款")
    @GetMapping("/phonepay")
    public void phonePay(HttpServletResponse response,@RequestParam("orderNum") String orderNum)
            throws AlipayApiException,Exception
    {
        UserOrder userOrderByOrderNum = null;
        try{
            userOrderByOrderNum = userOrderService.findUserOrderByOrderNum(orderNum);
        } catch (Exception e){
            throw new SystemException("订单查询失败");
        }
        if(userOrderByOrderNum == null){
            throw new SystemException("不存在此订单");
        }

        //设置该订单为支付中
        userOrderService.updateUserOrderStatusByOrderNum(UserOrder.PAYING,orderNum);

        response.setContentType("text/html;charset=utf-8");
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(orderNum.getBytes("UTF-8"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(userOrderByOrderNum.getPaidAccount().toString().getBytes("UTF-8"),"UTF-8");
        //订单名称，必填(不可重复)
        String subject = new String(userOrderByOrderNum.getOrderName().getBytes("UTF-8"),"UTF-8");
//        String subject = userOrderByOrderNum.getOrderName();
        //商品描述，可空
        String body = new String(userOrderByOrderNum.getOrderDescription().getBytes("UTF-8"),"UTF-8");
//        String body = userOrderByOrderNum.getOrderDescription();
        System.out.println("out_trade_no:" + out_trade_no);
        System.out.println(total_amount);
        System.out.println(subject);
        System.out.println(body);

        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        String result = alipayClient.pageExecute(request).getBody();
        try {
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @ApiOperation(value = "此接口为商户回调接口，不能直接调用")
    @PostMapping("/notify")
    public void notifyUrl(HttpServletResponse response, HttpServletRequest request) throws Exception {
        System.out.println("notify");
        PrintWriter out = response.getWriter();
        Map<String,String> params = new HashMap<String,String>();

        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("UTF-8"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.publicKey, AlipayConfig.charset, AlipayConfig.signType); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
	
        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("UTF-8"),"UTF-8");
            System.out.println("订单号:" + out_trade_no);
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("UTF-8"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("UTF-8"),"UTF-8");
            System.out.println("交易状态：" + trade_status);
            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                userOrderService.updateUserOrderStatusByOrderNum(UserOrder.UNPAY,out_trade_no);
                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序
                userOrderService.updateUserOrderStatusByOrderNum(UserOrder.SUCCESS,out_trade_no);
                out.write("订单" + "支付成功");
                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }
        }else {//验证失败
            out.write("fail");
            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }
        out.flush();
        out.close();
    }

    /**
     * 接口的回调函数，即用户成功付款以后的跳转界面
     * @param response
     * @param request
     * @throws IOException
     */
    @ApiOperation(value = "此接口为客户回调接口，不能直接调用")
    @GetMapping("/return")
    public void returnUrl(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //获取支付宝GET过来反馈信息
        System.out.println("return");
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        System.out.println(requestParams);
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("UTF-8"), "utf-8");
            params.put(name, valueStr);
        }
        System.out.println(params);
        PrintWriter writer = response.getWriter();
        writer.write("success");
        writer.flush();
        writer.close();
    }

}
