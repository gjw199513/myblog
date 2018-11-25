package com.gjw.blog.service.impl;

import com.gjw.blog.domain.Vote;
import com.gjw.blog.repository.VoteRepository;
import com.gjw.blog.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gjw19
 * @date 2018/11/25
 */
@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Override
    public Vote getVoteById(Long id) {
        return voteRepository.findOne(id);
    }

    @Override
    public void removeVote(Long id) {
        voteRepository.delete(id);
    }
}
