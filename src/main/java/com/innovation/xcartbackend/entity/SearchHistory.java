package com.innovation.xcartbackend.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class SearchHistory {

	@Id
	private int searchId;

	private int userId;

	private String searchTag;

	private LocalDateTime searchDate;

//	@OneToOne
//	private User user;

	public int getSearchId() {
		return searchId;
	}

	public void setSearchId(int searchId) {
		this.searchId = searchId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSearchTag() {
		return searchTag;
	}

	public void setSearchTag(String searchTag) {
		this.searchTag = searchTag;
	}

	public LocalDateTime getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(LocalDateTime searchDate) {
		this.searchDate = searchDate;
	}

}
