package com.meysam.developmentbooks.common.constants;

public final class MessageConstants {

    private MessageConstants() {}

    public static final class BookMsg {
        private BookMsg() {}

        public static final String BOOK_CREATED_SUCCESSFULLY = "Book created successfully";
        public static final String BOOKS_FETCHED_SUCCESSFULLY = "Fetched books successfully";
        public static final String AUTHOR_CANNOT_BE_BLANK_OR_NULL = "Author cannot be blank or null";
        public static final String ALL_BOOK_PARAMS_REQUIRED = "All book parameters must be provided";
        public static final String BOOK_ID_NEGATIVE = "BookId can't be negative!";
        public static final String BOOK_ISBN_NULL_OR_BLANK = "BookIsbn can't be null or blank!";
        public static final String YEAR_TOO_EARLY = "Year cannot be before 1800";
        public static final String PUBLICATION_YEAR_NULL = "Publication year cannot be null";
        public static final String YEAR_IN_FUTURE = "Year cannot be in future";
        public static final String TITLE_CANNOT_BE_NULL_OR_BLANK = "Title cannot be null";
        public static final String TITLE_TOO_LONG = "Title exceeds 100 chars";
        public static final String BOOK_ALREADY_EXISTS_BY_ISBN = "Book already exists by ISBN";
        public static final String BOOK_IS_NULL = "Book object is null";
        public static final String CREATE_BOOK_COMMAND_IS_NULL = "CreateBookCommand object is null!";
    }

    public static final class ShoppingBasketMsg {
        private ShoppingBasketMsg() {}

        public static final String CALCULATE_PRICE_COMMAND_IS_NULL = "CalculateBasketPriceCommand object is null!";
        public static final String BASKET_ID_NOT_POSITIVE = "BasketId value should be a positive number!";
        public static final String QUANTITY_NOT_POSITIVE = "Quantity value should be a positive number!";
        public static final String BASKET_CONTAINS_NULLS = "ShoppingBasket object contains null BasketId or BasketItem!";
    }

    public static final class Web {
        private Web() {}
        public static final String SUCCESS = "OK";
        public static final String INTERNAL_SERVER_ERROR = "An unexpected error occurred";
        public static final String MALFORMED_OR_INVALID_REQUEST_BODY = "Malformed or invalid request body";
    }
}
