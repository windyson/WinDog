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
import com.yunbot.windog.mapper.auto.BoholdMapper;
import com.yunbot.windog.model.auto.Bohold;
import com.yunbot.windog.model.auto.BoholdExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  BoholdService
 * @Title: BoholdService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:21:07  
 **/
@Service
public class BoholdService implements BaseService<Bohold, BoholdExample>{
	@Autowired
	private BoholdMapper boholdMapper;
	
      	   	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Bohold> list(Tablepar tablepar,Bohold record){
	        BoholdExample testExample=new BoholdExample();
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
	        	testExample.setOrderByClause("cur_date desc");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<Bohold> list= boholdMapper.selectByExample(testExample);
	        PageInfo<Bohold> pageInfo = new PageInfo<Bohold>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			BoholdExample example=new BoholdExample();
			example.createCriteria().andTidIn(stringB);
			return boholdMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public Bohold selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return boholdMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(Bohold record) {
		return boholdMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(Bohold record) {
				
		record.setTid(null);
		
				
		return boholdMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(Bohold record, BoholdExample example) {
		
		return boholdMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(Bohold record, BoholdExample example) {
		
		return boholdMapper.updateByExample(record, example);
	}

	@Override
	public List<Bohold> selectByExample(BoholdExample example) {
		
		return boholdMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(BoholdExample example) {
		
		return boholdMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(BoholdExample example) {
		
		return boholdMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param bohold
	 * @return
	 */
	public int checkNameUnique(Bohold bohold){
		BoholdExample example=new BoholdExample();
		example.createCriteria().andUidEqualTo(bohold.getUid());
		List<Bohold> list=boholdMapper.selectByExample(example);
		return list.size();
	}


}
