package com.wy.project.vote.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wy.project.common.Result;
import com.wy.project.model.VoteItem;
import com.wy.project.repository.VoteItemRepository;
import com.wy.project.vote.bean.SaveVoteItemRequest;
import com.wy.project.vote.bean.VoteRequest;

@Service
public class VoteService {

    @Autowired
    private VoteItemRepository voteItemRepository;

    /**
     * 查詢-全部投票項目
     * @return Result
     */
    @Transactional
    public Result findAll() {
        List<VoteItem> voteItems = voteItemRepository.getAllVoteItems();
        return Result.builder().code("OK").message("成功").data(voteItems).build();
    }

    /**
     * 儲存-投票項目
     * @param request
     * @return Result
     * @throws JsonProcessingException
     */
    public Result save(SaveVoteItemRequest request) throws JsonProcessingException {
        VoteItem voteItem = new VoteItem();
        voteItem.setId(request.getId());
        voteItem.setName(request.getName());

        Object object = voteItemRepository.saveVoteItem(voteItem);
        return Result.builder().code("OK").message("成功").data(object).build();
    }

    /**
     * 刪除-投票項目
     * @param id
     * @return Result
     */
    public Result delete(Integer id) {
        Integer code = voteItemRepository.deleteVoteItem(id);
        if (code == 0) {
        	return Result.builder().code("NG").message("刪除失敗").build();
        }
        return Result.builder().code("OK").message("成功").build();
    }

    /**
     * 投票
     * @param request
     * @return Result
     * @throws JsonProcessingException
     */
    public Result vote(VoteRequest request) throws JsonProcessingException {
        Integer code = voteItemRepository.saveVoteAndUpdateCount(request.getItemIds(), request.getVoterName());
        if (code == 0) {
        	return Result.builder().code("NG").message("投票失敗").build();
        }
        return Result.builder().code("OK").message("成功").build();
    }
}
