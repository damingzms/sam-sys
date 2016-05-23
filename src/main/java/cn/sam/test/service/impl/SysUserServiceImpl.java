package cn.sam.test.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.sam.test.dao.SysUserMapper;
import cn.sam.test.entity.SysUser;
import cn.sam.test.entity.SysUserExample;
import cn.sam.test.entity.SysUserExample.Criteria;
import cn.sam.test.helper.controllerAndView.Page;
import cn.sam.test.service.SysUserService;

@Service("sysUserService")
@Transactional
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	SysUserMapper sysUserMapper;

	@Override
	public List<SysUser> getUserList(SysUser user, Page<SysUser> page) {
		SysUserExample example = new SysUserExample();
		Criteria criteria = example.createCriteria();
		if (user != null) {
			if (StringUtils.isNotBlank(user.getLevel())) {
				criteria.andLevelEqualTo(user.getLevel());
			}
			if (StringUtils.isNotBlank(user.getUserName())) {
				criteria.andUserNameLike(user.getUserName() + "%");
			}
		}
		return sysUserMapper.selectByExampleWithRowbounds(example, page.getRowBounds());
	}

	@Override
	public SysUser getUserById(Integer id) {
		if (id == null) {
			return null;
		}
		return sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public void saveUser(SysUser user) {
		if (user == null) {
			return;
		}
		if (user.getId() != null) {
			SysUser dbObj = sysUserMapper.selectByPrimaryKey(user.getId());
			if (dbObj == null) {
				throw new RuntimeException("当前用户不存在。");
			}
			dbObj.setUserName(user.getUserName());
			dbObj.setLevel(user.getLevel());
			
			// 更新
			sysUserMapper.updateByPrimaryKey(dbObj);
		} else {
			
			// 插入
			sysUserMapper.insert(user);
		}
	}

	@Override
	public void delUser(Integer id) {
		if (id == null) {
			return;
		}
		sysUserMapper.deleteByPrimaryKey(id);
	}
	
}
