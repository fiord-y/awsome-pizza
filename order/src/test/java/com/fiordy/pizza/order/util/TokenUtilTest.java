package com.fiordy.pizza.order.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.NoSuchAlgorithmException;


@ExtendWith(MockitoExtension.class)
class TokenUtilTest {

    @Test
    void generateToken() {
        var token = TokenUtil.generateToken();
        Assertions.assertNotNull(token);
    }

    @Test
    void hashToken() throws NoSuchAlgorithmException {
        var token = TokenUtil.generateToken();
        var hashed = TokenUtil.hashToken(token);
        Assertions.assertNotNull(hashed);
    }

    @Test
    void verifyToken() throws NoSuchAlgorithmException {
        var token = TokenUtil.generateToken();
        var hashed = TokenUtil.hashToken(token);
        var verified = TokenUtil.verifyToken(token, hashed);
        Assertions.assertTrue(verified);
    }
}