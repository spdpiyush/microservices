package com.piyush.user.services;

import com.piyush.user.entities.User;
import com.piyush.user.exceptions.NotFoundException;
import com.piyush.user.models.Hotel;
import com.piyush.user.models.Rating;
import com.piyush.user.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public User saveUser(User user) {
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(NotFoundException::new);
        // get the Ratings from RATING SERVICE
        String url = "http://RATING-SERVICE/ratings/user/" + userId;
        Rating[] ratingsOfUser = restTemplate.getForObject(url, Rating[].class);

        List<Rating> ratings = Arrays.stream(ratingsOfUser).toList();
        log.info("ratings={}", ratings);

        List<Rating> ratingList = ratings.stream()
                        .map(rating -> {
                            ResponseEntity<Hotel> responseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/" + rating.getHotelId(), Hotel.class);
                            Hotel hotel = responseEntity.getBody();
                            rating.setHotel(hotel);
                            return rating;
                        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
