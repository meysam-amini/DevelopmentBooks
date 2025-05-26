package com.meysam.developmentbooks.infrastructure.config;

import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.FixedGroupSizeDiscount;
import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.GroupDiscount;
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

    /*
    * Here we make a chain, we sort rules based on size(5,4,3,...),
    * then we set next of 5->4 , set next of 4->3 , ... finally return 5.
    * */
    public GroupDiscount createStrategyChain() {
        List<DiscountProperties.GroupDiscountRule> rules = new ArrayList<>(properties.getDiscountGroups());

        rules.sort(Comparator.comparingInt(DiscountProperties.GroupDiscountRule::getSize).reversed());

        GroupDiscount head = null;
        GroupDiscount current = null;

        for (DiscountProperties.GroupDiscountRule rule : rules) {
            GroupDiscount next = new FixedGroupSizeDiscount(rule.getSize(), rule.getDiscount());
            if (head == null) {
                head = next;
            } else {
                current.setNext(next);
            }
            current = next;
        }

        return head;
    }

}
