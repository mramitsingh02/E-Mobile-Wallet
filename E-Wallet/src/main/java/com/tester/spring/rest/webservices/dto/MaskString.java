package com.tester.spring.rest.webservices.dto;

import lombok.SneakyThrows;

import java.util.Objects;

public class MaskString {
    private final byte[] charArray;
    private final int maskLastIndex;

    public MaskString(byte[] charArray) {
        this.charArray = charArray;
        this.maskLastIndex = (charArray.length / 4);
    }

    @SneakyThrows
    public MaskString(String value) {
        if (Objects.isNull(value) || "".equals(value)) {
            throw new IllegalArgumentException("Please provied the argument for masking.");
        }
        this.charArray = value.getBytes("UTF-8");
        this.maskLastIndex = value.length() / 4;
    }

    @SneakyThrows
    public MaskString(String value, int maskLastIndex) {
        if (Objects.isNull(value) || "".equals(value)) {
            throw new IllegalArgumentException("Please provied the argument for masking.");
        }
        this.charArray = value.getBytes("UTF-8");
        this.maskLastIndex = maskLastIndex;
    }

    @Override
    public String toString() {
        final char[] maskString = new String(this.charArray).toCharArray();
        StringBuilder sb = new StringBuilder();
        final int i1 = maskString.length - maskLastIndex;
        for (int i = 0; i < maskString.length; i++) {
            if (i < i1) {
                sb.append("x");
            } else {
                sb.append(maskString[i]);
            }

        }
        return sb.toString();
    }

    public String unMaskString() {
        return new String(this.charArray);
    }
}
