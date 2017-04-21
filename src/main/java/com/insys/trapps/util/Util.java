package com.insys.trapps.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by areyna on 4/20/17.
 */
public class Util {

    public static String generatePassword(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = RandomStringUtils.random( 10, characters );
        return password;
    }

}
