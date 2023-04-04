package com.yunbot.windog.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.TsysDatasMapper;
import com.yunbot.windog.mapper.auto.TsysFileDataMapper;
import com.yunbot.windog.model.auto.TsysDatas;
import com.yunbot.windog.model.auto.TsysFileData;
import com.yunbot.windog.model.auto.TsysFileDataExample;
import com.yunbot.windog.util.SnowflakeIdWorker;

@Service
public class SysFileDatasService implements BaseService<TsysFileData, TsysFileDataExample>{
	
	@Autowired
	private TsysFileDataMapper tsysFileDataMapper;
	@Autowired
	private TsysDatasMapper tsysDatasMapper;
	
	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		TsysFileDataExample example=new TsysFileDataExample();
		example.createCriteria().andIdIn(lista);
		return tsysFileDataMapper.deleteByExample(example);
	}

	@Override
	public int insertSelective(TsysFileData record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		return tsysFileDataMapper.insertSelective(record);
	}

	@Override
	public TsysFileData selectByPrimaryKey(String id) {
		
		return tsysFileDataMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(TsysFileData record) {
		
		return tsysFileDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByExampleSelective(TsysFileData record,
			TsysFileDataExample example) {
		
		return tsysFileDataMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByExample(TsysFileData record, TsysFileDataExample example) {
		
		return tsysFileDataMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysFileData> selectByExample(TsysFileDataExample example) {
		
		return tsysFileDataMapper.selectByExample(example);
	}

	@Override
	public long countByExample(TsysFileDataExample example) {
		
		return tsysFileDataMapper.countByExample(example);
	}

	@Override
	public int deleteByExample(TsysFileDataExample example) {
	
		return tsysFileDataMapper.deleteByExample(example);
	}
	
	/**
	 * 根据文件列表id查询出对应的文件信息,已经支持多个图片获取
	 * @param fileid
	 * @return
	 */
	public List<TsysDatas> queryfileID(String fileid){
		List<TsysDatas> list=new ArrayList<TsysDatas>();
		TsysFileDataExample fileDataExample=new TsysFileDataExample();
		fileDataExample.createCriteria().andFileIdEqualTo(fileid);
		List<TsysFileData> fileDatas=tsysFileDataMapper.selectByExample(fileDataExample);
		for (TsysFileData tsysFileData : fileDatas) {
			list.add(tsysDatasMapper.selectByPrimaryKey(tsysFileData.getDataId()));
		}
		return list;
	}

}
