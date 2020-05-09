package com.kj.commdityinfo.mapper;

import com.kj.commdityinfo.bean.CateItem;
import com.kj.commdityinfo.bean.CateItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CateItemMapper {
    long countByExample(CateItemExample example);

    int deleteByExample(CateItemExample example);

    int deleteByPrimaryKey(Integer cateId);

    int insert(CateItem record);

    int insertSelective(CateItem record);

    List<CateItem> selectByExample(CateItemExample example);

    CateItem selectByPrimaryKey(Integer cateId);

    int updateByExampleSelective(@Param("record") CateItem record, @Param("example") CateItemExample example);

    int updateByExample(@Param("record") CateItem record, @Param("example") CateItemExample example);

    int updateByPrimaryKeySelective(CateItem record);

    int updateByPrimaryKey(CateItem record);
}