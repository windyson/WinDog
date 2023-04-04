package com.yunbot.windog.service;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.yunbot.windog.common.base.BaseService;
import com.yunbot.windog.common.conf.V2Config;
import com.yunbot.windog.common.file.FileUploadUtils;
import com.yunbot.windog.common.support.ConvertUtil;
import com.yunbot.windog.mapper.auto.TsysDatasMapper;
import com.yunbot.windog.model.auto.TsysDatas;
import com.yunbot.windog.model.auto.TsysDatasExample;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.util.SnowflakeIdWorker;
import com.yunbot.windog.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class SysDatasService implements BaseService<TsysDatas, TsysDatasExample>{
	
	
	@Autowired
	private TsysDatasMapper tsysDatasMapper;
	
	/**
	 * 分页查询
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	 public PageInfo<TsysDatas> list(Tablepar tablepar,String searchText){
	        TsysDatasExample testExample=new TsysDatasExample();
	        testExample.setOrderByClause("id+0 DESC");
	        if(searchText!=null&&!"".equals(searchText)){
	        	testExample.createCriteria().andIdLike("%"+searchText+"%");
	        }
	        PageHelper.startPage(tablepar.getPageNum(), tablepar.getPageSize());
	        List<TsysDatas> list= tsysDatasMapper.selectByExample(testExample);
	        PageInfo<TsysDatas> pageInfo = new PageInfo<TsysDatas>(list);
	        return  pageInfo;
	 }

	
	@Override
	public int deleteByPrimaryKey(String ids) {
		List<String> lista=ConvertUtil.toListStrArray(ids);
		TsysDatasExample example=new TsysDatasExample();
		example.createCriteria().andIdIn(lista);
		return tsysDatasMapper.deleteByExample(example);
	}
	
	
	
	
	
	
	/**
	 * 文件上传文件存储到文件表
	 * @param record
	 * @param fileURL
	 * @return 主键
	 * @throws IOException 
	 */
	public String insertSelective(MultipartFile file) throws IOException {
		//文件上传获取文件名字
        String files_name = FileUploadUtils.upload(file);
        //相对路径——项目url请求路径
        String relative_filesURL=V2Config.getIsroot_dir()+files_name;
        //绝对路径-删除需要得路径
        String absolute_filesURL=null;
        
        
    	if ("Y".equals(V2Config.getIsstatic())) {//项目路径
           	absolute_filesURL=V2Config.getIsroot_dir()+files_name;
   		}else {//磁盘路径
   			absolute_filesURL=V2Config.getDefaultBaseDir()+files_name;
   			//filesURL=V2Config.getIsroot_dir()+files;
   		}
     
        String fileName=file.getOriginalFilename();
    	// 获得文件后缀名称
    	String suffixName = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    	if(StringUtils.isEmpty(suffixName)) {
    		//如果没有后缀默认后缀
    		suffixName=FileUploadUtils.IMAGE_JPG_EXTENSION;
    	}
     
		TsysDatas record=new TsysDatas();
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		record.setFilePath(relative_filesURL);
		record.setFileAbsolutePath(absolute_filesURL);
		record.setFileSuffix(suffixName);
		//上传路径类型
		record.setFileType(V2Config.getIsstatic());
		if(tsysDatasMapper.insertSelective(record)>0)
		{
			return record.getId();
		}
		return null;
	}
	
	@Override
	public int insertSelective(TsysDatas record) {
		//添加雪花主键id
		record.setId(SnowflakeIdWorker.getUUID());
		return tsysDatasMapper.insertSelective(record);
	}

	@Override
	public TsysDatas selectByPrimaryKey(String id) {
		
		return tsysDatasMapper.selectByPrimaryKey(id);
	}

	
	@Override
	public int updateByPrimaryKeySelective(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKeySelective(record);
	}
	
	public int updateByPrimaryKey(TsysDatas record) {
		return tsysDatasMapper.updateByPrimaryKey(record);
	}

	
	@Override
	public int updateByExampleSelective(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExampleSelective(record, example);
	}

	
	@Override
	public int updateByExample(TsysDatas record, TsysDatasExample example) {
		
		return tsysDatasMapper.updateByExample(record, example);
	}

	@Override
	public List<TsysDatas> selectByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.selectByExample(example);
	}

	
	@Override
	public long countByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.countByExample(example);
	}

	
	@Override
	public int deleteByExample(TsysDatasExample example) {
		
		return tsysDatasMapper.deleteByExample(example);
	}
	

	
}
