package org.example;

public class CalculatePrice {

    public double calculatePrice(double basePrice, int customerTypeInt, boolean isHoliday) {
        CustomerType customerType = CustomerType.fromInt(customerTypeInt);

        double customerDiscount = getCustomerDiscount(customerType);
        double holidayDiscount = isHoliday ? 0.05 : 0;

        double totalDiscount = customerDiscount + holidayDiscount;

        double finalPrice = basePrice * (1 - totalDiscount);

        return finalPrice;
    }

    private double getCustomerDiscount(CustomerType customerType) {
        return switch (customerType) {
            case REGULAR -> 0.10;
            case PREMIUM -> 0.15;
        };
    }
}
