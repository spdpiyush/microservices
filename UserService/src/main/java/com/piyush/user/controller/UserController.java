package com.piyush.user.controller;

import com.piyush.user.entities.User;
import com.piyush.user.services.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private int retryCount = 0;

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(user1);
    }

    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelRetry", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "getUserRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getUser(@PathVariable String userId) {
        log.info("Retry Count={}", retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }


    // fall back (ratingHotelFallback) method for circuitBreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
        log.info("ratingHotelFallback is called because service is down: ", ex.getMessage());
        User user =  User.builder()
                .userId("dummy001")
                .name("dummy")
                .email("dummy@dummy.com")
                .build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }
}
