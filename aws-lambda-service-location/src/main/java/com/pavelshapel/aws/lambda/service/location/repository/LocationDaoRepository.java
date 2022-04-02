package com.pavelshapel.aws.lambda.service.location.repository;

import com.pavelshapel.aws.lambda.service.location.model.Location;
import com.pavelshapel.core.spring.boot.starter.api.repository.DaoRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface LocationDaoRepository extends DaoRepository<String, Location> {
}