package com.project.bookstore.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestPurchaseDTO
{
    private Long customerId;
    private List<Long> bookIdList;
}
