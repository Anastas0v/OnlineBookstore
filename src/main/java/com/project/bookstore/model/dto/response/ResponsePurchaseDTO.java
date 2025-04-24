package com.project.bookstore.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponsePurchaseDTO
{
    private Double cost;
    private Integer loyaltyPointsEarned;
    private Integer loyaltyPointsUsed;
}
