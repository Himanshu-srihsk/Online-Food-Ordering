package com.fun.request;

import com.fun.model.Address;
import lombok.Data;

@Data
public class OrderRequest {
    private Address deliveryAddress;
    private Long resturantId;
}
