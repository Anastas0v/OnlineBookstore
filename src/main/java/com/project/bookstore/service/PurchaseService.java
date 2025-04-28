package com.project.bookstore.service;

import com.project.bookstore.model.dto.request.RequestPurchaseDTO;
import com.project.bookstore.model.dto.response.ResponsePurchaseDTO;

/**
 * Service interface for processing customer purchases.
 *
 * <p>Handles the full purchase flow, including:
 * <ul>
 *   <li>Validating customer and requested books</li>
 *   <li>Calculating total price with discounts and loyalty point usage</li>
 *   <li>Updating customer loyalty points after purchase</li>
 *   <li>Returning purchase details including total price, points earned, and points used</li>
 * </ul>
 */
public interface PurchaseService
{
    ResponsePurchaseDTO processPurchase(RequestPurchaseDTO requestPurchaseDTO);
}
