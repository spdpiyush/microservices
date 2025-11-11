package com.piyush.hotel.service;

import com.piyush.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    Hotel getHotel(String id);

    List<Hotel> getAllHotels();
}
