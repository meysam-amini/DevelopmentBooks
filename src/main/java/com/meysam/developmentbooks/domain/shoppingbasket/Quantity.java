package com.meysam.developmentbooks.domain.shoppingbasket;

public record Quantity(int value) {

    public Quantity {
        if (value <= 0L) {
            throw new IllegalArgumentException("Quantity value should be a positive number!");
        }
    }
}
