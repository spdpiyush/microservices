package com.piyush.hotel.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @Column(name = "hotel_id")
    private String id;

    @Column(name = "hotel_name")
    private String name;

    @Column(name = "hotel_location")
    private String location;

    @Column(name = "about")
    private String about;
}
