package com.yunbot.windog.controller.gen;

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
import com.yunbot.windog.model.auto.StockDaily;
import com.yunbot.windog.model.custom.TableSplitResult;
import com.yunbot.windog.model.custom.Tablepar;
import com.yunbot.windog.model.custom.TitleVo;
import com.yunbot.windog.service.StockDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "")
@Controller
@RequestMapping("/StockDailyController")
public class StockDailyController extends BaseController{
	
	private String prefix = "gen/stockDaily";
	@Autowired
	private StockDailyService stockDailyService;
	
	/**
	 * 分页跳转
	 */
	@ApiOperation(value = "分页跳转", notes = "分页跳转")
	@GetMapping("/view")
	@RequiresPermissions("gen:stockDaily:view")
    public String view(ModelMap model)
    {	
		String str="";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
	
	/**
	 * 分页查询
	 */
	//@Log(title = "集合查询", action = "111")
	@ApiOperation(value = "分页查询", notes = "分页查询")
	@PostMapping("/list")
	@RequiresPermissions("gen:stockDaily:list")
	@ResponseBody
	public Object list(Tablepar tablepar,StockDaily record){
		PageInfo<StockDaily> page=stockDailyService.list(tablepar,record) ; 
		TableSplitResult<StockDaily> result=new TableSplitResult<StockDaily>(page.getPageNum(), page.getTotal(), page.getList()); 
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
     */
	//@Log(title = "新增", action = "111")
   	@ApiOperation(value = "新增", notes = "新增")
	@PostMapping("/add")
	@RequiresPermissions("gen:stockDaily:add")
	@ResponseBody
	public AjaxResult add(StockDaily stockDaily){
		int b=stockDailyService.insertSelective(stockDaily);
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
	//@Log(title = "删除", action = "111")
	@ApiOperation(value = "删除", notes = "删除")
	@PostMapping("/remove")
	@RequiresPermissions("gen:stockDaily:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=stockDailyService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}
	
	/**
	 * 检查Name
	 * @param tsysUser
	 * @return
	 */
	@ApiOperation(value = "检查Name唯一", notes = "检查Name唯一")
	@PostMapping("/checkNameUnique")
	@ResponseBody
	public int checkNameUnique(StockDaily stockDaily){
		int b=stockDailyService.checkNameUnique(stockDaily);
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
        mmap.put("StockDaily", stockDailyService.selectByPrimaryKey(id));

        return prefix + "/edit";
    }
	
	/**
     * 修改保存
     */
    //@Log(title = "修改", action = "111")
    @ApiOperation(value = "修改保存", notes = "修改保存")
    @RequiresPermissions("gen:stockDaily:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StockDaily record)
    {
        return toAjax(stockDailyService.updateByPrimaryKeySelective(record));
    }

    
    /**
   	 * 根据主键查询
   	 * 
   	 * @param id
   	 * @param mmap
   	 * @return
   	 */
   	@ApiOperation(value = "根据id查询唯一", notes = "根据id查询唯一")
   	@PostMapping("/get/{id}")
   	public StockDaily edit(@PathVariable("id") String id) {
   		return stockDailyService.selectByPrimaryKey(id);
   	}
    

	
}
