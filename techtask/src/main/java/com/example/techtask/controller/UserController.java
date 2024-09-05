package com.example.techtask.controller;

import com.example.techtask.model.User;
import com.example.techtask.service.OrderService;
import com.example.techtask.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Attention! Only DI and service interaction applicable in this class. Each controller method
 * should only contain a call to the corresponding service method
 */
@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

	@Autowired
	private final UserService userServ;

  @GetMapping("desired-user")
  public User findUser() {
    return userServ.findUser();
  }

  @GetMapping("desired-users")
  public List<User> findUsers() {
    return userServ.findUsers();
  }
}
