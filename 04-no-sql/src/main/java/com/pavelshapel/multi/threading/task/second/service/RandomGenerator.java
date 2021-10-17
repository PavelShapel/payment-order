package com.pavelshapel.multi.threading.task.second.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomGenerator {
    public long getRandomLong() {
        return ThreadLocalRandom.current().nextLong(5);
    }

    public String getRandomString() {
        return RandomStringUtils.randomAlphanumeric(1, 10);
    }

    public Date getRandomDate() {
        Calendar min = new GregorianCalendar(1900, Calendar.JANUARY, 1);
        Calendar max = new GregorianCalendar(3000, Calendar.DECEMBER, 31);
        final long randomizedLong = ThreadLocalRandom.current().nextLong(
                min.getTimeInMillis(),
                max.getTimeInMillis()
        );
        return new Date(randomizedLong);
    }
}
