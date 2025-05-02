package com.university.pos.posbackend.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;

public class KeyGenerator {

    public static void main(String[] args) {
        // Generate a key for HS512 algorithm.  Key size must be >= 512 bits.
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // You can get the key as a string (Base64 encoded)
        String secretKeyString = io.jsonwebtoken.io.Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Generated Secret Key (Base64): " + secretKeyString);

        //  Use this secretKeyString in your Spring Boot application
    }

    public static Key generateKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS512);
    }
}
