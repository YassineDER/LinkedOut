# Compiling from source
 
```bash
mvn clean install
```
# Requirements

- Requires Java 19 or higher.
- Environment variables must be set:
+ `JWT_SECRET` must be a 256-bit secret key, encoded in base64 for JWT Authentication.
+ `RECAPTCHA_SECRET` must be a secret key for Google's reCAPTCHA v3.
+ `DB_USER` and `DB_PASS` must be the username and password for the PostgreSQL database.
+ `MAIL_USER` and `MAIL_PASS` must be the username and password for the email account used to send emails.

# Building the jar file

```bash
mvn clean package 
```