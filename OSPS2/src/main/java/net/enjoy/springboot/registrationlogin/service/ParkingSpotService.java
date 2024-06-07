package net.enjoy.springboot.registrationlogin.service;

import net.enjoy.springboot.registrationlogin.entity.ParkingSpot;
import net.enjoy.springboot.registrationlogin.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    public Page<ParkingSpot> getAllParkingSpots(Pageable pageable) {
        return parkingSpotRepository.findAll(pageable);
    }
    public void updateStatus(int PS_ID, int status) {
        Optional<ParkingSpot> optionalSpot = parkingSpotRepository.findById(PS_ID);
        if (optionalSpot.isPresent()) {
            ParkingSpot spot = optionalSpot.get();
            spot.setStatus(status);
            parkingSpotRepository.save(spot);
        }
    }
}
