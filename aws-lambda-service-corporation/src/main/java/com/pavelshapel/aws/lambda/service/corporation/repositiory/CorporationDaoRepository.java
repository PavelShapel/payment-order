package com.pavelshapel.aws.lambda.service.corporation.repositiory;

import com.pavelshapel.aws.lambda.service.corporation.model.Corporation;
import com.pavelshapel.core.spring.boot.starter.api.repository.DaoRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface CorporationDaoRepository extends DaoRepository<String, Corporation> {
}