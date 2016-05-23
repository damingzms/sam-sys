package cn.sam.test.service;

import java.util.List;

import cn.sam.test.entity.SysDictionary;

public interface SysDictionaryService {

	/**
	 * 根据type查找字典数据。先从缓存查找数据，找不到再从数据库查找数据，填充至缓存
	 * 
	 * @param type
	 * @return
	 */
	public List<SysDictionary> getByType(String type);

}
