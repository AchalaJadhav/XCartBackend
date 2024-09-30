package com.innovation.xcartbackend.entity;

import javax.persistence.*;

@Entity
public class Feedback {

	@Id
	private int feedbackId;

	private int productId;

	private int userId;

	private int rating;

	private String reviewComment;

//	@ManyToOne
//	private Product product;
//	
//	@ManyToOne
//	private User user;

	public int getFeedbackId() {
		return feedbackId;
	}

	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

}
