package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.Botransaction;
import com.yunbot.windog.model.auto.BotransactionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  BotransactionMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:22:27
 */
public interface BotransactionMapper {
      	   	      	      	      	      	      	      	      	      	      	      
    long countByExample(BotransactionExample example);

    int deleteByExample(BotransactionExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(Botransaction record);

    int insertSelective(Botransaction record);

    List<Botransaction> selectByExample(BotransactionExample example);
		
    Botransaction selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") Botransaction record, @Param("example") BotransactionExample example);

    int updateByExample(@Param("record") Botransaction record, @Param("example") BotransactionExample example); 
		
    int updateByPrimaryKeySelective(Botransaction record);

    int updateByPrimaryKey(Botransaction record);
  	  	
}