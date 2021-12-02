package com.pavelshapel.aws.ec2.web;

import com.pavelshapel.aws.ec2.entity.Greeting;
import com.pavelshapel.aws.ec2.repositpory.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class GreetingController {
    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping
    private ResponseEntity<List<Greeting>> findAll(){
        return ResponseEntity.ok(greetingRepository.findAll());
    }
}
