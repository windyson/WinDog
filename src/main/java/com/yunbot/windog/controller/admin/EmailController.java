package com.yunbot.windog.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.common.domain.AjaxResult;
import com.yunbot.windog.model.auto.TSysEmail;
import com.yunbot.windog.model.custom.TableSplitResult;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.model.custom.TitleVo;
import com.yunbot.windog.service.TSysEmailService;
import com.yunbot.windog.util.SimpleEmailUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 邮件发送Controller
 * @ClassName: EmailController
 * @author fuce
 * @date 2019-06-10 00:39
 * @version V1.0
 */
@Api(value = "邮件发送Controller")
@Controller
@RequestMapping("/EmailController")
public class EmailController extends BaseController{
		
	private String prefix = "admin/email";
	
	@Autowired
	private TSysEmailService tSysEmailService;
	
	/**
	 * 分页展示页面
	 * @param model
	 * @param dictId
	 * @return
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("system:email:view")
    public String view(ModelMap model)
    {	
		String str="邮件";
		setTitle(model, new TitleVo("发送", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }

	/**
	 * 分页list页面
	 * @param model
	 * @param dictId
	 * @return
	 */
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@RequiresPermissions("system:email:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String searchText){
		PageInfo<TSysEmail> page=tSysEmailService.list(tablepar,searchText) ; 
		TableSplitResult<TSysEmail> result=new TableSplitResult<TSysEmail>(page.getPageNum(), page.getTotal(), page.getList()); 
		return  result;
	}
	
	/**
     * 新增跳转
     */
	@ApiOperation(value = "新增跳转", notes = "新增跳转")
    @GetMapping("/add")
    public String add(ModelMap modelMap)
    {
        return prefix + "/add";
    }
	
	/**
	 * 新增
	 * @param tSysEmail
	 * @param model
	 * @return
	 * @throws Exception
	 * @author fuce
	 */
	//@Log(title = "新增邮件", action = "1")
	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("system:email:add")
	@ResponseBody
	public AjaxResult add(TSysEmail tSysEmail,Model model) throws Exception{
		int b=tSysEmailService.insertSelective(tSysEmail);
		SimpleEmailUtil.sendEmail(tSysEmail);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 删除邮件
	 * @param ids
	 * @return
	 */
	//@Log(title = "删除邮件", action = "1")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("/remove")
	@RequiresPermissions("system:email:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=tSysEmailService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查邮件同名
	 * @param tsysUser
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(TSysEmail tSysEmail){
		int b=tSysEmailService.checkNameUnique(tSysEmail);
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
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("TSysEmail", tSysEmailService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
	//@Log(title = "修改保存", action = "1")
	@ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("system:email:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TSysEmail record)
    {
        return toAjax(tSysEmailService.updateByPrimaryKeySelective(record));
    }


}
