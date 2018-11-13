package com.petProject.productsStore.exceptions;

public class ProductServiceException extends  RuntimeException{
    public ProductServiceException(String s) {
        super(s);
    }

    public ProductServiceException() {
        super();
    }
}
