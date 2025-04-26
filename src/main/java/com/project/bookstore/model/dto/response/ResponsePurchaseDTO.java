package com.project.bookstore.model.dto.response;

public class ResponsePurchaseDTO
{
    private Double cost;
    private Integer loyaltyPointsEarned;
    private Integer loyaltyPointsUsed;

    public ResponsePurchaseDTO(Double cost, Integer loyaltyPointsEarned, Integer loyaltyPointsUsed)
    {
        this.cost = cost;
        this.loyaltyPointsEarned = loyaltyPointsEarned;
        this.loyaltyPointsUsed = loyaltyPointsUsed;
    }

    public Double getCost()
    {
        return cost;
    }

    public void setCost(Double cost)
    {
        this.cost = cost;
    }

    public Integer getLoyaltyPointsEarned()
    {
        return loyaltyPointsEarned;
    }

    public void setLoyaltyPointsEarned(Integer loyaltyPointsEarned)
    {
        this.loyaltyPointsEarned = loyaltyPointsEarned;
    }

    public Integer getLoyaltyPointsUsed()
    {
        return loyaltyPointsUsed;
    }

    public void setLoyaltyPointsUsed(Integer loyaltyPointsUsed)
    {
        this.loyaltyPointsUsed = loyaltyPointsUsed;
    }
}
