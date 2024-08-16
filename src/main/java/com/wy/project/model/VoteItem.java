package com.wy.project.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "VOTE_ITEM")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "getAllVoteItems", 
            procedureName = "GET_ALL_VOTE_ITEMS", 
            resultClasses = { VoteItem.class }
    ),
    @NamedStoredProcedureQuery(
            name = "saveVoteItem",
            procedureName = "SAVE_VOTE_ITEM",
            parameters = {
        	    @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_id", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class)
            }
    ),
    @NamedStoredProcedureQuery(
            name = "deleteVoteItem",
            procedureName = "DELETE_VOTE_ITEM"
    ),
    @NamedStoredProcedureQuery(
            name = "saveVoteAndUpdateCount",
            procedureName = "SAVE_VOTE_AND_UPDATE_COUNT"
    ),
})
public class VoteItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer voteCount;
}
