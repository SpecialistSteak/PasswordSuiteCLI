package org.specialiststeak;

import java.security.SecureRandom;

import static org.specialiststeak.PgenCommand.*;

public class StringGenerator { //class to make the string. Actual command scrambles it, but this just makes sure specified chars are proper
    private String mix = "";
    private String lowercase_letters = "qwertyuiopasdfghjklzxcvbnm";
    private String uppercase_letters = "QWERTYUIOOPASDFGHJKLZXCVBNM";
    private String numbers = "1234567890";
    private String symbols = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+";
    private int length;
    public static SecureRandom SR = new SecureRandom();
    public StringGenerator(String exclusions) {
        char[] exclusions2;
        try {
            exclusions2 = exclusions.toCharArray();
        } catch (Exception e) {
            exclusions2 = new char[0];
        }
        StringBuilder sb = new StringBuilder();
        lowercase_letters = "qwertyuiopasdfghjklzxcvbnm";
        uppercase_letters = "QWERTYUIOOPASDFGHJKLZXCVBNM";
        numbers = "1234567890";
        symbols = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+";
        length = (lengthInteger <= 0 ? 16 : lengthInteger);
        for (char c : exclusions2) {
            int index = lowercase_letters.indexOf(c);
            if (index > -1) {
                lowercase_letters = lowercase_letters.substring(0, index) + lowercase_letters.substring(index + 1);
            }
            index = uppercase_letters.indexOf(c);
            if (index > -1) {
                uppercase_letters = uppercase_letters.substring(0, index) + uppercase_letters.substring(index + 1);
            }
            index = numbers.indexOf(c);
            if (index > -1) {
                numbers = numbers.substring(0, index) + numbers.substring(index + 1);
            }
            index = symbols.indexOf(c);
            if (index > -1) {
                symbols = symbols.substring(0, index) + symbols.substring(index + 1);
            }
        }
        sb.append(!excludedLower ? lowercase_letters : "")
                .append(!excludedUpper? uppercase_letters : "")
                .append(!excludedNumber ? numbers : "")
                .append(!excludedSymbol ? symbols : "");
        mix = sb.toString();
    }

    public String getMix() {
        return mix;
    }
    public String getLowercase_letters() {
        return lowercase_letters;
    }
    public int getLength() {
        return length;
    }
    public String getUppercase_letters() {
        return uppercase_letters;
    }
    public String getNumbers() {
        return numbers;
    }
    public String getSymbols() {
        return symbols;
    }
    public static String populatePassword(int length, StringGenerator s1){
        StringBuilder sb = new StringBuilder(s1.getLength());
        for (int i = 0; i < s1.getLength(); i++) {
            if (minumumUpper != 0) {
                sb.append(String.valueOf(s1.getUppercase_letters().charAt(SR.nextInt(s1.getUppercase_letters().length()))).repeat(Math.max(0, minumumUpper)));
            } else if (upperCaseInteger != null) {
                sb.append(String.valueOf(s1.getUppercase_letters().charAt(i)).repeat(Math.max(0, upperCaseInteger)));
            }
            if (minumumLower != 0) {
                sb.append(String.valueOf(s1.getLowercase_letters().charAt(i)).repeat(Math.max(0, minumumLower)));
            } else if (lowerCaseInteger != null) {
                sb.append(String.valueOf(s1.getLowercase_letters().charAt(i)).repeat(Math.max(0, lowerCaseInteger)));
            }
            if (minumumSymbol != 0) {
                sb.append(String.valueOf(s1.getSymbols().charAt(i)).repeat(Math.max(0, minumumSymbol)));
            } else if (symbolsInteger != null) {
                sb.append(String.valueOf(s1.getSymbols().charAt(i)).repeat(Math.max(0, symbolsInteger)));
            }
            if (minumumNumber != 0) {
                sb.append(String.valueOf(s1.getNumbers().charAt(i)).repeat(Math.max(0, minumumNumber)));
            } else if (numbersInteger != null) {
                sb.append(String.valueOf(s1.getNumbers().charAt(i)).repeat(Math.max(0, numbersInteger)));
            }
            if(includedChars != null){
                sb.append(includedChars);
            }
        }
        return sb.toString();
    }
    public static String generatePassword(StringGenerator s1){
        StringBuilder sb = new StringBuilder(populatePassword(s1.getLength(), s1));
        for(int i = 0; i < s1.getLength() - populatePassword(s1.getLength(), s1).length(); i++){
            sb.append(s1.getMix().charAt(SR.nextInt(s1.getMix().length())));
        }
        return sb.toString();
    }

    static String stringScrambler(String toScramble){
        char[] chars = toScramble.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = SR.nextInt(chars.length);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}