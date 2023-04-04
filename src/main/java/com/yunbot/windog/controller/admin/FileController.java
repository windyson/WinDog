package com.yunbot.windog.controller.admin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.common.domain.AjaxResult;
import com.yunbot.windog.common.file.FileUtils;
import com.yunbot.windog.model.auto.TsysDatas;
import com.yunbot.windog.model.auto.TsysFile;
import com.yunbot.windog.model.custom.TableSplitResult;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.model.custom.TitleVo;
import com.yunbot.windog.util.StringUtils;
import com.github.pagehelper.PageInfo;

/**
 * 文件上传controller
 * @author fuce 
 * @date: 2018年9月16日 下午4:23:50
 */
@Api(value = "文件上传")
@Controller
@RequestMapping("/FileController")
public class FileController extends BaseController{

	//跳转页面参数
	private String prefix = "admin/file";
	
	/**
	 * 分页展示页面
	 * @param model
	 * @return
	 * @author fuce
	 * @Date 2019年11月20日 下午10:18:32
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("system:file:view")
    public String view(ModelMap model)
    {	
		String str="上传图片";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * 文件列表
	 * @param tablepar
	 * @param searchText 搜索字符
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@RequiresPermissions("system:file:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText){
		PageInfo<TsysFile> page=sysFileService.list(tablepar,searchText) ; 
		TableSplitResult<TsysFile> result=new TableSplitResult<TsysFile>(page.getPageNum(), page.getTotal(), page.getList()); 
		return  result;
	}
	
	/**
	 * 新增文件跳转页面
	 * @return
	 * @author fuce
	 * @Date 2019年11月20日 下午10:19:03
	 */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }
	
    
    
    /**
     * 文件上传
     * @param file
     * @return
     * @author fuce
     * @Date 2019年11月20日 下午10:18:49
     */
	//@Log(title = "文件上传", action = "1")
	@ApiOperation(value = "文件上传", notes = "文件上传")
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("file") MultipartFile file)
    {
        try
        {
            if (!file.isEmpty())
            {
                //插入文件存储表
            	String id=sysDatasService.insertSelective(file);
                if(id!=null){
                	 return AjaxResult.successData(200, id);
                }
            }
            return error();
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }
	
    /**
     * 文件添加
     * @param file
     * @return
     */
	//@Log(title = "文件添加", action = "1")
	@ApiOperation(value = "文件添加", notes = "文件添加")
	@PostMapping("/add")
	@RequiresPermissions("system:file:add")
	@ResponseBody
	public AjaxResult add(TsysFile file,String dataId){
		if(StringUtils.isNotEmpty(dataId)) {
			int b=sysFileService.insertSelective(file,dataId);
			if(b>0){
				return success();
			}
		}
		return error("请上传文件");
	}
	
	/**
	 * 删除文件
	 * @param ids
	 * @return
	 */
	//@Log(title = "删除文件", action = "1")
	@ApiOperation(value = "删除文件", notes = "删除文件")
	@PostMapping("/remove")
	@RequiresPermissions("system:file:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysFileService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	
	/**
	 * 删除本地文件
	 * @param ids
	 * @return
	 */
	//@Log(title = "删除本地文件", action = "1")
	@ApiOperation(value = "删除本地文件", notes = "删除本地文件")
	@PostMapping("/del_file")
	@ResponseBody
	public AjaxResult del_file(String ids){
		int b=sysFileService.deleteBydataFile(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	
	/**
	 * 检查文件名字
	 * @param tsysFile
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(TsysFile tsysFile){
		int b=sysFileService.checkNameUnique(tsysFile);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改文件
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("tsysFile", sysFileService.selectByPrimaryKey(id));
        return prefix + "/edit";
    }
	
	/**
     * 修改保存文件
     */
	//@Log(title = "修改保存文件", action = "1")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("system:user:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TsysFile tsysFile,String dataId)
    {
        return toAjax(sysFileService.updateByPrimaryKey(tsysFile,dataId));
    }
    
    /**
     * 展示文件跳转页面
     */
	@ApiOperation(value = "展示文件跳转页面", notes = "展示文件跳转页面")
    @GetMapping("/viewfile/{id}")
    public String viewfile(@PathVariable("id") String id,ModelMap mmap){
    	mmap.put("tsysDatas",sysFileDatasService.queryfileID(id));
        return prefix + "/viewfile";
    }
    
    /**
     * 此功能为application.yml 下面的isstatic为N 时候需要的
     * 逻辑为：根据数据库文件存放地址，读取图片流放入到<ima src>里面展示
     */
	@ApiOperation(value = "展示文件跳转页面", notes = "展示文件跳转页面")
    @GetMapping("/viewImg/{id}")
    public void viewIMG(@PathVariable("id") String id,HttpServletRequest request,HttpServletResponse response){
    	TsysDatas datas= sysDatasService.selectByPrimaryKey(id);
    	try {
			FileUtils.readIMGTohtml(request, response, datas.getFileAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
}
