package com.kj.commdityinfo.utils;

import lombok.AllArgsConstructor;

/**
 * 自定义相应返回类型
 * @author kj
 *
 * */
//@AllArgsConstructor
public class Message<T> {
    /**
     * 状态码
     * */
    private Integer status;
    /**
     * 返回信息
     * */
    private String message;
    /**
     * 当前页
     */
    private Integer page;
    /**
     * 当前条目数
     */
    private  Integer pageSize;
    /**
     * 数据总量
     */
    private Long total;
    /**
     * 返回的数据类
     * */
    private T data;
    /**
     * 返回的时间
     * */
    private Long time;

    public Message(Integer status, String message, T data, Long time) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.time = time;
    }

    public Message(){

    }

    public Message(Integer status, String message, Integer page, Integer pageSize, Long total, T data, Long time) {
        this.status = status;
        this.message = message;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}

