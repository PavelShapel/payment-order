package com.pavelshapel.aws.lambda.service.nbrb.service.api;

public interface ApiService<B, R> {
    R get(B ratedDto);
}
