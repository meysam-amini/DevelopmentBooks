package developmentbooks.domain.shoppingbasket;

import developmentbooks.domain.book.Book;

public record BasketItem(Book book, Quantity quantity) {

    public BasketItem {
        if(book==null || quantity == null){
            throw new IllegalArgumentException("BasketItem should have a Book & Quantity object!");
        }
    }


}
