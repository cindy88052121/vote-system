package com.wy.project.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wy.project.model.VoteItem;

@Repository
public class VoteItemRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<VoteItem> getAllVoteItems() {
       StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("getAllVoteItems");
       storedProcedureQuery.execute();
       return storedProcedureQuery.getResultList();
    }

    public Object saveVoteItem(VoteItem voteItem) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SAVE_VOTE_ITEM");
        storedProcedureQuery.registerStoredProcedureParameter("v_id", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("v_name", String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter("v_id", voteItem.getId());
        storedProcedureQuery.setParameter("v_name", voteItem.getName());
        storedProcedureQuery.execute();
        return storedProcedureQuery.getSingleResult();
    }

    public Integer deleteVoteItem(Integer id) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("DELETE_VOTE_ITEM");
        storedProcedureQuery.registerStoredProcedureParameter("v_id", Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("v_code", Integer.class, ParameterMode.OUT);

        storedProcedureQuery.setParameter("v_id", id);
        storedProcedureQuery.execute();

        return (Integer) storedProcedureQuery.getOutputParameterValue("v_code");
    }

    public Integer saveVoteAndUpdateCount(List<Integer> itemIds, String voterName) throws JsonProcessingException {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("SAVE_VOTE_AND_UPDATE_COUNT");
        storedProcedureQuery.registerStoredProcedureParameter("v_itemIds", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("v_voterName", String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter("v_code", Integer.class, ParameterMode.OUT);

        ObjectMapper objectMapper = new ObjectMapper();
        String itemIdsJson = objectMapper.writeValueAsString(itemIds);

        storedProcedureQuery.setParameter("v_itemIds", itemIdsJson);
        storedProcedureQuery.setParameter("v_voterName", voterName);
        storedProcedureQuery.execute();

        return (Integer) storedProcedureQuery.getOutputParameterValue("v_code");
    }
}
