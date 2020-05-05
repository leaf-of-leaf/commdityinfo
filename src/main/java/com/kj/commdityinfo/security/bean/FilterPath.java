package com.kj.commdityinfo.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kj
 * @Date 2020/4/29 16:54
 * @Version 1.0
 * 文件路径的封装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FilterPath {
    private String[] strings;
}
