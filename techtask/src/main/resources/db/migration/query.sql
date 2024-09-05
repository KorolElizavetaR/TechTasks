Select * from orders;
SELECT * FROM users;

SELECT * from orders o
	JOIN users u ON u.id = o.user_id;

-- Возвращать самый новый заказ, в котором больше одного предмета.
SELECT * from orders o	WHERE quantity>1 ORDER BY created_at DESC LIMIT 1;

-- Возвращать пользователя с максимальной общей суммой товаров, доставленных в 2003.
SELECT u.* from orders o
	JOIN users u ON u.id = o.user_id
	WHERE EXTRACT(YEAR FROM created_at) = 2003 
	GROUP BY u.id, u.email, u.user_status
	ORDER BY SUM(o.quantity*o.price) DESC LIMIT 1;

-- Возвращать пользователей у которых есть оплаченные заказы в 2010.


-- Возвращать заказы от активных пользователей, отсортированные по дате создания.