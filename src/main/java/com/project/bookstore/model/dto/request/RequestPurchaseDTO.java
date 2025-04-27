package com.project.bookstore.model.dto.request;

import java.util.List;

public class RequestPurchaseDTO
{
    private Long customerId;
    private List<Long> bookIdList;
    private boolean useLoyaltyPoints;

    public Long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(Long customerId)
    {
        this.customerId = customerId;
    }

    public List<Long> getBookIdList()
    {
        return bookIdList;
    }

    public void setBookIdList(List<Long> bookIdList)
    {
        this.bookIdList = bookIdList;
    }

    public boolean isUseLoyaltyPoints()
    {
        return useLoyaltyPoints;
    }

    public void setUseLoyaltyPoints(boolean useLoyaltyPoints)
    {
        this.useLoyaltyPoints = useLoyaltyPoints;
    }
}
