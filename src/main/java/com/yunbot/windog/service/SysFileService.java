package com.yunbot.windog.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ClassUtils;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.TsysDatasMapper;
import com.yunbot.windog.mapper.auto.TsysFileDataMapper;
import com.yunbot.windog.mapper.auto.TsysFileMapper;
import com.yunbot.windog.mapper.custom.TsysDatasDao;
import com.yunbot.windog.model.auto.TsysDatas;
import com.yunbot.windog.model.auto.TsysDatasExample;
import com.yunbot.windog.model.auto.TsysFile;
import com.yunbot.windog.model.auto.TsysFileData;
import com.yunbot.windog.model.auto.TsysFileDataExample;
import com.yunbot.windog.model.auto.TsysFileExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.shiro.util.ShiroUtils;
import com.yunbot.windog.util.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.hutool.core.io.FileUtil;

@Service
public class SysFileService implements BaseService<TsysFile, TsysFileExample>{
	
	//文件信息mapper
	@Autowired
	private TsysFileMapper tsysFileMapper;
	//文件存储关联mapper
	@Autowired
	private TsysFileDataMapper tsysFileDataMapper;
	//自定义文件dao
	@Autowired
	private TsysDatasDao tsysDatasDao;
	//文件存储mapper
	@Autowired
	private TsysDatasMapper tsysDatasMapper;
	
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TsysFile> list(Tablepar tablepar,String searchText){
	        TsysFileExample testExample=new TsysFileExample();
	        testExample.setOrderByClause("id+0 DESC");
	        if(searchText!=null&&!"".equals(searchText)){
	        	testExample.createCriteria().andFileNameLike("%"+searchText+"%");
	        }

	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<TsysFile> list= tsysFileMapper.selectByExample(testExample);
	        PageInfo<TsysFile> pageInfo = new PageInfo<TsysFile>(list);
	        return  pageInfo;
	 }

	/**
	 * 删除文件信息全部
	 * @param ids 文件集合 1,2,3
	 */
	@Transactional
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		//删除本地文件
		List<TsysDatas> datas=tsysDatasDao.selectByPrimaryKeys(lista);
		for (TsysDatas tsysDatas : datas) {
			deletefile(tsysDatas);
			//删除文件存储表
			tsysDatasMapper.deleteByPrimaryKey(tsysDatas.getId());
			
		}
		//删除数据库
		TsysFileExample example=new TsysFileExample();
		example.createCriteria().andIdIn(lista);
		tsysFileMapper.deleteByExample(example);
		
		//删除关联表
		TsysFileDataExample fileDataExample=new TsysFileDataExample();
		fileDataExample.createCriteria().andFileIdIn(lista);
		int i=tsysFileDataMapper.deleteByExample(fileDataExample);
		return i;
	}
		
	/**
	 * 删除文件存储表以及数据库
	 * @param ids 文件集合 1,2,3
	 */
	public int deleteBydataFile(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		//删除本地文件
		TsysDatasExample example=new TsysDatasExample();
		example.createCriteria().andIdIn(lista);
		List<TsysDatas> datas=tsysDatasMapper.selectByExample(example);
		
		for (TsysDatas tsysDatas : datas) {
			deletefile(tsysDatas);
			//删除文件存储表
			tsysDatasMapper.deleteByPrimaryKey(tsysDatas.getId());
		}
		//删除数据库
		return tsysDatasMapper.deleteByExample(example);
	}
	
	
	/**
	 *删除本地文件方法
	 */
	public void deletefile(TsysDatas tsysDatas) {
		if("Y".equals(tsysDatas.getFileType())) {
			String url=ClassUtils.getDefaultClassLoader().getResource("").getPath()+tsysDatas.getFileAbsolutePath();
			FileUtil.del(url);
		}else {
			FileUtil.del(tsysDatas.getFileAbsolutePath());
		}
		
		
	}

	
	
	@Transactional
	public int insertSelective(TsysFile record,String dataId) {
		//插入创建人id
		record.setCreateUserId(ShiroUtils.getUserId());
		//插入创建人name
		record.setCreateUserName(ShiroUtils.getLoginName());
		//插入创建时间
		record.setCreateTime(new Date());
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		//插入关联表
		TsysFileData tsysFileData=new TsysFileData();
		tsysFileData.setId(SnowflakeIdWorker.getUUID());
		tsysFileData.setFileId(record.getId());
		tsysFileData.setDataId(dataId);
		tsysFileDataMapper.insert(tsysFileData);
		return tsysFileMapper.insertSelective(record);
	}

	@Override
	public TsysFile selectByPrimaryKey(String id) {
		
		return tsysFileMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysFile record) {
		return tsysFileMapper.updateByPrimaryKeySelective(record);
	}
	
	/**
	 * 修改信息
	 * @param record
	 * @return
	 */
	@Transactional
	public int updateByPrimaryKey(TsysFile record,String dataId) {
		//获取旧数据
		TsysFile old_data=tsysFileMapper.selectByPrimaryKey(record.getId());
		
		//删除绑定数据
		TsysFileDataExample fileDataExample=new TsysFileDataExample();
		fileDataExample.createCriteria().andFileIdEqualTo(record.getId());
		tsysFileDataMapper.deleteByExample(fileDataExample);
		
		
		//插入关联表
		TsysFileData tsysFileData=new TsysFileData();
		tsysFileData.setId(SnowflakeIdWorker.getUUID());
		tsysFileData.setFileId(record.getId());
		tsysFileData.setDataId(dataId);
		tsysFileDataMapper.insert(tsysFileData);
		
		//修改旧数据
		//插入修改人id
		old_data.setUpdateUserId(ShiroUtils.getUserId());
		//插入修改人name
		old_data.setUpdateUserName(ShiroUtils.getLoginName());
		//插入修改时间
		old_data.setUpdateTime(new Date());
		
		
		return tsysFileMapper.updateByPrimaryKey(old_data);
	}

	
	@Override
	public int updateByExampleSelective(TsysFile record, TsysFileExample example) {
		
		return tsysFileMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysFile record, TsysFileExample example) {
		
		return tsysFileMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysFile> selectByExample(TsysFileExample example) {
		
		return tsysFileMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysFileExample example) {
		
		return tsysFileMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysFileExample example) {
		
		return tsysFileMapper.deleteByExample(example);
	}
	
	/**
	 * 检查文件名字
	 * @param TsysFile
	 * @return
	 */
	public int checkNameUnique(TsysFile tsysFile){
		TsysFileExample example=new TsysFileExample();
		example.createCriteria().andFileNameEqualTo(tsysFile.getFileName());
		List<TsysFile> list=tsysFileMapper.selectByExample(example);
		return list.size();
	}

	
	//无用
	@Override
	public int insertSelective(TsysFile record) {
		
		return tsysFileMapper.insertSelective(record);
	}
	
}
