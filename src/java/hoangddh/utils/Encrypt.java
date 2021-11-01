/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangddh.utils;

import java.io.Serializable;
import java.security.MessageDigest;
/**
 *
 * @author tanta
 */
public class Encrypt implements Serializable {

    public static String toHexString(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(str.getBytes());

        String result = "";

        for (byte b : hash) {

            int value = 0xFF & b;
            String toAppend = Integer.toHexString(value);
            if (toAppend.length() == 1) {
                toAppend = "0" + toAppend;
            }
            result += toAppend;
        }

        return result.toString().toUpperCase();
    }
    
}
