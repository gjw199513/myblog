package com.gjw.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gjw.blog.domain.Vote;

/**
 * Vote 仓库.
 *
 * @since 2018年11月25日
 * @author gjw199513
 */
public interface VoteRepository extends JpaRepository<Vote, Long>{
 
}
