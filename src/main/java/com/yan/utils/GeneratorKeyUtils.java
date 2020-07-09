package com.yan.utils;

import java.util.UUID;

public class GeneratorKeyUtils {

    public static String generatorKey(){
        UUID uuid = UUID.randomUUID();
        String value=uuid.toString().replace("-", "").toLowerCase(); ;
        return value;
    }
}
