# Compiling from source
 
```bash
mvn clean install
```
# Requirements

- Requires Java 19 or higher.
- Environment variables `JWT_SECRET` and `RECAPTCHA_SECRET` must be set.
+ `JWT_SECRET` must be a 256-bit secret key, encoded in base64.
+ `RECAPTCHA_SECRET` must be a secret key for Google's reCAPTCHA v3.

# Building the jar file

```bash
mvn clean package 
```