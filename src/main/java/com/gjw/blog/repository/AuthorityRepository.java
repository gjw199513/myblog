package com.gjw.blog.repository;

import com.gjw.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库.
 *
 * @since 1.0.0 2018年11月24日
 * @author gjw199513
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
