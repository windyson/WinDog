package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.StockLib;
import com.yunbot.windog.model.auto.StockLibExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  StockLibMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:03:08
 */
public interface StockLibMapper {
      	   	      	      	      	      	      
    long countByExample(StockLibExample example);

    int deleteByExample(StockLibExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(StockLib record);

    int insertSelective(StockLib record);

    List<StockLib> selectByExample(StockLibExample example);
		
    StockLib selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") StockLib record, @Param("example") StockLibExample example);

    int updateByExample(@Param("record") StockLib record, @Param("example") StockLibExample example); 
		
    int updateByPrimaryKeySelective(StockLib record);

    int updateByPrimaryKey(StockLib record);
  	  	
}