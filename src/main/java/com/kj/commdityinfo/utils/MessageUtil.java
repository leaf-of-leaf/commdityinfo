package com.kj.commdityinfo.utils;

/**
 * 返回消息的工具类
 * @author kj
 * */
public class MessageUtil {
    /**
     * 成功，并且返回数据
     * */
    public static <E>Message<E> success(E obj){
        return new Message<>(200, "success", obj, System.currentTimeMillis());
    }
    /**
     * 分页成功，返回总数，当前页，每页条数
     */
    public static <E>Message<E> successPage(Integer page, Long total, Integer pageSize, E obj){
        return new Message<>(200,"success",page,pageSize,total,obj, System.currentTimeMillis());
    }
    /**
     * 成功,但无返回数据
     * */
    public static <E>Message<E> success(){
        return new Message<>(200, "success", null, System.currentTimeMillis());
    }

    /**
     * 失败,将异常信息返回
     * */
    public static <E>Message<E> error(Integer code, String msg){
        return new Message<E>(code, msg, null, System.currentTimeMillis());
    }
}
