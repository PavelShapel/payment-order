package com.pavelshapel.service.location.controller;

import com.pavelshapel.jpa.spring.boot.starter.repository.search.SearchSpecification;
import com.pavelshapel.jpa.spring.boot.starter.service.jpa.JpaService;
import com.pavelshapel.service.location.entity.Location;
import com.pavelshapel.service.location.entity.LocationType;
import com.pavelshapel.web.spring.boot.starter.controller.AbstractJpaRestController;
import com.pavelshapel.web.spring.boot.starter.controller.converter.FromDtoConverter;
import com.pavelshapel.web.spring.boot.starter.controller.converter.ToDtoConverter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.pavelshapel.service.location.controller.LocationJpaRestController.HOME_PATH;


@RestController
@RequestMapping(HOME_PATH)
public class LocationJpaRestController extends AbstractJpaRestController<Location> {
    public static final String HOME_PATH = "/locations" + StringUtils.EMPTY;

    @Autowired
    private JpaService<LocationType> locationTypeJpaService;

    @Autowired
    public LocationJpaRestController(JpaService<Location> locationJpaService,
                                     SearchSpecification<Location> locationSearchSpecification,
                                     ToDtoConverter<Location> locationToDtoConverter,
                                     FromDtoConverter<Location> locationFromDtoConverter) {
        super(locationJpaService, locationSearchSpecification, locationFromDtoConverter, locationToDtoConverter);
    }

    @GetMapping({"/resolve" + ID_PATH})
    public ResponseEntity<String> getResolvedLocation(@RequestParam(required = false, defaultValue = "false") Boolean reverse, @PathVariable Long id) {
        String dto = Optional.ofNullable(getParentage(reverse, id).getBody()).orElse(Collections.emptyList())
                .stream().map(this::resolveLocation)
                .collect(Collectors.joining(","));
        return ResponseEntity.ok(dto);
    }

    private String resolveLocation(Location location) {
        Long locationTypeId = location.getLocationTypeId();
        LocationType locationType = locationTypeJpaService.findById(locationTypeId);
        String locationTypeName = locationType.getName();
        return String.format("%s %s", locationTypeName, location.getName());
    }
}
