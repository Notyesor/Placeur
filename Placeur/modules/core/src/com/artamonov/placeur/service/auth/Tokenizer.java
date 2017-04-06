/*
 * TODO Copyright
 */

package com.artamonov.placeur.service.auth;

import java.util.concurrent.TimeUnit;

public class Tokenizer {
    private static final int TOKEN_LIFETIME_HOURS = 48;
    public static String createToken(String login, String password) {
        StringBuilder sb = new StringBuilder();
        sb.append((login + password.hashCode()).hashCode()).append("-").append(System.currentTimeMillis());
        return sb.toString();
    }
    public static boolean isActiveToken(String token) {
        long millis = Long.parseLong(token.split("-")[1]);
        return System.currentTimeMillis() - millis < TimeUnit.HOURS.toMillis(TOKEN_LIFETIME_HOURS);
    }
}
