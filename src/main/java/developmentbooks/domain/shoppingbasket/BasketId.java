package developmentbooks.domain.shoppingbasket;

import jakarta.annotation.Nullable;

import java.util.Objects;

public record BasketId(@Nullable Long value) {

    public BasketId {
        if (Objects.nonNull(value) && value <= 0L) {
            throw new IllegalArgumentException("BasketId value should be a positive number!");
        }
    }
}
