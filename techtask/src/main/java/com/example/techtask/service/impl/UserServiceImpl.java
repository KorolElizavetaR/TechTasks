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
import lombok.RequiredArgsConstructor;

@Service
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	@Autowired 
	private final EntityManagerFactory em;
	
	@Override
	public User findUser() {
		// Возвращать пользователя с максимальной общей суммой товаров, доставленных в 2003.
		EntityManager session = em.createEntityManager();
		 session.getTransaction().begin();
		 User user = session.
				 createQuery("SELECT u from User u JOIN u.orders o WHERE EXTRACT(YEAR FROM o.createdAt) = 2003 GROUP BY u.id, u.email, u.userStatus ORDER BY SUM(o.quantity*o.price) DESC LIMIT 1", User.class)
				 .getSingleResult(); 
		session.getTransaction().commit();
		return user;
	}

	@Override
	public List<User> findUsers() {
		//  Возвращать пользователей у которых есть оплаченные заказы в 2010.
		EntityManager session = em.createEntityManager();
		 session.getTransaction().begin();
		 List<User> users = session.
				 createQuery("SELECT u from User u JOIN u.orders o WHERE EXTRACT(YEAR FROM o.createdAt) = 2010 and o.orderStatus = 'PAID'", User.class)
				 .getResultList(); 
		session.getTransaction().commit();
		return users;
	}

}
