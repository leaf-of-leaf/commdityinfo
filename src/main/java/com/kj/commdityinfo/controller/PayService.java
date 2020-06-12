package com.kj.commdityinfo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.kj.commdityinfo.utils.AlipayConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kj
 * @Date 2020/6/10 16:46
 * @Version 1.0
 */
@RestController
public class PayService {

    @ApiOperation(value="手机端为指定订单执行付款操作:此接口不能通过swagger看到访问支付页面的效果，需要根据此处的参数信息在地址栏中发送请求", notes="已付款订单会提示已付款")
    @GetMapping({"/phonepay"})
    public void phonePay(HttpServletResponse response)
            throws AlipayApiException,Exception
    {
        response.setContentType("text/html;charset=utf-8");
        AlipayClient alipayClient = AlipayConfig.getAlipayClient();
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();

        request.setReturnUrl(AlipayConfig.return_url);
        request.setNotifyUrl(AlipayConfig.notify_url);

//        AlipayTradePayModel model = new AlipayTradePayModel();
//
//        model.setOutTradeNo("123");
//
//        model.setTotalAmount("0.01");
//
//        model.setSubject("测试订单");
//
//        model.setBody(System.currentTimeMillis() + "");
//
//        model.setProductCode("FAST_INSTANT_TRADE_PAY");
//
//        request.setBizModel(model);

        //商户订单号，商户网站订单系统中唯一订单号，必填

        String out_trade_no = new String(new Long(System.currentTimeMillis()).toString().getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String("100".getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String("商品".getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String("商品描述".getBytes("ISO-8859-1"),"UTF-8");

        request.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

//        String result = ((AlipayTradeWapPayResponse)alipayClient.pageExecute
//                (request))
//                .getBody();


        String result = alipayClient.pageExecute(request).getBody();
        try {
            response.getWriter().println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
