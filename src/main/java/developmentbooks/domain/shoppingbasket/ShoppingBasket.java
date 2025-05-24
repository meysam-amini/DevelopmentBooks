package developmentbooks.domain.shoppingbasket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingBasket {

    private final BasketId id;
    private final List<BasketItem> items;

    public ShoppingBasket(BasketId id) {
        this(id, new ArrayList<>());
    }

    public ShoppingBasket(BasketId id, List<BasketItem> items) {
        this.id = id;
        this.items = items;
    }


    public BasketId getId() {
        return id;
    }

    public List<BasketItem> getItems() {
        return Collections.unmodifiableList(items);
    }

}
