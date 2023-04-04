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
import com.yunbot.windog.mapper.auto.StockLibMapper;
import com.yunbot.windog.model.auto.StockLib;
import com.yunbot.windog.model.auto.StockLibExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  StockLibService
 * @Title: StockLibService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-13 23:03:08  
 **/
@Service
public class StockLibService implements BaseService<StockLib, StockLibExample>{
	@Autowired
	private StockLibMapper stockLibMapper;
	
      	   	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<StockLib> list(Tablepar tablepar,StockLib record){
	        StockLibExample testExample=new StockLibExample();
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
	        	testExample.setOrderByClause("insdt desc, metric asc, val desc");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<StockLib> list= stockLibMapper.selectByExample(testExample);
	        PageInfo<StockLib> pageInfo = new PageInfo<StockLib>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			StockLibExample example=new StockLibExample();
			example.createCriteria().andSidIn(stringB);
			return stockLibMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public StockLib selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return stockLibMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(StockLib record) {
		return stockLibMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(StockLib record) {
				
		record.setSid(null);
		
				
		return stockLibMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(StockLib record, StockLibExample example) {
		
		return stockLibMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(StockLib record, StockLibExample example) {
		
		return stockLibMapper.updateByExample(record, example);
	}

	@Override
	public List<StockLib> selectByExample(StockLibExample example) {
		
		return stockLibMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(StockLibExample example) {
		
		return stockLibMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(StockLibExample example) {
		
		return stockLibMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param stockLib
	 * @return
	 */
	public int checkNameUnique(StockLib stockLib){
		StockLibExample example=new StockLibExample();
		example.createCriteria().andCodeEqualTo(stockLib.getCode());
		List<StockLib> list=stockLibMapper.selectByExample(example);
		return list.size();
	}


}
