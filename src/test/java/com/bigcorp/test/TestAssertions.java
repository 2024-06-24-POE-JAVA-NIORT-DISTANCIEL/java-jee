package com.bigcorp.test;

public class TestAssertions {
    public static void main(String[] args) {
        int value = 10;
        assert value > 15 : "La valeur doit être supérieure à 15";
        System.out.println("Les assertions sont activées");
    }
}

