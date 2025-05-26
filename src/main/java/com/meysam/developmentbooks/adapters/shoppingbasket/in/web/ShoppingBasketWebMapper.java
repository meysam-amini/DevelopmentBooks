package com.meysam.developmentbooks.adapters.shoppingbasket.in.web;

import com.meysam.developmentbooks.application.shoppingbasket.ports.in.command.dto.BookQuantityDto;
import com.meysam.developmentbooks.domain.book.*;
import com.meysam.developmentbooks.domain.shoppingbasket.Quantity;
import com.meysam.developmentbooks.domain.shoppingbasket.ShoppingBasket;
import com.meysam.developmentbooks.infrastructure.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.meysam.developmentbooks.common.constants.MessageConstants.ShoppingBasketMsg.CALCULATE_PRICE_COMMAND_IS_NULL;

@Mapper
public class ShoppingBasketWebMapper {

    public ShoppingBasket toDomain(List<BookQuantityDto> command) {
        if (command == null)
            throw new IllegalArgumentException(CALCULATE_PRICE_COMMAND_IS_NULL);

        HashMap<Book, Quantity> domainItems = (HashMap<Book, Quantity>) command.stream()
                .collect(Collectors.toMap(
                        e -> new Book(new BookId(e.id()), new BookIsbn(e.isbn()), new Title(e.title()), new Author(e.author()), new PublicationYear(e.publicationYear())),
                        e -> new Quantity(e.quantity())));

        return new ShoppingBasket(null, domainItems);
    }

}
