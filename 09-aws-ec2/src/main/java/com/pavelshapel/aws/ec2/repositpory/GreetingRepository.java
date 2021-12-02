package com.pavelshapel.aws.ec2.repositpory;

import com.pavelshapel.aws.ec2.entity.Greeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends JpaRepository<Greeting, Long> {

}