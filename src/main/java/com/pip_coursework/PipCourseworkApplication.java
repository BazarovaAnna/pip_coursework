package com.pip_coursework;

import com.pip_coursework.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PipCourseworkApplication implements CommandLineRunner {
    @Autowired
    CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(PipCourseworkApplication.class, args);
    }

    @Override
    public void run(String... arg0) throws Exception {
        // clear all record if existed before do the tutorial with new data
        repository.deleteAll();
    }
}
