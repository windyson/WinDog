package com.yunbot.windog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yunbot.windog.common.domain.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 该接口为了前后端分离or手机端服务不用登录的接口  访问地址:localhot:8080/ApiController/test
 * 如何还需要其他 接口不登陆就能访问：ShiroFilterMapFactory.java里面配置开放自己的controller
 * @ClassName: ApiController
 * @author fuce
 * @date 2020-04-15 22:59
 */
@Api(value = "接口API")
@Controller
@RequestMapping("/ApiController")
public class ApiController {
	
	//@Log(title = "${TsysTables.tableComment}集合查询", action = "111")
	@ApiOperation(value = "测试方法", notes = "测试方法")
	@GetMapping("/test")
	@ResponseBody
	public AjaxResult test() {

		return AjaxResult.success();
	}
}
