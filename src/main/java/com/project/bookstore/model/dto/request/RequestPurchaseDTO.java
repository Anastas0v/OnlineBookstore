package com.project.bookstore.model.dto.request;

import java.util.List;

public class RequestPurchaseDTO
{
    private Long customerId;
    private List<Long> bookIdList;

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
}
