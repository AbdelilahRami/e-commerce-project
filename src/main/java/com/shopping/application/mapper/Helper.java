package com.shopping.application.mapper;

import com.shopping.application.exception.UuidConversionException;

import java.util.UUID;

public class Helper {


    public static  UUID manageUserUUIdConversion(String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return null;
        }
        return uuid;
    }

    public static UUID manageProductUUIdConversion(String id) {
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
