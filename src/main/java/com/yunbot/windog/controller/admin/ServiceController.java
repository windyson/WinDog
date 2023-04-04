package com.yunbot.windog.controller.admin;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.model.custom.Service;
import com.yunbot.windog.model.custom.TitleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 服务器信息Controller
 * @ClassName: ServiceController
 * @author fuce
 * @date 2019-06-23 00:55
 * @version V1.0
 */
@Api(value = "服务器信息")
@Controller
@RequestMapping("/ServiceController")
public class ServiceController extends BaseController{

	//跳转页面参数
	private String prefix = "admin/service";
	
	/**
	 * 展示页面
	 * @param model
	 * @return
	 * @author fuce
	 * @Date 2019年11月11日 下午4:04:03
	 */
	@ApiOperation(value = "展示页面", notes = "展示页面")
	@GetMapping("/view")
	@RequiresPermissions("system:service:view")
    public String view(ModelMap model)
    {	
		
		String str="服务器";
		setTitle(model, new TitleVo("列表", str+"管理", true,"欢迎进入"+str+"页面", true, false));
		model.addAttribute("service", new Service());
        return prefix + "/list";
    }
}
