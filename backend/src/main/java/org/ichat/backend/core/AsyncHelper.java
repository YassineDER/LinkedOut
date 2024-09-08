package org.ichat.backend.core;

import org.ichat.backend.exception.AccountException;
import org.springframework.http.HttpStatus;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

public class AsyncHelper {
    private static final ConcurrentHashMap<String, Long> lastEmailSent = new ConcurrentHashMap<>();

    public static void performEmailRateLimit(Runnable operation, String to) throws AccountException {
        long currentTime = System.currentTimeMillis();
        long twoMinuteMillis = 120 * 1000; // once per 2 minutes

        if (lastEmailSent.containsKey(to) && (currentTime - lastEmailSent.get(to)) < twoMinuteMillis)
            throw new AccountException("You can only send one email per 2 minutes", HttpStatus.TOO_MANY_REQUESTS.value());

        operation.run();
        lastEmailSent.put(to, currentTime);
    }
}