package com.herusantoso.tokonezia.util;

public class Constants {

    public static final class User {
        // Regex for acceptable logins
        public static final String USERNAME_REGEX = "^[_.@A-Za-z0-9-]*$";

        public static final String SYSTEM_ACCOUNT = "system";
        public static final String ANONYMOUS_USER = "anonymoususer";
        public static final String DEFAULT_LANGUAGE = "en";
    }

    public static final class PageParameter {
        public static final String LIST_DATA = "listData";
        public static final String TOTAL_PAGES = "totalPages";
        public static final String TOTAL_ELEMENTS = "totalElements";
    }

    public static final class Role {
        public static final String USER = "USER";
        public static final String ADMIN = "ADMIN";
    }

    public static final class PaymentMethode {
        public static final String VIRTUAL_ACCOUNT_BCA = "VA-BCA";
    }

    public static final class PaymentStatus {
        public static final String WAITING_FOR_PAYMENT = "Waiting for payment";
        public static final String ALREADY_PAID = "Already Paid";
        public static final String EXPIRED = "Expired";
    }

}
