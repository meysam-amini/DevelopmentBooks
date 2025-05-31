package com.meysam.developmentbooks.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix = "book-shopping-basket")
@Component
public class DiscountRulesConfig {

    private List<GroupDiscountRule> discountRules;

    public List<GroupDiscountRule> getDiscountRules() {
        return discountRules;
    }

    public void setDiscountRules(List<GroupDiscountRule> discountRules) {
        this.discountRules = discountRules;
    }

    public static class GroupDiscountRule {
        private int size;
        private double discount;

        public int getSize() { return size; }
        public void setSize(int size) { this.size = size; }

        public double getDiscount() { return discount; }
        public void setDiscount(double discount) { this.discount = discount; }
    }
}

