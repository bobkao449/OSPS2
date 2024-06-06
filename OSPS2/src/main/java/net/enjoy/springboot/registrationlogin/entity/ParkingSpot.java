package net.enjoy.springboot.registrationlogin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "yuntech")
public class ParkingSpot {

    @Id
    @Column(name = "PS_ID")
    private int PS_ID;

    @Column(name = "Section_ID")
    private int Section_ID;

    @Column(name = "PS_type")
    private int PS_type;

    @Column(name = "PS_Lat")
    private double PS_Lat;

    @Column(name = "PS_Lng")
    private double PS_Lng;

    @Column(name = "status")
    private int  status;

}
