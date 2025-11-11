package com.piyush.hotel.service;

import com.piyush.hotel.entities.Hotel;
import com.piyush.hotel.exceptions.NotFoundException;
import com.piyush.hotel.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public Hotel createHotel(Hotel hotel) {
        hotel.setId(UUID.randomUUID().toString());
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {
        return hotelRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }
}
