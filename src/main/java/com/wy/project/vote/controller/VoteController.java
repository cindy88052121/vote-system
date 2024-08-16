package com.wy.project.vote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.wy.project.common.Result;
import com.wy.project.vote.bean.SaveVoteItemRequest;
import com.wy.project.vote.bean.VoteRequest;
import com.wy.project.vote.service.VoteService;

@RestController
@CrossOrigin
public class VoteController {

    @Autowired
    private VoteService voteService;

    /**
     * 查詢-全部投票項目
     * @return Result
     */
    @RequestMapping(value = "/voteItems", method = RequestMethod.GET)
    public Result findAll() {
        return voteService.findAll();
    }

    /**
     * 新增-投票項目
     * @param request
     * @return Result
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/voteItems", method = RequestMethod.POST)
    public Result insert(@RequestBody SaveVoteItemRequest request) throws JsonProcessingException {
        return voteService.save(request);
    }

    /**
     * 刪除-投票項目
     * @param id
     * @return Result
     */
    @RequestMapping(value = "/voteItems/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Integer id) {
        return voteService.delete(id);
    }

    /**
     * 投票
     * @param request
     * @return Result
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/voteItems/vote", method = RequestMethod.POST)
    public Result vote(@RequestBody VoteRequest request) throws JsonProcessingException {
        return voteService.vote(request);
    }
}
