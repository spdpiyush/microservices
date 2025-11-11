package com.piyush.user.external;

import com.piyush.user.models.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    @PostMapping("/ratings/create")
    ResponseEntity<Rating> createRating(Rating rating);
}
