package com.example.techtask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import com.example.techtask.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private final EntityManager session;

	@Override
	// Возвращать пользователя с максимальной общей суммой товаров, доставленных в
	// 2003.
	public User findUser() {
		return session.createQuery(
				"SELECT u from User u JOIN u.orders o WHERE EXTRACT(YEAR FROM o.createdAt) = 2003 GROUP BY u.id, u.email, u.userStatus ORDER BY SUM(o.quantity*o.price) DESC LIMIT 1",
				User.class).getSingleResult();
	}

	// Возвращать пользователей у которых есть оплаченные заказы в 2010.
	@Override
	public List<User> findUsers() {
		List<User> users = session.createQuery(
				"SELECT u from User u JOIN u.orders o WHERE EXTRACT(YEAR FROM o.createdAt) = 2010 and o.orderStatus = 'PAID'",
				User.class).getResultList();
		return users;
	}

}
