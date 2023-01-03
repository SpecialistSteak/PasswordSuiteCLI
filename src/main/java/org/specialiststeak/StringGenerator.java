package org.specialiststeak;

import java.security.SecureRandom;
import static org.specialiststeak.PgenCommand.*;

public class StringGenerator { //class to make the string. Actual command scrambles it, but this just makes sure specified chars are proper
    public static final String LETTERS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOOPASDFGHJKLZXCVBNM";
    public static final String LOWERCASE_LETTERS = "qwertyuiopasdfghjklzxcvbnm";
    public static final String UPPERCASE_LETTERS = "QWERTYUIOOPASDFGHJKLZXCVBNM";
    public static final String NUMBERS = "1234567890";
    public static final String SYMBOLS = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+";
    public static final String MIX = "`~!@#$%^&*()_+{}||:\"<>?,../;'[]\\//*-+1234567890QWERTYUIOOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm";
    static SecureRandom sr = new SecureRandom();
    static int totalLengthCheck = 0;
    static void defRandomizerCheck(Integer letters, Integer upperCase, Integer lowerCase, Integer symbols, Integer numbers,
                       Integer minLetters, Integer minUpper, Integer minLower, Integer minNumbers, Integer minSymbols,
                       Integer length, String exclusions) throws Exception {
        //prelim checks
        if (minLetters != null && (minLower != null || minUpper != null)) {
            throw new CustomException("Error: minimum letters cannot be chosen alongside minimum lowercase letters and minimum uppercase letters. Please specify one or the other and try again.");
        }
        if (letters != null && (lowerCase != null || upperCase != null)) {
            throw new CustomException("Error: amount of letters cannot be chosen alongside amount of lowercase letters and amount of uppercase letters. Please specify one or the other and try again.");
        }

        if (minLetters != null) {
            totalLengthCheck += minLetters;
        } else if (letters != null) {
            totalLengthCheck += letters;
        }

        if (minUpper != null) {
            totalLengthCheck += minUpper;
        } else if (upperCase != null) {
            totalLengthCheck += upperCase;
        }

        if (minLower != null) {
            totalLengthCheck += minLower;
        } else if (lowerCase != null) {
            totalLengthCheck += lowerCase;
        }

        if (minNumbers != null) {
            totalLengthCheck += minNumbers;
        } else if (numbers != null) {
            totalLengthCheck += numbers;
        }

        if (minSymbols != null) {
            totalLengthCheck += minSymbols;
        } else if (symbols != null) {
            totalLengthCheck += symbols;
        }

        if (totalLengthCheck > length) {
            throw new CustomException("Error: total length exceeds desired length.");
        }
    }
    static String defRandomizer(Integer letters, Integer upperCase, Integer lowerCase, Integer symbols, Integer numbers,
                             Integer minLetters, Integer minUpper, Integer minLower, Integer minNumbers, Integer minSymbols,
                             Integer length, String exclusions) throws Exception {

        StringBuilder sb = new StringBuilder(length);

        if(letters!= null){
            for(int i = 0; i < letters; i++) {
                if(exclusions.indexOf(LETTERS.charAt(sr.nextInt(LETTERS.length()))) < 0){
                    sb.append(LETTERS.charAt(sr.nextInt(LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }
        else if(minLetters!= null){
            for(int i = 0; i < minLetters; i++) {
                if(exclusions.indexOf(LETTERS.charAt(sr.nextInt(LETTERS.length()))) < 0){
                    sb.append(LETTERS.charAt(sr.nextInt(LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }

        if(upperCase!= null){
            for(int i = 0; i < upperCase; i++) {
                if(exclusions.indexOf(UPPERCASE_LETTERS.charAt(sr.nextInt(UPPERCASE_LETTERS.length()))) < 0){
                    sb.append(UPPERCASE_LETTERS.charAt(sr.nextInt(UPPERCASE_LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }
        else if(minUpper!= null){
            for(int i = 0; i < minUpper; i++) {
                if(exclusions.indexOf(UPPERCASE_LETTERS.charAt(sr.nextInt(UPPERCASE_LETTERS.length()))) < 0){
                    sb.append(UPPERCASE_LETTERS.charAt(sr.nextInt(UPPERCASE_LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }

        if(lowerCase!= null){
            for(int i = 0; i < lowerCase; i++) {
                if(exclusions.indexOf(LOWERCASE_LETTERS.charAt(sr.nextInt(LOWERCASE_LETTERS.length()))) < 0){
                    sb.append(LOWERCASE_LETTERS.charAt(sr.nextInt(LOWERCASE_LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }
        else if(minLower!= null){
            for(int i = 0; i < minLower; i++) {
                if(exclusions.indexOf(LOWERCASE_LETTERS.charAt(sr.nextInt(LOWERCASE_LETTERS.length()))) < 0){
                    sb.append(LOWERCASE_LETTERS.charAt(sr.nextInt(LOWERCASE_LETTERS.length())));
                }
                else{
                    i--;
                }
            }
        }

        if(numbers!= null){
            for(int i = 0; i < numbers; i++) {
                if(exclusions.indexOf(NUMBERS.charAt(sr.nextInt(NUMBERS.length()))) < 0){
                    sb.append(NUMBERS.charAt(sr.nextInt(NUMBERS.length())));
                }
                else{
                    i--;
                }
            }
        }
        else if(minNumbers!= null){
            for(int i = 0; i < minNumbers; i++) {
                if(exclusions.indexOf(NUMBERS.charAt(sr.nextInt(NUMBERS.length()))) < 0){
                    sb.append(NUMBERS.charAt(sr.nextInt(NUMBERS.length())));
                }
                else{
                    i--;
                }
            }
        }

        if(symbols!= null){
            for(int i = 0; i < symbols; i++) {
                if(exclusions.indexOf(SYMBOLS.charAt(sr.nextInt(SYMBOLS.length()))) < 0){
                    sb.append(SYMBOLS.charAt(sr.nextInt(SYMBOLS.length())));
                }
                else{
                    i--;
                }
            }
        }
        else if(minSymbols!= null){
            for(int i = 0; i < minSymbols; i++) {
                if(exclusions.indexOf(SYMBOLS.charAt(sr.nextInt(SYMBOLS.length()))) < 0){
                    sb.append(SYMBOLS.charAt(sr.nextInt(SYMBOLS.length())));
                }
                else{
                    i--;
                }
            }
        }

        for(int i = 0; i < (length - totalLengthCheck); i++){
            sb.append(exclusions.charAt(sr.nextInt(exclusions.length())));
        }

        return stringScrambler(sb.toString());
    }

    static String stringGen(){
        return (excludedSymbol ? SYMBOLS : "") +
                (excludedNumber ? NUMBERS : "") +
                (excludedLetter ? LETTERS : "") +
                (excludedLower ? LOWERCASE_LETTERS : "") +
                (excludedUpper ? UPPERCASE_LETTERS : "") +
                (excludedChars != null ? excludedChars : "");
    }
    static String defRandomizerWithNoMinimumsOrSetAmounts(String genString, int length){
        StringBuilder s1 = new StringBuilder(length);
        for(int i = 0; i < s1.length(); i++){
            s1.append(genString.charAt(sr.nextInt(genString.length())));
        }
        return s1.toString();
    }
    static String stringScrambler(String toScramble){
        char[] chars = toScramble.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int j = sr.nextInt(chars.length);
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
}
class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }
}