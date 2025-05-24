package developmentbooks.domain.shoppingbasket;

public record BasketId(Long value) {

    public BasketId {
        if (value <= 0L) {
            throw new IllegalArgumentException("BasketId value should be a positive number!");
        }
    }
}
