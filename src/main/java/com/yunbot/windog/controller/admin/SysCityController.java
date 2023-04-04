package com.yunbot.windog.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.common.domain.AjaxResult;
import com.yunbot.windog.model.auto.SysCity;
import com.yunbot.windog.model.custom.TableSplitResult;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.model.custom.TitleVo;
import com.yunbot.windog.service.SysCityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 城市Controller
 * @ClassName: SysCityController
 * @author fuce
 * @date 2019-11-20 22:31
 */
@Api(value = "城市设置")
@Controller
@RequestMapping("/SysCityController")
public class SysCityController extends BaseController{
	
	private String prefix = "admin/province/sysCity";
	@Autowired
	private SysCityService sysCityService;
	
	/**
	 * 城市设置展示跳转
	 * 
	 * @param model
	 * @return
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:sysCity:view")
    public String view(ModelMap model)
    {	
		String str="城市设置";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * 城市设置list
	 * @param tablepar
	 * @param searchText
	 * @return
	 */
	//@Log(title = "城市设置集合查询", action = "111")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@RequiresPermissions("gen:sysCity:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText){
		PageInfo<SysCity> page=sysCityService.list(tablepar,searchText) ; 
		TableSplitResult<SysCity> result=new TableSplitResult<SysCity>(page.getPageNum(), page.getTotal(), page.getList()); 
		return  result;
	}
	
	/**
	 * 新增跳转
	 * @param modelMap
	 * @return
	 */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	/**
	 * 新增保存
	 * @param sysCity
	 * @return
	 */
	//@Log(title = "城市设置新增", action = "111")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("gen:sysCity:add")
	@ResponseBody
	public AjaxResult add(SysCity sysCity){
		int b=sysCityService.insertSelective(sysCity);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	//@Log(title = "城市设置删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("/remove")
	@RequiresPermissions("gen:sysCity:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysCityService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查
	 * @param tsysUser
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(SysCity sysCity){
		int b=sysCityService.checkNameUnique(sysCity);
		if(b>0){
			return 1;
		}else{
			return 0;
		}
	}
	
	
	/**
	 * 修改跳转
	 * @param id
	 * @param mmap
	 * @return
	 */
	@ApiOperation(value = "修改跳转", notes = "修改跳转")
	@GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        mmap.put("SysCity", sysCityService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "城市设置修改", action = "111")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("gen:sysCity:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCity record)
    {
        return toAjax(sysCityService.updateByPrimaryKeySelective(record));
    }

    
    

	
}
