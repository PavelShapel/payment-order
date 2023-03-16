package com.pavelshapel.service.location;

import com.pavelshapel.service.location.model.Location;

public interface MockLocation {
    default Location getMockLocation(String name) {
        Location location = new Location();
        location.setName(name);
        return location;
    }
}
