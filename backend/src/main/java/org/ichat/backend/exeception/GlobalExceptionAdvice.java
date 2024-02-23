package org.ichat.backend.exeception;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionAdvice {
    private final Environment env;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalErrors(Exception ex) {
        LogIfDev(ex);

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", ex.getMessage());

        return ResponseEntity.internalServerError().body(body);
    }

    private void LogIfDev(Exception ex) {
        if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev"))
            ex.printStackTrace();
    }

    @ResponseBody
    @ExceptionHandler(StorageException.class)
    public ResponseEntity<Object> handleStorageErrors(StorageException ex) {
        LogIfDev(ex);

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", ex.getMessage());
        body.put("cause", ex.getCause().getMessage());

        return ResponseEntity.internalServerError().body(body);
    }


}
