package com.shopping.application.exception;

import lombok.Getter;

@Getter
public class ProductCategoryNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    private  String message= "PRODUCT_CATEGORY_NOT_FOUND";

    @Override public String getMessage() {
        return message;
    }
}
