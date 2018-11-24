package com.gjw.blog.repository;

import com.gjw.blog.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户仓库.
 *
 * @since 1.0.0 2018年11月24日
 * @author gjw
 */
public interface UserRepository extends CrudRepository<User, Long>{
}
