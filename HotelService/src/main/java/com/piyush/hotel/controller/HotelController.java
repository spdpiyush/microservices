package com.piyush.hotel.controller;

import com.piyush.hotel.entities.Hotel;
import com.piyush.hotel.service.HotelService;
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
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotelRequest) {
        Hotel hotel = hotelService.createHotel(hotelRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String id) {
        Hotel hotel = hotelService.getHotel(id);
        return ResponseEntity.ok(hotel);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }
}
