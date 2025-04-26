package com.project.bookstore.model.dto.response;

public class LoyaltyPointsDTO
{
    private Long customerId;
    private Integer loyaltyPoints;

    public LoyaltyPointsDTO(Long customerId, Integer loyaltyPoints)
    {
        this.customerId = customerId;
        this.loyaltyPoints = loyaltyPoints;
    }

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public Integer getLoyaltyPoints()
    {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(Integer loyaltyPoints)
    {
        this.loyaltyPoints = loyaltyPoints;
    }
}
