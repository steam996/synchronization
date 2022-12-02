package org.example;

import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new TreeMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String route = generateRoute("RLRFR", 100);
            Runnable logic = () -> {
                int count = 0;
                synchronized (sizeToFreq) {
                    for (char r : route.toCharArray()) {
                        if (r == 'R') {
                            count++;

                        }
                    }

                    if (sizeToFreq.containsKey(count)) {
                        sizeToFreq.put(count, sizeToFreq.get(count) + 1);
                    } else {
                        sizeToFreq.put(count, 1);
                    }
                }
            };
            new Thread(logic).start();
        }
        List<Integer> keyList = new ArrayList<>(sizeToFreq.keySet());
        int keyMaxValue = 0;
        int maxValue = 0;

        for (int key : keyList) {
            if (sizeToFreq.get(key) > maxValue) {
                maxValue = sizeToFreq.get(key);
                keyMaxValue = key;
            }
        }

        System.out.println("Самое частое количество повторений " + maxValue
                + " (встретилось " + keyMaxValue + " раз)");

        System.out.println("Другие размеры:");

        for (int key : keyList) {
            System.out.println(key + "  (" + sizeToFreq.get(key) + " раз)");
        }
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }
}