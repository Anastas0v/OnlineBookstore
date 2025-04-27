package com.project.bookstore.service;

import com.project.bookstore.model.dto.request.RequestPurchaseDTO;
import com.project.bookstore.model.dto.response.ResponsePurchaseDTO;

public interface PurchaseService
{
    ResponsePurchaseDTO processPurchase(RequestPurchaseDTO requestPurchaseDTO);
}
