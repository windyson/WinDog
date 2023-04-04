package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.StockDaily;
import com.yunbot.windog.model.auto.StockDailyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  StockDailyMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2023-01-28 13:51:36
 */
public interface StockDailyMapper {
      	   	      	   	      	      	      	      	      	      	      	      	      	      
    long countByExample(StockDailyExample example);

    int deleteByExample(StockDailyExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(StockDaily record);

    int insertSelective(StockDaily record);

    List<StockDaily> selectByExample(StockDailyExample example);
		
    StockDaily selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") StockDaily record, @Param("example") StockDailyExample example);

    int updateByExample(@Param("record") StockDaily record, @Param("example") StockDailyExample example); 
		
    int updateByPrimaryKeySelective(StockDaily record);

    int updateByPrimaryKey(StockDaily record);
  	  	
}