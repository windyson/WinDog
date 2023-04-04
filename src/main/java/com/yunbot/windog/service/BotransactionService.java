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
import com.yunbot.windog.mapper.auto.BotransactionMapper;
import com.yunbot.windog.model.auto.Botransaction;
import com.yunbot.windog.model.auto.BotransactionExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.StringUtils;

/**
 *  BotransactionService
 * @Title: BotransactionService.java 
 * @Package com.yunbot.windog.service 
 * @author william_自动生成
 * @email 710984@qq.com
 * @date 2022-06-18 23:22:27  
 **/
@Service
public class BotransactionService implements BaseService<Botransaction, BotransactionExample>{
	@Autowired
	private BotransactionMapper botransactionMapper;
	
      	   	      	      	      	      	      	      	      	      	      	      	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<Botransaction> list(Tablepar tablepar,Botransaction record){
	        BotransactionExample testExample=new BotransactionExample();
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
	        List<Botransaction> list= botransactionMapper.selectByExample(testExample);
	        PageInfo<Botransaction> pageInfo = new PageInfo<Botransaction>(list);
	        return  pageInfo;
	 }

	@Override
	public int deleteByPrimaryKey(String ids) {
					
			Integer[] integers = ConvertUtil.toIntArray(",", ids);
			List<Integer> stringB = Arrays.asList(integers);
			BotransactionExample example=new BotransactionExample();
			example.createCriteria().andTidIn(stringB);
			return botransactionMapper.deleteByExample(example);
			
				
	}
	
	
	@Override
	public Botransaction selectByPrimaryKey(String id) {
				
			Integer id1 = Integer.valueOf(id);
			return botransactionMapper.selectByPrimaryKey(id1);
				
	}

	
	@Override
	public int updateByPrimaryKeySelective(Botransaction record) {
		return botransactionMapper.updateByPrimaryKeySelective(record);
	}
	
	
	/**
	 * 添加
	 */
	@Override
	public int insertSelective(Botransaction record) {
				
		record.setTid(null);
		
				
		return botransactionMapper.insertSelective(record);
	}
	
	
	@Override
	public int updateByExampleSelective(Botransaction record, BotransactionExample example) {
		
		return botransactionMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(Botransaction record, BotransactionExample example) {
		
		return botransactionMapper.updateByExample(record, example);
	}

	@Override
	public List<Botransaction> selectByExample(BotransactionExample example) {
		
		return botransactionMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(BotransactionExample example) {
		
		return botransactionMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(BotransactionExample example) {
		
		return botransactionMapper.deleteByExample(example);
	}
	
	/**
	 * 检查name
	 * @param botransaction
	 * @return
	 */
	public int checkNameUnique(Botransaction botransaction){
		BotransactionExample example=new BotransactionExample();
		example.createCriteria().andUidEqualTo(botransaction.getUid());
		List<Botransaction> list=botransactionMapper.selectByExample(example);
		return list.size();
	}


}
