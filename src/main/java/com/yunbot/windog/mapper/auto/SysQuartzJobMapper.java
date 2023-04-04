package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.SysQuartzJob;
import com.yunbot.windog.model.auto.SysQuartzJobExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 定时任务调度表 SysQuartzJobMapper
 * @author fuce_自动生成
 * @email 115889198@qq.com
 * @date 2019-09-13 00:03:35
 */
public interface SysQuartzJobMapper {
      	      	   	      	   	      	   	      	   	      	   	      	   	      	   	      
    long countByExample(SysQuartzJobExample example);

    int deleteByExample(SysQuartzJobExample example);
		
    int deleteByPrimaryKey(String id);
		
    int insert(SysQuartzJob record);

    int insertSelective(SysQuartzJob record);

    List<SysQuartzJob> selectByExample(SysQuartzJobExample example);
		
    SysQuartzJob selectByPrimaryKey(String id);
		
    int updateByExampleSelective(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example);

    int updateByExample(@Param("record") SysQuartzJob record, @Param("example") SysQuartzJobExample example); 
		
    int updateByPrimaryKeySelective(SysQuartzJob record);

    int updateByPrimaryKey(SysQuartzJob record);
  	  	
}