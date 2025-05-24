package developmentbooks.domain.shoppingbasket;

import java.util.*;

public class ShoppingBasket {

    private final BasketId id;
    private final Set<BasketItem> items;

    public ShoppingBasket(BasketId id) {
        this(id, new HashSet<>());
    }

    public ShoppingBasket(BasketId id, Set<BasketItem> items) {
        this.id = id;
        this.items = items;
    }


    public BasketId getId() {
        return id;
    }

    public Set<BasketItem> getItems() {
        return Collections.unmodifiableSet(items);
    }

}
