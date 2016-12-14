package com.insys.trapps.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by vnalitkin on 11/28/2016.
 */
@Slf4j
public class Utils {
    public static Long getId(String url){
        String[] urlPars = url.split("/");
        log.debug("Id= " + Long.parseLong(urlPars[urlPars.length-1]));
        return Long.parseLong(urlPars[urlPars.length-1]);
    }

}
