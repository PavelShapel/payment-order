package com.pavelshapel.service.location.controller;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchCriteria;
import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.web.spring.boot.starter.controller.AbstractJpaRestController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.pavelshapel.service.location.controller.LocationJpaRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class LocationJpaRestController extends AbstractJpaRestController<Location> {
    public static final String HOME_PATH = "/locations" + StringUtils.EMPTY;
    private final Converter<Location, Location> locationToDtoConverter;
    private final Converter<Location, Location> locationFromDtoConverter;

    @Autowired
    public LocationJpaRestController(JpaService<Location> locationJpaService,
                                     SearchSpecification<Location> locationSearchSpecification,
                                     Converter<Location, Location> locationToDtoConverter,
                                     Converter<Location, Location> locationFromDtoConverter) {
        super(locationJpaService, locationSearchSpecification);
        this.locationToDtoConverter = locationToDtoConverter;
        this.locationFromDtoConverter = locationFromDtoConverter;
    }


    @Override
    public ResponseEntity<Location> save(@RequestBody @Valid Location entity) {
        Location location = locationFromDtoConverter.convert(entity);
        return super.save(location);
    }

    @Override
    public ResponseEntity<Location> update(@PathVariable Long id, @RequestBody @Valid Location entity) {
        Location locationDto = locationFromDtoConverter
                .andThen(location -> super.update(id, location).getBody())
                .andThen(locationToDtoConverter)
                .convert(entity);
        return ResponseEntity.ok(locationDto);
    }

    @Override
    public ResponseEntity<Location> findById(@PathVariable Long id) {
        Location location = super.findById(id).getBody();
        Location locationDto = locationToDtoConverter.convert(location);
        return ResponseEntity.ok(locationDto);
    }

    @Override
    public ResponseEntity<List<Location>> findAll() {
        List<Location> locations = super.findAll().getBody();
        List<Location> locationsDto = locations.stream()
                .map(locationToDtoConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok(locationsDto);
    }

    @Override
    public ResponseEntity<Page<Location>> findAll(@PageableDefault Pageable pageable) {
        Page<Location> locations = super.findAll(pageable).getBody();
        Page<Location> locationsDto = locations.map(locationToDtoConverter::convert);
        return ResponseEntity.ok(locationsDto);
    }

    @Override
    public ResponseEntity<Page<Location>> findAll(@Valid SearchCriteria searchCriteria,@PageableDefault Pageable pageable) {
        Page<Location> locations = super.findAll(searchCriteria, pageable).getBody();
        Page<Location> locationsDto = locations.map(locationToDtoConverter::convert);
        return ResponseEntity.ok(locationsDto);
    }

    @Override
    public ResponseEntity<List<Location>> getParentage(@PathVariable Long id) {
        List<Location> locations = super.getParentage(id).getBody();
        List<Location> locationsDto = locations.stream()
                .map(locationToDtoConverter::convert)
                .collect(Collectors.toList());
        return ResponseEntity.ok(locationsDto);
    }
}
