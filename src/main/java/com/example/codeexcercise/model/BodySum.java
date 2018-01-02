package com.example.codeexcercise.model;

import java.util.Arrays;

public class BodySum {
    private int[] numbers;
    private String text;
    private int sumResult;

    public BodySum() {
    }

    public BodySum(int[] numbers, String text) {
        this.numbers = numbers;
        this.text = text;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getSumResult() {
        return sumResult;
    }

    public void setSumResult(int sumResult) {
        this.sumResult = sumResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BodySum bodySum = (BodySum) o;
        return Arrays.equals(numbers, bodySum.numbers);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(numbers);
    }
}
