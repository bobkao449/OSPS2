package net.enjoy.springboot.registrationlogin.controller;

import net.enjoy.springboot.registrationlogin.entity.ParkingSpot;
import net.enjoy.springboot.registrationlogin.service.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ParkingSpotController {

    @Autowired
    private ParkingSpotService parkingSpotService;

    @GetMapping("/yuntechparkingSpots")
    @ResponseBody
    public ResponseEntity<List<ParkingSpot>> getAllParkingSpots() {
        int page = 0; // 第一页
        int size = 1000000000;
        Pageable pageable = PageRequest.of(page, size);
        List<ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots(pageable).getContent();
        return ResponseEntity.ok(parkingSpots);//只返回.json數據
    }

    @GetMapping("/parkingSpots")
    public String getAllParkingSpots(Model model, @RequestParam(defaultValue = "0") int page) {
        int size = 100;
        Pageable pageable = PageRequest.of(page, size);
        model.addAttribute("parkingSpots", parkingSpotService.getAllParkingSpots(pageable));
        return "parkingSpots"; // 返回Thymeleaf模板的名稱
    }

    @PostMapping("/updateStatus")
    public String updateStatus(@RequestParam int PS_ID, @RequestParam int status) {
        parkingSpotService.updateStatus(PS_ID, status);
        return "redirect:/parkingSpots"; // 重定向回 parkingSpots 页面
    }
}