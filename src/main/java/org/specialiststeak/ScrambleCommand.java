package org.specialiststeak;

import picocli.CommandLine;

import static org.specialiststeak.StringGenerator.stringScrambler;

@CommandLine.Command(
        name = "scramble",
        mixinStandardHelpOptions = true
)
public class ScrambleCommand implements Runnable {
    @CommandLine.Parameters
    private String stringToScramble;

    @Override
    public void run(){
        System.out.println(stringScrambler(stringToScramble));
    }
}