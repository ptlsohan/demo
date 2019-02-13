package com.example.demo.dto;

import java.util.List;

public class CalcReport {

	
	private String siteId;
	private String expression;
	private String tagname;
	private List<String> tagVal;
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public List<String> getTagVal() {
		return tagVal;
	}
	public void setTagVal(List<String> tagVal) {
		this.tagVal = tagVal;
	}
	
	
}
