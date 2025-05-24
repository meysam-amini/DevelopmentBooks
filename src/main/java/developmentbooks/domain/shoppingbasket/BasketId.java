package developmentbooks.domain.shoppingbasket;

import java.util.Objects;

public record BasketId(Long value) {

    public BasketId {
        if (Objects.nonNull(value) && value <= 0L) {
            throw new IllegalArgumentException("BasketId value should be a positive number!");
        }
    }
}
