package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.ParkingSpot;
import net.enjoy.springboot.registrationlogin.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/parkingSpots")
    public String getAllParkingSpots(Model model) {
        List<ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots();
        model.addAttribute("parkingSpots", parkingSpots);
        return "parkingSpots"; // 这里返回的是模板的名称
    }
}
