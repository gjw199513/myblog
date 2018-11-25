package com.gjw.blog.service;

import com.gjw.blog.domain.Vote;

/**
 * Vote 服务接口.
 * 
 * @since 2018年11月25日
 * @author gjw199513
 */
public interface VoteService {
	/**
	 * 根据id获取 Vote
	 * @param id
	 * @return
	 */
	Vote getVoteById(Long id);
	/**
	 * 删除Vote
	 * @param id
	 * @return
	 */
	void removeVote(Long id);
}
