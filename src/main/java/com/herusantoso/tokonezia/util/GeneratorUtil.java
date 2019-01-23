package com.herusantoso.tokonezia.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Random;

public class GeneratorUtil {

    public static Integer generateAmountCode() {
        Random random = new Random();
        // generate a random integer from 0 to 899, then add 100
        return random.nextInt(900) + 100;
    }

}
