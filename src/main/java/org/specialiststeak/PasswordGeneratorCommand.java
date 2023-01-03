package org.specialiststeak;

import picocli.CommandLine;
@CommandLine.Command(version = "0.1", mixinStandardHelpOptions = true, subcommands = {
    PgenCommand.class,
    ScrambleCommand.class
})
public class PasswordGeneratorCommand implements Runnable {
    public static void main( String[] args ) {
        new CommandLine(new PasswordGeneratorCommand()).execute(args);
    }

    public void run() {
        System.out.println("""
                 .----------------.  .----------------.\s
                | .--------------. || .--------------. |
                | |   ______     | || |    ______    | |
                | |  |_   __ \\   | || |  .' ___  |   | |
                | |    | |__) |  | || | / .'   \\_|   | |
                | |    |  ___/   | || | | |    ____  | |
                | |   _| |_      | || | \\ `.___]  _| | |
                | |  |_____|     | || |  `._____.'   | |
                | |              | || |              | |
                | '--------------' || '--------------' |
                 '----------------'  '----------------'\s
                Version 0.1      Author: SpecialistSteak
                Type -h or --help to get started! Enjoy!""");
    }

}

