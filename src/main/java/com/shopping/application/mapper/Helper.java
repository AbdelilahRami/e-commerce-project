package com.shopping.application.mapper;

import com.shopping.application.exception.UuidConversionException;

import java.util.UUID;

public class Helper {

    /*This method is used for product creation. we want to create the product whether the user is provided or not*/
    public static  UUID manageUserUUIdConversion(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return null;
        }
        return uuid;
    }

    /*This method is used for product creation and product update. we throw exception in case of unformatted uuid */
    public static UUID manageUUIDConversion(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new UuidConversionException();
        } catch (NullPointerException e) {
            return null;
        }
        return uuid;
    }
}
