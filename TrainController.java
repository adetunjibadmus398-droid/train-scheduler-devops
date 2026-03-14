package com.train.scheduler.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @GetMapping
    public List<String> getTrains() {

        List<String> trains = new ArrayList<>();

        trains.add("Train A - Washington to New York");
        trains.add("Train B - Boston to Washington");
        trains.add("Train C - Chicago to Seattle");

        return trains;
    }
}
