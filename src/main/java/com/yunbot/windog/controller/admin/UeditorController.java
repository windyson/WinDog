package com.yunbot.windog.controller.admin;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.yunbot.windog.common.base.BaseController;
import com.yunbot.windog.model.auto.TsysDatas;
import com.yunbot.windog.model.custom.PublicMsg;
import com.yunbot.windog.model.custom.Ueditor;
import com.yunbot.windog.service.UeditorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 百度Controller
 * 
 * @ClassName: UeditorController
 * @author fuce
 * @date 2019-11-20 22:34
 */
@Api(value = "百度编辑器")
@Controller
@RequestMapping("/UeditorController")
public class UeditorController extends BaseController {
	@Autowired
	private UeditorService ueditorService;

	@ApiOperation(value = "百度编辑器配置", notes = "百度编辑器配置")
	@RequestMapping("/ueditor")
	@ResponseBody
	public Object ueditor(HttpServletRequest request, String action, MultipartFile upfile) {
		if ("config".equals(action)) {
			return PublicMsg.UEDITOR_CONFIG;
		}
		if ("uploadimage".equals(action) || "uploadvideo".equals(action) ) {// 图片-视频（音频）上传

			try {
				// 添加文件到本地
				TsysDatas tsysDatas = ueditorService.fileDataByinsert(upfile);
				// 添加文件信息
				ueditorService.fileInfoByininsert(tsysDatas.getId());
				String url2 = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort()
						+ request.getContextPath() + "/" + tsysDatas.getFilePath();
				Ueditor ueditor = new Ueditor("SUCCESS", url2, "ssss", "");
				return ueditor;
			} catch (IOException e) {
				e.printStackTrace();
			}

			return null;
		}
		return PublicMsg.UeditorMsg.ERROR.get();
	}

	// @Log(title = "图片上传", action = "1")
	@ApiOperation(value = "图片上传", notes = "分页查询")
	@RequestMapping(value = "/imgUpload")
	@ResponseBody
	public Ueditor imgUpload(MultipartFile upfile) {
		Ueditor ueditor = new Ueditor();
		return ueditor;
	}

	public String updateAvatar(MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				// 插入文件存储表
				String id = sysDatasService.insertSelective(file);
				if (id != null) {
					return id;
				}
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}
