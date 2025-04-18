package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    @Autowired
    private EventRepository eventRepository;

    //FIND ALL SORTED BY NAME; INSERT
    @Transactional(readOnly = true)
    public List<CityDTO> findAllSortedByName (){
        List<City> result = repository.findAll(Sort.by("name"));
        return result.stream().map(CityDTO::new).toList();
    }

    @Transactional
    public CityDTO insert (CityDTO dto){
        City city = new City();
        city.setName(dto.getName());
        city.getEvents().clear();
        for (EventDTO e : dto.getEvents()){
            Event event = eventRepository.getReferenceById(e.getId());
            city.getEvents().add(event);
        }
        city = repository.save(city);
        return new CityDTO(city);
    }

}
