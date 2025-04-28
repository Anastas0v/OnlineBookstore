package com.project.bookstore.model.dto.request;

public class BookDTO
{
    private String title;
    private Double basePrice;
    private String type;

    public BookDTO(String title, Double basePrice, String type)
    {
        this.title = title;
        this.basePrice = basePrice;
        this.type = type;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public Double getBasePrice()
    {
        return basePrice;
    }

    public void setBasePrice(Double basePrice)
    {
        this.basePrice = basePrice;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
