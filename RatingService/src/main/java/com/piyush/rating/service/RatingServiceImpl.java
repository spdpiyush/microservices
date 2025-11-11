package com.piyush.rating.service;

import com.piyush.rating.entities.Rating;
import com.piyush.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getUserRatings(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    @Override
    public List<Rating> getHotelRating(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    @Override
    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }
}
