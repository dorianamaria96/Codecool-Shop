package com.codecool.shop.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.security.NoSuchAlgorithmException;

import java.security.spec.InvalidKeySpecException;


public class Security {

    public String hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }


    public boolean verifyPassword(String plainTextPassword, String hashedPassword){
        BCrypt.Result result = BCrypt.verifyer().verify(plainTextPassword.toCharArray(), hashedPassword.toCharArray());
        return result.verified;
    }


}
