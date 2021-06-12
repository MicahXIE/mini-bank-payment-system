package com.micah.demo.commons.utils;

import java.util.UUID;

public final class IdGenerator {

    public static String generateLoanId() {
        return UUID.randomUUID().toString();
    }

    public static String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
