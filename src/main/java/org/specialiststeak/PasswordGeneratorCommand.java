package org.specialiststeak;

import picocli.CommandLine;
@CommandLine.Command(version = "2.10", mixinStandardHelpOptions = true, subcommands = {
    PgenCommand.class,
    ScrambleCommand.class
})
public class PasswordGeneratorCommand implements Runnable {
    @CommandLine.Option(names = {"-ha", "--helpall"}, description = "Shows all help messages.")
    private boolean helpAll;
    public static void main( String[] args ) {
        new CommandLine(new PasswordGeneratorCommand()).execute(args);
    }
    public void run() {
        if(helpAll){
            System.out.println("""
                    pgen SUBCOMMAND
                        Usage: <main class> pgen [-hV] [-el] [-eL] [-en] [-es] [-eu]
                                                 [-e=<excludedChars>] [-i=<includedChars>]
                                                 [-L=<lowerCaseInteger>] [-len=<lengthInteger>]
                                                 [-mL=<minumumLower>] [-mn=<minumumNumber>]
                                                 [-ms=<minumumSymbol>] [-mu=<minumumUpper>]
                                                 [-n=<numbersInteger>] [-s=<symbolsInteger>]
                                                 [-U=<upperCaseInteger>]
                          -e, --exclude=<excludedChars>
                                                    Excludes all specified characters.
                              -el, --excludeLetters Excludes all letters.
                              -eL, --excludeLower   Excludes all lowercase letters.
                              -en, --excludeNumbers Excludes all excludes all numbers.
                              -es, --excludeSymbols Excludes all symbols.
                              -eu, --excludeUpper   Excludes all uppercase letters.
                          -h, --help                Show this help message and exit.
                          -i, --include=<includedChars>
                                                    Includes all specified characters.
                          -L, --lowercase=<lowerCaseInteger>
                                                    Set amount of lowercase letters in string.
                              -len, --length=<lengthInteger>
                                                    Set length of password.
                              -mL, --minumumLower=<minumumLower>
                                                    Excludes all specified characters.
                              -mn, --minumumNumbers=<minumumNumber>
                                                    Excludes all specified characters.
                              -ms, --minumumSymbols=<minumumSymbol>
                                                    Excludes all specified characters.
                              -mu, --minumumUpper=<minumumUpper>
                                                    Excludes all specified characters.
                          -n, --numbers=<numbersInteger>
                                                    Set amount of numbers in string.
                          -s, --symbols=<symbolsInteger>
                                                    Set amount of symbols in string.
                          -U, --uppercase=<upperCaseInteger>
                                                    Set amount of uppercase letters in string.
                          -V, --version             Print version information and exit.
                          
                    scramble SUBCOMMAND:
                        Usage: <main class> scramble [-hV] <stringToScramble>
                              <stringToScramble>
                          -h, --help               Show this help message and exit.
                          -V, --version            Print version information and exit.
                        
                    MAIN COMMAND:
                        Usage: <main class> [-hV] [-ha] [COMMAND]
                            -h, --help           Show this help message and exit.
                                -ha, --helpall   Shows all help messages.
                            -V, --version        Print version information and exit.
                          Commands:
                            pgen
                            scramble
                    """);
        }
        else{System.out.println("""
                 .----------------.  .----------------.  .----------------.  .-----------------.
                | .--------------. || .--------------. || .--------------. || .--------------. |
                | |   ______     | || |    ______    | || |  _________   | || | ____  _____  | |
                | |  |_   __ \\   | || |  .' ___  |   | || | |_   ___  |  | || ||_   \\|_   _| | |
                | |    | |__) |  | || | / .'   \\_|   | || |   | |_  \\_|  | || |  |   \\ | |   | |
                | |    |  ___/   | || | | |    ____  | || |   |  _|  _   | || |  | |\\ \\| |   | |
                | |   _| |_      | || | \\ `.___]  _| | || |  _| |___/ |  | || | _| |_\\   |_  | |
                | |  |_____|     | || |  `._____.'   | || | |_________|  | || ||_____|\\____| | |
                | |              | || |              | || |              | || |              | |
                | '--------------' || '--------------' || '--------------' || '--------------' |
                 '----------------'  '----------------'  '----------------'  '----------------'\s
                Version 2.10                                            Author: SpecialistSteak\s
                Type -h (--help) or -ha (--helpall) to get started!""");}
    }

}