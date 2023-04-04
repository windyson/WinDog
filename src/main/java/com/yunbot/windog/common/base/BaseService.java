package com.yunbot.windog.common.base;

import java.util.List;
import org.apache.ibatis.annotations.Param;
/**
 * @author William
 */

public interface BaseService<T,T2> {
	
    int deleteByPrimaryKey(String id);

    int insertSelective(T record);

    T selectByPrimaryKey(String id);
   
    int updateByPrimaryKeySelective(T record);
    
    int updateByExampleSelective(@Param("record") T record, @Param("example") T2 example);

    int updateByExample(@Param("record") T record, @Param("example") T2 example);
    
    List<T> selectByExample(T2 example);

    long countByExample(T2 example);

    int deleteByExample(T2 example);

}
