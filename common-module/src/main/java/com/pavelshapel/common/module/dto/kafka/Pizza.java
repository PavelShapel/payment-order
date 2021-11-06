package com.pavelshapel.common.module.dto.kafka;

import lombok.Getter;

public enum Pizza {
    MARGHERITA(10),
    CAPRICCIOSA(20),
    FOUR_CHEESE(15);

    @Getter
    private final int cookingDuration;

    Pizza(int cookingDuration) {
        this.cookingDuration = cookingDuration;
    }
}
