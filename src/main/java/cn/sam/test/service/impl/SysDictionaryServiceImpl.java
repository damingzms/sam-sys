package cn.sam.test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import cn.sam.test.dao.SysDictionaryMapper;
import cn.sam.test.entity.SysDictionary;
import cn.sam.test.entity.SysDictionaryExample;
import cn.sam.test.service.CacheService;
import cn.sam.test.service.SysDictionaryService;
import cn.sam.test.util.JsonUtil;

@Service("sysDictionaryService")
@Transactional
public class SysDictionaryServiceImpl implements SysDictionaryService {
	
	@Autowired
	SysDictionaryMapper sysDictionaryMapper;

	@Autowired
	CacheService cacheService;

	@Override
	public List<SysDictionary> getByType(final String type) {
		Callable<String> callable = new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				SysDictionaryExample example = new SysDictionaryExample();
				example.createCriteria().andTypeEqualTo(type);
				List<SysDictionary> dicts = sysDictionaryMapper.selectByExample(example);
				if (CollectionUtils.isEmpty(dicts)) {
					return null;
				}
				return JsonUtil.toJson(dicts);
			}
		};

		// 先从缓存查询数据，如果查询不到，会调用Callable的call方法
		@SuppressWarnings("unchecked")
		List<SysDictionary> list = JsonUtil.
				toBean(cacheService.get(CacheService.KEY_DICTIONARY_TYPE_LEVEL, callable, CacheService.EXPIRY_ONE_DAY * 15),
						ArrayList.class, SysDictionary.class);
		
		return list;
	}

}
