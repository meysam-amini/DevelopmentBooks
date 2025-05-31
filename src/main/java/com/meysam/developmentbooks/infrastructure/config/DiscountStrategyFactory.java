//package com.meysam.developmentbooks.infrastructure.config;
//
//import com.meysam.developmentbooks.domain.book.Book;
//import com.meysam.developmentbooks.domain.shoppingbasket.basketpricecalculation.GroupDiscount;
//import com.meysam.developmentbooks.infrastructure.config.DiscountProperties;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Function;
//
//public class DiscountStrategyFactory {
//
//    private final DiscountProperties properties;
//
//    public DiscountStrategyFactory(DiscountProperties properties) {
//        this.properties = properties;
//    }
//
//    public Map<Integer, Function<List<Book>, Double>> createDiscountMap() {
//        Map<Integer, Function<List<Book>, Double>> map = new HashMap<>();
//
//        for (DiscountProperties.GroupDiscountRule rule : properties.getDiscountGroups()) {
//            int size = rule.getSize();
//            double discount = rule.getDiscount();
//            map.put(size, group -> group.size() * GroupDiscount.UNIT_PRICE * (1 - discount));
//        }
//
//        return map;
//    }
//}
