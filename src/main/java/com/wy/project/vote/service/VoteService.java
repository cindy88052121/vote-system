package com.wy.project.vote.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
     */
    public Result save(SaveVoteItemRequest request) {
        VoteItem voteItem = new VoteItem();
        voteItem.setId(request.getId());
        voteItem.setName(request.getName());

        Object object = null;
        try {
            object = voteItemRepository.saveVoteItem(voteItem);
        } catch (Exception e) {
        	return Result.builder().code("NG").message("儲存失敗").build();
        }
        return Result.builder().code("OK").message("成功").data(object).build();
    }

    /**
     * 刪除-投票項目
     * @param id
     * @return Result
     */
    public Result delete(Integer id) {
        try {
            voteItemRepository.deleteVoteItem(id);
        } catch (Exception e) {
            return Result.builder().code("NG").message("刪除失敗").build();
        }
        return Result.builder().code("OK").message("成功").build();
    }

    /**
     * 投票
     * @param request
     * @return Result
     */
    public Result vote(VoteRequest request) {
    	Integer code = 0;
        try {
            code = voteItemRepository.saveVoteAndUpdateCount(request.getItemIds(), request.getVoterName());
        } catch (Exception e) {
            return Result.builder().code("NG").message("投票失敗").build();
        }
        if (code == 0) {
            return Result.builder().code("NG").message("投票失敗").build();
        }
        return Result.builder().code("OK").message("成功").build();
    }
}
