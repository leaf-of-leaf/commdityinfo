package com.kj.commdityinfo.mapper;

import com.kj.commdityinfo.bean.NavItem;
import com.kj.commdityinfo.bean.NavItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NavItemMapper {
    long countByExample(NavItemExample example);

    int deleteByExample(NavItemExample example);

    int deleteByPrimaryKey(Integer navId);

    int insert(NavItem record);

    int insertSelective(NavItem record);

    List<NavItem> selectByExample(NavItemExample example);

    NavItem selectByPrimaryKey(Integer navId);

    int updateByExampleSelective(@Param("record") NavItem record, @Param("example") NavItemExample example);

    int updateByExample(@Param("record") NavItem record, @Param("example") NavItemExample example);

    int updateByPrimaryKeySelective(NavItem record);

    int updateByPrimaryKey(NavItem record);
}