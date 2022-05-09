package com.pavelshapel.aws.lambda.service.nbrb.repositiory;

import com.pavelshapel.aws.lambda.service.nbrb.model.Nbrb;
import com.pavelshapel.core.spring.boot.starter.api.repository.DaoRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.stereotype.Repository;

@Repository
@EnableScan
public interface NbrbDaoRepository extends DaoRepository<String, Nbrb> {
}