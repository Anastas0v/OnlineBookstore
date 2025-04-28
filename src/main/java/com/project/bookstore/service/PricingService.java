package com.project.bookstore.service;

import com.project.bookstore.model.Book;
import com.project.bookstore.model.Customer;

import java.util.List;

public interface PricingService
{
    /**
     * Calculates the total price for a list of books purchased by a customer,
     * applying discounts, loyalty points, and bundle offers where applicable.
     *
     * <p>Pricing rules:
     * <ul>
     *   <li>Old edition books get a 20% discount.</li>
     *   <li>If buying 3 or more books (bundle), an extra 5% discount is applied on old editions,
     *       and 10% discount on regular books.</li>
     *   <li>New releases have no discount, ever.</li>
     *   <li>If customer chooses to use loyalty points and has at least 10 points,
     *       one non-new-release book is made free (only once per purchase).</li>
     * </ul>
     *
     * <p>Customer loyalty points:
     * <ul>
     *   <li>If loyalty points are used for a free book, points are reset to 0.</li>
     *   <li>Otherwise, customer earns 1 loyalty point per book purchased.</li>
     * </ul>
     *
     * @param bookList the list of books being purchased
     * @param customer the customer making the purchase
     * @param useLoyaltyPoints true if customer wants to use loyalty points for a free book, false otherwise
     * @return the total calculated price after applying discounts and loyalty adjustments
     */
    double calculatePrice(List<Book> bookList, Customer customer, boolean useLoyaltyPoints);
}
