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
import com.yunbot.windog.mapper.auto.StockCalMapper;
import com.yunbot.windog.model.auto.StockCal;
import com.yunbot.windog.model.auto.StockCalExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  StockCalService
 * @Title: StockCalService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:01:36  
 **/
@Service
public class StockCalService implements BaseService<StockCal, StockCalExample>{
	@Autowired
	private StockCalMapper stockCalMapper;
	
      	   	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<StockCal> list(Tablepar tablepar,StockCal record){
	        StockCalExample testExample=new StockCalExample();
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
	        	testExample.setOrderByClause("opendt desc");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<StockCal> list= stockCalMapper.selectByExample(testExample);
	        PageInfo<StockCal> pageInfo = new PageInfo<StockCal>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			StockCalExample example=new StockCalExample();
			example.createCriteria().andIdIn(stringB);
			return stockCalMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public StockCal selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return stockCalMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(StockCal record) {
		return stockCalMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(StockCal record) {
				
		record.setId(null);
		
				
		return stockCalMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(StockCal record, StockCalExample example) {
		
		return stockCalMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(StockCal record, StockCalExample example) {
		
		return stockCalMapper.updateByExample(record, example);
	}

	@Override
	public List<StockCal> selectByExample(StockCalExample example) {
		
		return stockCalMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(StockCalExample example) {
		
		return stockCalMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(StockCalExample example) {
		
		return stockCalMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param stockCal
	 * @return
	 */
	public int checkNameUnique(StockCal stockCal){
		StockCalExample example=new StockCalExample();
		example.createCriteria().andOpendtEqualTo(stockCal.getOpendt());
		List<StockCal> list=stockCalMapper.selectByExample(example);
		return list.size();
	}


}
