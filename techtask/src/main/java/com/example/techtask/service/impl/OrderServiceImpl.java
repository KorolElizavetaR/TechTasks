package com.example.techtask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Service
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	@Autowired 
	private final EntityManagerFactory em;
	
	@Override
	public Order findOrder() {
		// Возвращать самый новый заказ, в котором больше одного предмета.
		 EntityManager session = em.createEntityManager();
		 session.getTransaction().begin();
		 Order order = 
				 session.createQuery("FROM Order WHERE quantity > 1 ORDER BY createdAt DESC LIMIT 1", Order.class)
				 .getSingleResult();
		session.getTransaction().commit();
		return order;
	}

	@Override
	public List<Order> findOrders() {
		// Возвращать заказы от активных пользователей, отсортированные по дате создания.
		EntityManager session = em.createEntityManager();
		 session.getTransaction().begin();
		 List<Order> orders = 
				 session.createQuery("SELECT o FROM Order o JOIN User u ON u.id = o.userId WHERE u.userStatus = 'ACTIVE' ORDER BY createdAt", Order.class)
				 .getResultList();	
		session.getTransaction().commit();
		return orders;
	}

}
