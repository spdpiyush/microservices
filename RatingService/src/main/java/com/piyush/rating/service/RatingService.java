package com.piyush.rating.service;

import com.piyush.rating.entities.Rating;

import java.util.List;

public interface RatingService {

    Rating createRating(Rating rating);

    List<Rating> getUserRatings(String userId);

    List<Rating> getHotelRating(String hotelId);

    List<Rating> getAllRating();
}
