package com.example.techtask.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Service
@Transactional (readOnly = true)
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	@PersistenceContext
	private final EntityManager session;
	
	@Override
	public Order findOrder() {
		return session.
				createQuery("FROM Order WHERE quantity > 1 ORDER BY createdAt DESC LIMIT 1", Order.class).
				getSingleResult();
	}

	@Override
	public List<Order> findOrders() {
		 List<Order> orders = 
				 session.createQuery("SELECT o FROM Order o JOIN User u ON u.id = o.userId WHERE u.userStatus = 'ACTIVE' ORDER BY createdAt", Order.class)
				 .getResultList();	
		return orders;
	}

}
