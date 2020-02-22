package com.forever17.project.charityquest.utils;

import com.forever17.project.charityquest.constants.CharityConstants;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * MD5 util
 *
 * @author MingLiu (MLiu54@sheffield.ac.uk)
 * @version 1.0
 * @date 15 Feb 2020
 * @since 1.0
 */
public class MD5Util {
    private static MessageDigest md;

    /**
     * generate md5 code from input string
     *
     * @param input input string
     * @return md5 code which converted from input string
     * @throws NoSuchAlgorithmException exception
     */
    public static String md5(String input) throws NoSuchAlgorithmException {
        if (Objects.isNull(md)) {
            md = MessageDigest.getInstance(CharityConstants.MD5);
        }
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        StringBuilder hashText = new StringBuilder(no.toString(16));
        while (hashText.length() < 32) {
            hashText.insert(0, "0");
        }
        return hashText.toString();
    }
}
