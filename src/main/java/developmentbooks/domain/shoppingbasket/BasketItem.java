package developmentbooks.domain.shoppingbasket;

import developmentbooks.domain.book.Book;

public record BasketItem(Book book, Quantity quantity) {

    public BasketItem {

    }
}
