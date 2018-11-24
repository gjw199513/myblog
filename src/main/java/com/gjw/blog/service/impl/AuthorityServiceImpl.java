package com.gjw.blog.service.impl;

import com.gjw.blog.domain.Authority;
import com.gjw.blog.repository.AuthorityRepository;
import com.gjw.blog.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Authority服务接口实现
 * @author gjw19
 * @date 2018/11/24
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority getAuthorityById(Long id) {
		return authorityRepository.findOne(id);
	}
}
