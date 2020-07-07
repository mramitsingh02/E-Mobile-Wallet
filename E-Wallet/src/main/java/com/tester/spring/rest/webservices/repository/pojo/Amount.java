package com.tester.spring.rest.webservices.repository.pojo;

import com.tester.spring.rest.webservices.exception.InvalidAmountFound;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

@Getter
public final class Amount implements Serializable {
    private static final long serialVersionUID = 2330019623894757611L;
    private final BigDecimal value;

    public Amount(double doubleAmount) {
        value = new BigDecimal(doubleAmount);
    }

    public Amount(long longAmount) {
        value = new BigDecimal(longAmount);
    }

    public Amount(String stringAmount) {
        value = new BigDecimal(stringAmount);
    }

    public Amount(BigDecimal value) {
        this.value = value;
    }

    public static Amount of(double doubleAmount) {
        return new Amount(doubleAmount);
    }

    public static Amount of() {
        return new Amount(0);
    }

    public static Amount of(long longAmount) {
        return new Amount(longAmount);
    }

    public static Amount of(String stringAmount) {
        if (stringAmount == null) throw new IllegalArgumentException("Invalid argument.");


        return new Amount(stringAmount);
    }

    public static BigDecimal valueOf(long val) {
        return BigDecimal.valueOf(val);
    }

    private static Amount of(BigDecimal abs) {
        return new Amount(abs);
    }

    public BigDecimal add(Amount that) {
        return getValue().add(that.value);
    }

    public BigDecimal subtract(Amount that) {
        return getValue().subtract(that.value);
    }

    public BigDecimal multiply(Amount that) {
        return getValue().multiply(that.value);
    }

    public BigDecimal divide(Amount divisor) {
        return getValue().divide(divisor.value);
    }

    public BigDecimal pow(int n) {
        return getValue().pow(n);
    }

    public Amount abs() {
        return Amount.of(getValue().abs());
    }

    public Amount plus() {
        return Amount.of(getValue().plus());
    }

    public int signum() {
        return getValue().signum();
    }

    public Amount round(MathContext mc) {
        return Amount.of(getValue().round(mc));
    }

    public Amount min(Amount thatVal) {
        return Amount.of(getValue().min(thatVal.value));
    }

    public Amount max(Amount thatVal) {
        return Amount.of(getValue().min(thatVal.value));
    }

    @Override
    public String toString() {
        return getValue().toString();
    }

    public long longValue() {
        return getValue().longValue();
    }

    public long longValueExact() {
        return getValue().longValueExact();
    }

    public int intValue() {
        return getValue().intValue();
    }

    public int intValueExact() {
        return getValue().intValueExact();
    }

    public float floatValue() {
        return getValue().floatValue();
    }

    public double doubleValue() {
        return getValue().doubleValue();
    }


    public void validate() {
        if (this.value.doubleValue() <= 0) {
            throw new InvalidAmountFound("Amount should not be -ve.");
        }
    }
}
