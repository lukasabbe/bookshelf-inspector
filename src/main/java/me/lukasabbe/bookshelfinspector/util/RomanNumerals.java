package me.lukasabbe.bookshelfinspector.util;

import java.util.TreeMap;

//https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java
public class RomanNumerals {

    private final static TreeMap<Integer, String> map = new TreeMap<>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }

    public static String toRoman(int number){
        int key = map.floorKey(number);

        if(number == key){
            return map.get(number);
        }

        return map.get(key) + toRoman(number-key);
    }
}
