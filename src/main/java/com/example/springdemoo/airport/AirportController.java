package com.example.springdemoo.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<Airport> getAirports() {
        return airportService.getAirports();
    }

    @PostMapping
    public void registerNewAirport(@RequestBody Airport airport) {
        airportService.addNewAirport(airport);
    }

    @DeleteMapping(path = "{airportId}")
    public void deleteAirport(@PathVariable("airportId") Long id) {
        airportService.deleteAirport(id);
    }

    @PutMapping(path = "{airportId}")
    public void updateAirport(
            @PathVariable("airportId") Long airportId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Long terminal,
            @RequestParam(required = false) Boolean active) {
        airportService.updateAirport(airportId, name, code, terminal, active);
    }
}
