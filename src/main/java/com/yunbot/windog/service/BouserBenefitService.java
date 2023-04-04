package com.yunbot.windog.service;

import java.util.List;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.StrUtil;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.BouserBenefitMapper;
import com.yunbot.windog.model.auto.BouserBenefit;
import com.yunbot.windog.model.auto.BouserBenefitExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  BouserBenefitService
 * @Title: BouserBenefitService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-19 21:54:37  
 **/
@Service
public class BouserBenefitService implements BaseService<BouserBenefit, BouserBenefitExample>{
	@Autowired
	private BouserBenefitMapper bouserBenefitMapper;
	
      	   	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<BouserBenefit> list(Tablepar tablepar,BouserBenefit record){
	        BouserBenefitExample testExample=new BouserBenefitExample();
			//搜索
			if(StrUtil.isNotEmpty(tablepar.getSearchText())) {//小窗体
	        	testExample.createCriteria().andLikeQuery2(tablepar.getSearchText());
	        }else {//大搜索
	        	testExample.createCriteria().andLikeQuery(record);
	        }
			//表格排序
			if(StrUtil.isNotEmpty(tablepar.getOrderByColumn())) {
	        	testExample.setOrderByClause(StringUtils.toUnderScoreCase(tablepar.getOrderByColumn()) +" "+tablepar.getIsAsc());
	        }else{
	        	testExample.setOrderByClause("curdt desc, benefit desc");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<BouserBenefit> list= bouserBenefitMapper.selectByExample(testExample);
	        PageInfo<BouserBenefit> pageInfo = new PageInfo<BouserBenefit>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			BouserBenefitExample example=new BouserBenefitExample();
			example.createCriteria().andSidIn(stringB);
			return bouserBenefitMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public BouserBenefit selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return bouserBenefitMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(BouserBenefit record) {
		return bouserBenefitMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(BouserBenefit record) {
				
		record.setSid(null);
		
				
		return bouserBenefitMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(BouserBenefit record, BouserBenefitExample example) {
		
		return bouserBenefitMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(BouserBenefit record, BouserBenefitExample example) {
		
		return bouserBenefitMapper.updateByExample(record, example);
	}

	@Override
	public List<BouserBenefit> selectByExample(BouserBenefitExample example) {
		
		return bouserBenefitMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(BouserBenefitExample example) {
		
		return bouserBenefitMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(BouserBenefitExample example) {
		
		return bouserBenefitMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param bouserBenefit
	 * @return
	 */
	public int checkNameUnique(BouserBenefit bouserBenefit){
		BouserBenefitExample example=new BouserBenefitExample();
		example.createCriteria().andUidEqualTo(bouserBenefit.getUid());
		List<BouserBenefit> list=bouserBenefitMapper.selectByExample(example);
		return list.size();
	}


}
