package com.project.bookstore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoyaltyPoints
{
    private Long customerId;
    private Integer loyaltyPoints;
}
