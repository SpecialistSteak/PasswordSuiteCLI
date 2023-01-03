package org.specialiststeak;
import picocli.CommandLine;

import static org.specialiststeak.StringGenerator.*;

@CommandLine.Command(
        name = "pgen",
        mixinStandardHelpOptions = true
)
public class PgenCommand implements Runnable {
    @CommandLine.Option(names = {"-l", "--letters"}, description = "Set amount of letters in password.")
    private Integer lettersInteger;
    @CommandLine.Option(names = {"-U", "--uppercase"}, description = "Set amount of uppercase letters in string.")
    private Integer upperCaseInteger;
    @CommandLine.Option(names = {"-L", "--lowercase"}, description = "Set amount of lowercase letters in string.")
    private Integer lowerCaseInteger;
    @CommandLine.Option(names = {"-s", "--symbols"}, description = "Set amount of symbols in string.")
    private Integer symbolsInteger;
    @CommandLine.Option(names = {"-n", "--numbers"}, description = "Set amount of numbers in string.")
    private Integer numbersInteger;

    @CommandLine.Option(names = {"-e", "--exclude"}, description = "Excludes all specified characters.")
    static String excludedChars;
    @CommandLine.Option(names = {"-el", "--excludeLetters"}, description = "Excludes all letters.")
    static boolean excludedLetter;
    @CommandLine.Option(names = {"-eu", "--excludeUpper"}, description = "Excludes all uppercase letters.")
    static boolean excludedUpper;
    @CommandLine.Option(names = {"-eL", "--excludeLower"}, description = "Excludes all lowercase letters.")
    static boolean excludedLower;
    @CommandLine.Option(names = {"-en", "--excludeNumbers"}, description = "Excludes all excludes all numbers.")
    static boolean excludedNumber;
    @CommandLine.Option(names = {"-es", "--excludeSymbols"}, description = "Excludes all symbols.")
    static boolean excludedSymbol;

    @CommandLine.Option(names = {"-ml", "--minumumLetters"}, description = "Excludes all specified characters.")
    static Integer minumumLetter;
    @CommandLine.Option(names = {"-mu", "--minumumUpper"}, description = "Excludes all specified characters.")
    static Integer minumumUpper;
    @CommandLine.Option(names = {"-mL", "--minumumLower"}, description = "Excludes all specified characters.")
    static Integer minumumLower;
    @CommandLine.Option(names = {"-mn", "--minumumNumbers"}, description = "Excludes all specified characters.")
    static Integer minumumNumber;
    @CommandLine.Option(names = {"-ms", "--minumumSymbols"}, description = "Excludes all specified characters.")
    static Integer minumumSymbol;

    @CommandLine.Option(names = {"-len", "--length"}, description = "Set length of password.")
    private Integer lengthInteger; //check by adding minimums to ensure it's < max length

    @Override
    public void run(){
        int lengthInt = (lengthInteger == null ? 16 : lengthInteger);
        try {
            String exclusions = stringGen();
            // Check if there are no minimums or set amounts specified
            if (minumumLetter == null && minumumLower == null && minumumUpper == null && minumumNumber == null && minumumSymbol == null
                    && lettersInteger == null && upperCaseInteger == null && lowerCaseInteger == null && symbolsInteger == null && numbersInteger == null) {
                System.out.println(defRandomizerWithNoMinimumsOrSetAmounts(exclusions, lengthInt));
            } else {
                defRandomizerCheck(lettersInteger, upperCaseInteger, lowerCaseInteger, symbolsInteger,
                        numbersInteger, minumumLetter, minumumUpper, minumumLower, minumumNumber, minumumSymbol, lengthInteger, exclusions);
                System.out.println(defRandomizer(lettersInteger, upperCaseInteger, lowerCaseInteger, symbolsInteger,
                        numbersInteger, minumumLetter, minumumUpper, minumumLower, minumumNumber, minumumSymbol, lengthInteger, exclusions));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}