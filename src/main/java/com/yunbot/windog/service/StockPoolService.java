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
import com.yunbot.windog.mapper.auto.StockPoolMapper;
import com.yunbot.windog.model.auto.StockPool;
import com.yunbot.windog.model.auto.StockPoolExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  StockPoolService
 * @Title: StockPoolService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:45  
 **/
@Service
public class StockPoolService implements BaseService<StockPool, StockPoolExample>{
	@Autowired
	private StockPoolMapper stockPoolMapper;
	
      	   	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<StockPool> list(Tablepar tablepar,StockPool record){
	        StockPoolExample testExample=new StockPoolExample();
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
	        	testExample.setOrderByClause("sid ASC");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<StockPool> list= stockPoolMapper.selectByExample(testExample);
	        PageInfo<StockPool> pageInfo = new PageInfo<StockPool>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			StockPoolExample example=new StockPoolExample();
			example.createCriteria().andSidIn(stringB);
			return stockPoolMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public StockPool selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return stockPoolMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(StockPool record) {
		return stockPoolMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(StockPool record) {
				
		record.setSid(null);
		
				
		return stockPoolMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(StockPool record, StockPoolExample example) {
		
		return stockPoolMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(StockPool record, StockPoolExample example) {
		
		return stockPoolMapper.updateByExample(record, example);
	}

	@Override
	public List<StockPool> selectByExample(StockPoolExample example) {
		
		return stockPoolMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(StockPoolExample example) {
		
		return stockPoolMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(StockPoolExample example) {
		
		return stockPoolMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param stockPool
	 * @return
	 */
	public int checkNameUnique(StockPool stockPool){
		StockPoolExample example=new StockPoolExample();
		example.createCriteria().andCodeEqualTo(stockPool.getCode());
		List<StockPool> list=stockPoolMapper.selectByExample(example);
		return list.size();
	}


}
