package com.fun.service;

import com.fun.model.*;
import com.fun.repository.*;
import com.fun.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private CartService cartService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Order createOrder(OrderRequest order, User user) throws Exception {
        Address shipAddress = order.getDeliveryAddress();
        Address savedAddress = addressRepository.save(shipAddress);
        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepository.save(user);
        }
        Optional<Restaurant> resturant = restaurantRepository.findById(order.getRestaurantId());
        if(resturant.isEmpty()) {
            throw new Exception("Restaurant not found with id "+order.getRestaurantId());
        }
        Order createdOrder =new Order();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(resturant.get());

        Cart cart = cartService.findCartByUserId(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem cartItem : cart.getItem()){
            OrderItem item = new OrderItem();
            item.setFood(cartItem.getFood());
            item.setQuantity(cartItem.getQuantity());
            item.setIngredients(cartItem.getIngredients());
            item.setTotalPrice(cartItem.getTotalPrice());
            OrderItem savedOrderItem = orderItemRepository.save(item);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotals(cart);
        createdOrder.setItems(orderItems);
        createdOrder.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(createdOrder);
        resturant.get().getOrders().add(savedOrder);
        restaurantRepository.save(resturant.get());
        return createdOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws Exception {
        Order order = findOrderById(orderId);
        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED")
        || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);
            return orderRepository.save(order);
        }
        throw new Exception("please select a valid order status");
    }

    @Override
    public void cancelOrder(Long orderId) throws Exception {
        Order order = findOrderById(orderId);
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);
    }

    @Override
    public List<Order> getResturantsOrder(Long resturantId, String orderStatus) throws Exception {
        List<Order> orders = orderRepository.findByRestaurantId(resturantId);
        if(orderStatus!=null){
            orders = orders.stream().filter(order-> order.getOrderStatus().equals(orderStatus))
                    .collect(Collectors.toList());
        }
        return orders;
    }
    @Override
    public Order findOrderById(Long orderId) throws Exception {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new Exception("Order not found with id "+orderId);
        }
        return optionalOrder.get();
    }
}
