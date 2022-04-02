package com.pavelshapel.aws.lambda.service.location;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.aws.lambda.service.location.model.LocationType;

public interface MockLocation {
    default Location getMockLocation(String id, Location parent, String name, LocationType locationType) {
        Location location = new Location();
        location.setId(id);
        location.setParent(parent);
        location.setName(name);
        location.setLocationType(locationType);
        return location;
    }
}