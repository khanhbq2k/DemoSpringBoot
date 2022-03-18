package com.example.springdemoo.airport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    @Autowired
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<Airport> getAirports(){
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportDetail(Long airportId){
        if(!airportRepository.existsById(airportId)){
            throw new IllegalStateException("Airport with id " + airportId + " does not exist!");
        }
        return airportRepository.findById(airportId);
    }

    public void addNewAirport(Airport airport){
        Optional<Airport> airportOptional = airportRepository.findAirportByCode(airport.getCode());
        if(airportOptional.isPresent()){
            throw new IllegalStateException("Airport with code: " + airport.getCode() + " is existed!");
        }
        airportRepository.save(airport);
    }

    public void deleteAirport(Long airportId){
        if(!airportRepository.existsById(airportId)){
            throw new IllegalStateException("Airport with id " + airportId + " does not exist!");
        }
        airportRepository.deleteById(airportId);
    }

    @Transactional
    public void updateAirport(Long airportId, String name, String code, Long terminal, Boolean active){
        Airport airport = airportRepository.findById(airportId)
                .orElseThrow(() -> new IllegalStateException("Airport with " + airportId + " does not exist!"));

        if(name != null && name.length() > 0  && !Objects.equals(airport.getName(), name)){
            airport.setName(name);
        }

        if(code != null && code.length() > 0  && !Objects.equals(airport.getCode(), code)){
            Optional<Airport> airportOptional = airportRepository.findAirportByCode(code);
            if(airportOptional.isPresent()){
               throw new IllegalStateException("Airport with code: " + code + " is existed!");
            }
            airport.setName(code);
        }

        if(terminal != null && !Objects.equals(airport.getTerminal(), terminal)){
            airport.setTerminal(terminal);
        }

        if(active != null && !Objects.equals(airport.getActive(), active)){
            airport.setActive(active);
        }

    }

}
