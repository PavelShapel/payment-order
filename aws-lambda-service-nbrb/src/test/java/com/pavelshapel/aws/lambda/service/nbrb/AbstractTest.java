package com.pavelshapel.aws.lambda.service.nbrb;

import com.pavelshapel.common.module.dto.aws.NbrbDto;
import com.pavelshapel.common.module.dto.aws.RatedDto;

import java.time.LocalDate;

public abstract class AbstractTest {
    public static final String USD = "USD";
    public static final double DEFAULT_AMOUNT = 2;
    public static final double DEFAULT_RATE = 2.5493;

    protected RatedDto createTestRatedDto() {
        RatedDto ratedDto = new RatedDto();
        ratedDto.setAbbreviation(USD);
        ratedDto.setAmount(DEFAULT_AMOUNT);
        return ratedDto;
    }

    protected NbrbDto createTestNbrbDto() {
        NbrbDto nbrbDto = new NbrbDto();
        nbrbDto.setDate(LocalDate.now());
        nbrbDto.setAbbreviation(USD);
        nbrbDto.setScale(1);
        nbrbDto.setRate(DEFAULT_RATE);
        return nbrbDto;
    }
}
