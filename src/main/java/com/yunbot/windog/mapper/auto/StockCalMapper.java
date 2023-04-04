package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.StockCal;
import com.yunbot.windog.model.auto.StockCalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  StockCalMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:36
 */
public interface StockCalMapper {
      	   	      	      
    long countByExample(StockCalExample example);

    int deleteByExample(StockCalExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(StockCal record);

    int insertSelective(StockCal record);

    List<StockCal> selectByExample(StockCalExample example);
		
    StockCal selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") StockCal record, @Param("example") StockCalExample example);

    int updateByExample(@Param("record") StockCal record, @Param("example") StockCalExample example); 
		
    int updateByPrimaryKeySelective(StockCal record);

    int updateByPrimaryKey(StockCal record);
  	  	
}