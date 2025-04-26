package com.project.bookstore.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO
{
    private String title;
    private Double basePrice;
    private String type;
}
