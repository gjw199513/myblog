package com.gjw.blog.service;

import com.gjw.blog.domain.Authority;

/**
 * Authority服务接口
 * @author gjw19
 * @date 2018/11/24
 */
public interface AuthorityService {
    /**
	 * 根据id获取 Authority
	 * @param id
	 * @return
	 */
	Authority getAuthorityById(Long id);
}
