package com.kj.commdityinfo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author kj
 * @Date 2020/5/6 15:37
 * @Version 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {

    /**
     * 访问的url
     */
    private String url;

    /**
     * 来源ip
     */
    private String ip;
    /**
     * 访问端口
     */
    private Integer port;
    /**
     * 异常信息
     */
    private String exception;
    /**
     * 对应token
     */
    private String token;
    /**
     * 完成访问消耗的时间
     */
    private Long spendTime;
    /**
     * 访问时的参数
     */
    private String params;
}
