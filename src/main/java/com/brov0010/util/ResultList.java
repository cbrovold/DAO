package com.brov0010.util;

import java.util.ArrayList;

/**
 * Maintains a total count for a cached list in scrollable results. This way the extended {@link ArrayList} does
 * 	not need to contain all results.
 * 
 * Ex: A query result would return a total of 10,000 records. However, in order to scroll for efficiency, you only
 * 		want the list to contain 100 records, but you still need to know the metadata of the actual result and how 
 * 		many it contains.
 * 
 * @param <T>
 */
public class ResultList<T> extends ArrayList<T> {

	private static final long serialVersionUID = -4851832853614706304L;
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
