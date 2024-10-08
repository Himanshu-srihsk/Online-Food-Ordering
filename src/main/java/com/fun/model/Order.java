package com.fun.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  	private Long id;
	@ManyToOne
	private User  customer;

	//private Long restaurantId;
	@JsonIgnore
	@ManyToOne
	private Restaurant restaurant;
	private  Long totalAmount;

	private Date createdAt;
	@ManyToOne
	private Address deliveryAddress;
	@OneToMany
	private List<OrderItem> items = new ArrayList<>();

	private int totalItems;
	private  Long totalPrice;
	private String orderStatus;

	//private Payment payment;

}
