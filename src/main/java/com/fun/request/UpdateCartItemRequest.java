package com.fun.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartItemid;
    private int Quantity;
}
