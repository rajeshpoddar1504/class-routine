package com.routine.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BatchBean {
	@JsonProperty("id")
	private int id;
	@JsonProperty("batch_abbr")
	private String batchAbbr;
	@JsonProperty("batch_desc")
	private String batchDesc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBatchAbbr() {
		return batchAbbr;
	}
	public void setBatchAbbr(String batchAbbr) {
		this.batchAbbr = batchAbbr;
	}
	public String getBatchDesc() {
		return batchDesc;
	}
	public void setBatchDesc(String batchDesc) {
		this.batchDesc = batchDesc;
	}
	public BatchBean(int id, String batchAbbr, String batchDesc) {
		super();
		this.id = id;
		this.batchAbbr = batchAbbr;
		this.batchDesc = batchDesc;
	}
	public BatchBean() {
		super();
	}
	
	
}
