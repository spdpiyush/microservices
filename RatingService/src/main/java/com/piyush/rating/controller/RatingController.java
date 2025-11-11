package com.piyush.rating.controller;

import com.piyush.rating.entities.Rating;
import com.piyush.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody Rating requestRating) {
        Rating rating = ratingService.createRating(requestRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<Rating>> findRatingsByUserId(@PathVariable String userId) {
        List<Rating> ratings = ratingService.getUserRatings(userId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("hotel/{hotelId}")
    public ResponseEntity<List<Rating>> findRatingsByHotelId(@PathVariable String hotelId) {
        List<Rating> ratings = ratingService.getHotelRating(hotelId);
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rating>> findAllRatings() {
        List<Rating> ratings = ratingService.getAllRating();
        return ResponseEntity.ok(ratings);
    }
}
