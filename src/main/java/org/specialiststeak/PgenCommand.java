package org.specialiststeak;
import picocli.CommandLine;

import static org.specialiststeak.StringGenerator.*;

@CommandLine.Command(
        name = "pgen",
        mixinStandardHelpOptions = true
)
public class PgenCommand implements Runnable {
    @CommandLine.Option(names = {"-U", "--uppercase"}, description = "Set amount of uppercase letters in string.")
    static Integer upperCaseInteger;
    @CommandLine.Option(names = {"-L", "--lowercase"}, description = "Set amount of lowercase letters in string.")
    static Integer lowerCaseInteger;
    @CommandLine.Option(names = {"-s", "--symbols"}, description = "Set amount of symbols in string.")
    static Integer symbolsInteger;
    @CommandLine.Option(names = {"-n", "--numbers"}, description = "Set amount of numbers in string.")
    static Integer numbersInteger;

    @CommandLine.Option(names = {"-e", "--exclude"}, description = "Excludes all specified characters.")
    static String excludedChars;
    @CommandLine.Option(names = {"-i", "--include"}, description = "Includes all specified characters.")
    static String includedChars;
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

    @CommandLine.Option(names = {"-mu", "--minumumUpper"}, description = "Excludes all specified characters.")
    static int minumumUpper;
    @CommandLine.Option(names = {"-mL", "--minumumLower"}, description = "Excludes all specified characters.")
    static int minumumLower;
    @CommandLine.Option(names = {"-mn", "--minumumNumbers"}, description = "Excludes all specified characters.")
    static int minumumNumber;
    @CommandLine.Option(names = {"-ms", "--minumumSymbols"}, description = "Excludes all specified characters.")
    static int minumumSymbol;

    @CommandLine.Option(names = {"-len", "--length"}, description = "Set length of password.")
    static int lengthInteger;

    @Override
    public void run(){
        //for simplicity make excluded letter exclude upper and lower
        if(excludedLetter){
            excludedLower = true;
            excludedUpper = true;
        }

        //call on password generation commands
        StringGenerator s1 = new StringGenerator(excludedChars);
        System.out.println(stringScrambler(generatePassword(s1)));
    }
}