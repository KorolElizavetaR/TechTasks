package com.example.techtask;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;

// Attention! It is FORBIDDEN to make any changes in this file!

@SpringBootApplication
public class RunApplication {

  public static void main(String[] args) {
    SpringApplication.run(RunApplication.class, args);
  }
  
}
