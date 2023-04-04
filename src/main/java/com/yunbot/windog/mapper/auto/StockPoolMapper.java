package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.StockPool;
import com.yunbot.windog.model.auto.StockPoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  StockPoolMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:45
 */
public interface StockPoolMapper {
      	   	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      
    long countByExample(StockPoolExample example);

    int deleteByExample(StockPoolExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(StockPool record);

    int insertSelective(StockPool record);

    List<StockPool> selectByExample(StockPoolExample example);
		
    StockPool selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") StockPool record, @Param("example") StockPoolExample example);

    int updateByExample(@Param("record") StockPool record, @Param("example") StockPoolExample example); 
		
    int updateByPrimaryKeySelective(StockPool record);

    int updateByPrimaryKey(StockPool record);
  	  	
}