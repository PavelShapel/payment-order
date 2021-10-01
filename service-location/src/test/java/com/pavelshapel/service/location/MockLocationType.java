package com.pavelshapel.service.location;

import com.pavelshapel.service.location.entity.LocationType;

public interface MockLocationType {
    default LocationType getMockLocationType(String name) {
        LocationType locationType = new LocationType();
        locationType.setName(name);
        return locationType;
    }
}
