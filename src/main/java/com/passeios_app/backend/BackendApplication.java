package com.passeios_app.backend;

import java.security.KeyStore.SecretKeyEntry;
import java.security.spec.EncodedKeySpec;

import javax.crypto.SecretKey;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Encoders;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
//		SecretKey key = Jwts.SIG.HS256.key().build();
//		String secret = Encoders.BASE64.encode(key.getEncoded());
//		System.out.println(secret);
	}

}
