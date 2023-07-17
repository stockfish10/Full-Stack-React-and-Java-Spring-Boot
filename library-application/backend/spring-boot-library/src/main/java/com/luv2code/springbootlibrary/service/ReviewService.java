package com.luv2code.springbootlibrary.service;

import com.luv2code.springbootlibrary.entity.ReviewEntity;
import com.luv2code.springbootlibrary.repository.BookRepository;
import com.luv2code.springbootlibrary.repository.ReviewRepository;
import com.luv2code.springbootlibrary.requestmodels.ReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Transactional
public class ReviewService {


    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public void postReview(String userEmail, ReviewRequest reviewRequest) throws Exception {
        ReviewEntity validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, reviewRequest.getBookId());

        if (validateReview != null){
            throw new Exception("Review already created");
        }

        ReviewEntity review = new ReviewEntity();
        review.setBookId(reviewRequest.getBookId());
        review.setRating(review.getRating());
        review.setUserEmail(userEmail);

        if (reviewRequest.getReviewDescription().isPresent()){
            review.setReviewDescription(reviewRequest.getReviewDescription().map(
                    Object::toString
            ).orElse(null));
        }

        review.setDate(Date.valueOf(LocalDate.now()));

        reviewRepository.save(review);
    }

    public boolean userReviewListed(String userEmail, Long bookId) {
        ReviewEntity validateReview = reviewRepository.findByUserEmailAndBookId(userEmail, bookId);

        return validateReview != null;
    }

}
