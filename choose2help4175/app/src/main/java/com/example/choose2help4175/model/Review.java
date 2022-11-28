package com.example.choose2help4175.model;

import java.util.Date;

public class Review {

    private String reviewId;
    private String reviewText;
    private String reviewAuthor;
    private String reviewDate;

    public Review(String reviewId, String reviewText, String reviewAuthor, String reviewDate) {
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.reviewAuthor = reviewAuthor;
        this.reviewDate = reviewDate;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getReviewAuthor() {
        return reviewAuthor;
    }

    public void setReviewAuthor(String reviewAuthor) {
        this.reviewAuthor = reviewAuthor;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}