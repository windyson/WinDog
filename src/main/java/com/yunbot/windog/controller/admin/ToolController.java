package com.yunbot.windog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.model.custom.TitleVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 表单构建Controller
 * @ClassName: ToolController
 * @author fuce
 * @date 2019-11-20 22:32
 */
@Api(value = "表单构建工具类")
@Controller
@RequestMapping("/ToolController")
public class ToolController  extends BaseController{

	//跳转页面参数
	private String prefix = "admin/tool";
	
	
	@ApiOperation(value = "表单构建工具类", notes = "表单构建工具类")
	@GetMapping("/view")
    public String view(ModelMap model)
    {	
		
		String str="工具";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
        return prefix + "/list";
    }
			
}
