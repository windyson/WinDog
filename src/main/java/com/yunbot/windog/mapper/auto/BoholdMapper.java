package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.Bohold;
import com.yunbot.windog.model.auto.BoholdExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  BoholdMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:21:07
 */
public interface BoholdMapper {
      	   	      	      	      	      	      	      	      	      
    long countByExample(BoholdExample example);

    int deleteByExample(BoholdExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(Bohold record);

    int insertSelective(Bohold record);

    List<Bohold> selectByExample(BoholdExample example);
		
    Bohold selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") Bohold record, @Param("example") BoholdExample example);

    int updateByExample(@Param("record") Bohold record, @Param("example") BoholdExample example); 
		
    int updateByPrimaryKeySelective(Bohold record);

    int updateByPrimaryKey(Bohold record);
  	  	
}