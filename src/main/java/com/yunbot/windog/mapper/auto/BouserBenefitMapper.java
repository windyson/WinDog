package com.yunbot.windog.mapper.auto;

import com.yunbot.windog.model.auto.BouserBenefit;
import com.yunbot.windog.model.auto.BouserBenefitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 *  BouserBenefitMapper
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-19 21:54:37
 */
public interface BouserBenefitMapper {
      	   	      	      	      	      	      	      
    long countByExample(BouserBenefitExample example);

    int deleteByExample(BouserBenefitExample example);
		
    int deleteByPrimaryKey(Integer id);
		
    int insert(BouserBenefit record);

    int insertSelective(BouserBenefit record);

    List<BouserBenefit> selectByExample(BouserBenefitExample example);
		
    BouserBenefit selectByPrimaryKey(Integer id);
		
    int updateByExampleSelective(@Param("record") BouserBenefit record, @Param("example") BouserBenefitExample example);

    int updateByExample(@Param("record") BouserBenefit record, @Param("example") BouserBenefitExample example); 
		
    int updateByPrimaryKeySelective(BouserBenefit record);

    int updateByPrimaryKey(BouserBenefit record);
  	  	
}