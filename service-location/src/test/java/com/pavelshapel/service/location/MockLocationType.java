package com.pavelshapel.service.location;

import com.pavelshapel.service.location.model.LocationType;

public interface MockLocationType {
    default LocationType getMockLocationType(String name) {
        LocationType locationType = new LocationType();
        locationType.setName(name);
        return locationType;
    }
}
