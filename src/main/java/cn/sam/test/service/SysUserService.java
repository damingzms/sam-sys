package cn.sam.test.service;

import java.util.List;

import cn.sam.test.entity.SysUser;
import cn.sam.test.helper.controllerAndView.Page;

public interface SysUserService {

	public List<SysUser> getUserList(SysUser user, Page<SysUser> page);

	public SysUser getUserById(Integer id);
	
	public void saveUser(SysUser user);
	
	public void delUser(Integer id);

}
