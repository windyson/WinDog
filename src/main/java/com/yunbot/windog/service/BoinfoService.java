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
import com.yunbot.windog.mapper.auto.BoinfoMapper;
import com.yunbot.windog.model.auto.Boinfo;
import com.yunbot.windog.model.auto.BoinfoExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  BoinfoService
 * @Title: BoinfoService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:02:09  
 **/
@Service
public class BoinfoService implements BaseService<Boinfo, BoinfoExample>{
	@Autowired
	private BoinfoMapper boinfoMapper;
	
      	   	      	      	      	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Boinfo> list(Tablepar tablepar,Boinfo record){
	        BoinfoExample testExample=new BoinfoExample();
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
	        	testExample.setOrderByClause("uid ASC");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<Boinfo> list= boinfoMapper.selectByExample(testExample);
	        PageInfo<Boinfo> pageInfo = new PageInfo<Boinfo>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			BoinfoExample example=new BoinfoExample();
			example.createCriteria().andUidIn(stringB);
			return boinfoMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public Boinfo selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return boinfoMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(Boinfo record) {
		return boinfoMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(Boinfo record) {
				
		record.setUid(null);
		
				
		return boinfoMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(Boinfo record, BoinfoExample example) {
		
		return boinfoMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(Boinfo record, BoinfoExample example) {
		
		return boinfoMapper.updateByExample(record, example);
	}

	@Override
	public List<Boinfo> selectByExample(BoinfoExample example) {
		
		return boinfoMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(BoinfoExample example) {
		
		return boinfoMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(BoinfoExample example) {
		
		return boinfoMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param boinfo
	 * @return
	 */
	public int checkNameUnique(Boinfo boinfo){
		BoinfoExample example=new BoinfoExample();
		example.createCriteria().andNameEqualTo(boinfo.getName());
		List<Boinfo> list=boinfoMapper.selectByExample(example);
		return list.size();
	}


}
