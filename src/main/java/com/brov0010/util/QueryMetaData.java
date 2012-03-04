package com.brov0010.util;

/**
 * Maintains metadata most specifically for scrolling. 
 */
public class QueryMetaData {

	public enum SortDirection {
		ASC,DESC
	}
	
	private int start;
	private int limit;
	private String sort;
	private SortDirection dir;

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public SortDirection getDir() {
		return dir;
	}
	public void setDir(SortDirection dir) {
		this.dir = dir;
	}
}
