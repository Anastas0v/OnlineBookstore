package com.project.bookstore.web;

import com.project.bookstore.model.dto.request.RequestPurchaseDTO;
import com.project.bookstore.model.dto.response.ResponsePurchaseDTO;
import com.project.bookstore.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/purchase")
public class PurchaseAPIController
{
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<ResponsePurchaseDTO> purchase(@RequestBody RequestPurchaseDTO requestPurchaseDTO)
    {
        ResponsePurchaseDTO responsePurchaseDTO = getPurchaseService().processPurchase(requestPurchaseDTO);
        return ResponseEntity.ok(responsePurchaseDTO);
    }

    public PurchaseService getPurchaseService()
    {
        return purchaseService;
    }
}
