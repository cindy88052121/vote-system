package com.wy.project.vote.bean;

import java.util.List;

import lombok.Data;

@Data
public class VoteRequest {

	private List<Integer> itemIds;

	private String voterName;
}
