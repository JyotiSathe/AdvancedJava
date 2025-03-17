package com.siemens;

public class Main {
    public static void main(String[] args) {

        String name = "Jyoti Sathe";
        //functional interface runnable with lambda
        Runnable runnable = () -> {
            for (char ch : name.toCharArray()) {
                System.out.println(ch);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread = new Thread(runnable);

        thread.start();
    }
}