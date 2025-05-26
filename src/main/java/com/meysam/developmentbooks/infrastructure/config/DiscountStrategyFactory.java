package com.meysam.developmentbooks.infrastructure.config;

import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.FixedGroupSizeDiscount;
import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.GroupDiscount;
import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.OptimalGroupDiscount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class DiscountStrategyFactory {

    private final DiscountProperties properties;

    public DiscountStrategyFactory(DiscountProperties properties) {
        this.properties = properties;
    }


    public GroupDiscount createStrategyChain() {
        List<DiscountProperties.GroupDiscountRule> rules = new ArrayList<>(properties.getDiscountGroups());

        // Sort rules by grouping size(5, 4, 3...)
        rules.sort(Comparator.comparingInt(DiscountProperties.GroupDiscountRule::getSize).reversed());

        List<GroupDiscount> strategies = new ArrayList<>();
        for (DiscountProperties.GroupDiscountRule rule : rules) {
            strategies.add(new FixedGroupSizeDiscount(rule.getSize(), rule.getDiscount()));
        }

        return new OptimalGroupDiscount(strategies);
    }
}
