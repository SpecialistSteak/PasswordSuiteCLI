package org.specialiststeak;

import java.security.SecureRandom;

import static org.specialiststeak.PgenCommand.*;

public class StringGenerator { //class to make the string. Actual command scrambles it, but this just makes sure specified chars are proper
    //instance variables
    private String mix = "";
    private String lowercase_letters = "qwertyuiopasdfghjklzxcvbnm";
    private String uppercase_letters = "QWERTYUIOOPASDFGHJKLZXCVBNM";
    private String numbers = "1234567890";
    private String symbols = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+";
    private int length;

    //secure random for random generation
    public static SecureRandom SR = new SecureRandom();

    //constructor to make basic data to pull from
    public StringGenerator(String exclusions) {
        char[] exclusions2;
        try {
            exclusions2 = exclusions.toCharArray();
        } catch (Exception e) {
            exclusions2 = new char[0];
        }

        //char array exclusions makes each exclusion a char
        StringBuilder sb = new StringBuilder();
        lowercase_letters = "qwertyuiopasdfghjklzxcvbnm";
        uppercase_letters = "QWERTYUIOOPASDFGHJKLZXCVBNM";
        numbers = "1234567890";
        symbols = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+";
        length = (lengthInteger <= 0 ? 16 : lengthInteger); //check for length, set to 16 for default
        for (char c : exclusions2) {
            int index = lowercase_letters.indexOf(c);
            //remove all the exclusions from respective strings
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

        //append new strings (w/o exclusions) to 'mix', if limit exists do not append either
        if(!(excludedLower || upperCaseInteger == null)) {
            sb.append(uppercase_letters);
        }
        if(!(excludedUpper || lowerCaseInteger == null)){
            sb.append(lowercase_letters);
        }
        if(!(excludedNumber || numbersInteger == null)){
            sb.append(numbers);
        }
        if(!(excludedSymbol || symbolsInteger == null)){
            sb.append(symbols);
        }
        mix = sb.toString();
    }

    //getters, no need for setters
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

    //method to fill in the password based off of specifications of password object
    public static String populatePassword(int length, StringGenerator s1){
        StringBuilder sb = new StringBuilder(s1.getLength());
        for (int i = 0; i < s1.getLength(); i++) {
            //if minimums are present, account for them and fill them in accordingly
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

    //fill the rest of the password
    public static String generatePassword(StringGenerator s1){
        StringBuilder sb = new StringBuilder(populatePassword(s1.getLength(), s1));
        for(int i = 0; i < s1.getLength() - populatePassword(s1.getLength(), s1).length(); i++){
            sb.append(s1.getMix().charAt(SR.nextInt(s1.getMix().length())));
        }
        return sb.toString();
    }

    //scramble a string, randomly swapping letters
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