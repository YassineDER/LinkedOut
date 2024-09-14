package org.ichat.backend.core;

import org.ichat.backend.exception.AccountException;
import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class AsyncHelper {
    private static final ConcurrentHashMap<String, Long> lastEmailSent = new ConcurrentHashMap<>();

    public static void performEmailRateLimit(Runnable operation, String to) throws AccountException {
        long currentTime = System.currentTimeMillis();
        long twoMinuteMillis = 60 * 1000; // once per minute

        if (lastEmailSent.containsKey(to) && (currentTime - lastEmailSent.get(to)) < twoMinuteMillis)
            throw new AccountException("Vous ne pouvez envoyer qu'un seul e-mail toutes les minutes", HttpStatus.TOO_MANY_REQUESTS.value());

        operation.run();
        lastEmailSent.put(to, currentTime);
    }
}