package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.Boinfo;
import com.yunbot.windog.model.auto.BoinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  BoinfoMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:02:09
 */
public interface BoinfoMapper {
      	   	      	      	      	      	      	      	      	      	      	      	      	      
    long countByExample(BoinfoExample example);

    int deleteByExample(BoinfoExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(Boinfo record);

    int insertSelective(Boinfo record);

    List<Boinfo> selectByExample(BoinfoExample example);
		
    Boinfo selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") Boinfo record, @Param("example") BoinfoExample example);

    int updateByExample(@Param("record") Boinfo record, @Param("example") BoinfoExample example); 
		
    int updateByPrimaryKeySelective(Boinfo record);

    int updateByPrimaryKey(Boinfo record);
  	  	
}