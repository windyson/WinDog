package com.yunbot.windog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.util.StrUtil;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.StockDailyMapper;
import com.yunbot.windog.model.auto.StockDaily;
import com.yunbot.windog.model.auto.StockDailyExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.SnowflakeIdWorker;
import com.yunbot.windog.util.StringUtils;

/**
 *  StockDailyService
 * @Title: StockDailyService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2023-01-28 13:51:36  
 **/
@Service
public class StockDailyService implements BaseService<StockDaily, StockDailyExample>{
	@Autowired
	private StockDailyMapper stockDailyMapper;
	
      	   	      	   	      	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<StockDaily> list(Tablepar tablepar,StockDaily record){
	        StockDailyExample testExample=new StockDailyExample();
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
	        	testExample.setOrderByClause("trade_date ASC");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<StockDaily> list= stockDailyMapper.selectByExample(testExample);
	        PageInfo<StockDaily> pageInfo = new PageInfo<StockDaily>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
				
			List<String> lista=ConvertUtil.toListStrArray(ids);
			StockDailyExample example=new StockDailyExample();
			example.createCriteria().andTradeDateIn(lista);
			return stockDailyMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public StockDaily selectByPrimaryKey(String id) {
				
			return stockDailyMapper.selectByPrimaryKey(id);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(StockDaily record) {
		return stockDailyMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(StockDaily record) {
				
		//添加雪花主键id
		record.setTradeDate(SnowflakeIdWorker.getUUID());
			
				
		return stockDailyMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(StockDaily record, StockDailyExample example) {
		
		return stockDailyMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(StockDaily record, StockDailyExample example) {
		
		return stockDailyMapper.updateByExample(record, example);
	}

	@Override
	public List<StockDaily> selectByExample(StockDailyExample example) {
		
		return stockDailyMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(StockDailyExample example) {
		
		return stockDailyMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(StockDailyExample example) {
		
		return stockDailyMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param stockDaily
	 * @return
	 */
	public int checkNameUnique(StockDaily stockDaily){
		StockDailyExample example=new StockDailyExample();
		example.createCriteria().andOpenEqualTo(stockDaily.getOpen());
		List<StockDaily> list=stockDailyMapper.selectByExample(example);
		return list.size();
	}


}
