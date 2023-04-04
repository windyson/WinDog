package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.BoholdView;
import com.yunbot.windog.model.auto.BoholdViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * VIEW BoholdViewMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-20 23:21:22
 */
public interface BoholdViewMapper {
      	      	      	      	      	      	      	      	      	      
    long countByExample(BoholdViewExample example);

    int deleteByExample(BoholdViewExample example);
		
    int insert(BoholdView record);

    int insertSelective(BoholdView record);

    List<BoholdView> selectByExample(BoholdViewExample example);
		
    int updateByExampleSelective(@Param("record") BoholdView record, @Param("example") BoholdViewExample example);

    int updateByExample(@Param("record") BoholdView record, @Param("example") BoholdViewExample example); 
	  	
}