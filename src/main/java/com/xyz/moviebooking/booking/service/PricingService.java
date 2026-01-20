package com.xyz.moviebooking.booking.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PricingService {

    @Value("${app.discounts.third-ticket-discount}")
    private double thirdTicketDiscount;

    @Value("${app.discounts.afternoon-show-discount}")
    private double afternoonShowDiscount;

    public BigDecimal calculateTotalPrice(BigDecimal basePrice, int seatCount, boolean isAfternoonShow) {
        BigDecimal totalPrice = basePrice.multiply(BigDecimal.valueOf(seatCount));
        
        // Apply afternoon discount
        if (isAfternoonShow) {
            totalPrice = totalPrice.multiply(BigDecimal.valueOf(1 - afternoonShowDiscount));
        }
        
        // Apply third ticket discount (50% off third ticket only)
        if (seatCount >= 3) {
            BigDecimal thirdTicketPrice = basePrice;
            if (isAfternoonShow) {
                thirdTicketPrice = thirdTicketPrice.multiply(BigDecimal.valueOf(1 - afternoonShowDiscount));
            }
            BigDecimal discount = thirdTicketPrice.multiply(BigDecimal.valueOf(thirdTicketDiscount));
            totalPrice = totalPrice.subtract(discount);
        }
        
        return totalPrice;
    }
}