package cn.sam.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.sam.test.entity.SysDictionary;
import cn.sam.test.entity.SysUser;
import cn.sam.test.helper.controllerAndView.Page;
import cn.sam.test.service.SysDictionaryService;
import cn.sam.test.service.SysUserService;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysDictionaryService sysDictionaryService;

	@RequestMapping("")
	public String getUserList(ModelMap model, SysUser user, Page<SysUser> page) {
		List<SysDictionary> typeList = sysDictionaryService.getByType(SysDictionary.TYPE_LEVEL);
		List<SysUser> userList = sysUserService.getUserList(user, page);
		model.put("criteria", user);
		model.put("typeList", typeList);
		model.put("userList", userList);
		return "userList";
	}

	@RequestMapping("/toEditUser")
	public String toEditUser(HttpServletRequest request, ModelMap model, SysUser user) {
		List<SysDictionary> typeList = sysDictionaryService.getByType(SysDictionary.TYPE_LEVEL);
		model.put("typeList", typeList);
		if (user.getId() != null) {
			SysUser dbUser = sysUserService.getUserById(user.getId());
			model.put("user", dbUser);
		}
		return "userEdit";
	}

	@RequestMapping("/saveUser")
	public String saveUser(HttpServletRequest request, ModelMap model, SysUser user) {
		sysUserService.saveUser(user);
		return "redirect:/sysUser";
	}

	@RequestMapping("/delUser")
	public String delUser(HttpServletRequest request, ModelMap model, SysUser user) {
		sysUserService.delUser(user.getId());
		return "redirect:/sysUser";
	}

}
