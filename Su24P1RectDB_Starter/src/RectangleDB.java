import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * < MUST add honor pledge here >
 */

/**
 * The class containing the main method, the entry point of the application. It
 * will take a command line file argument which include the commands to be read
 * and creates the appropriate BST object and outputs the correct results
 * to the console as specified in the file.
 *
 * @author CS Staff
 * @version 2024-5-22
 */
public class RectangleDB {

    /**
     * The entry point of the application.
     *
     * @param args
     *            The name of the command file passed in as a command line
     *            argument.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(
                "Invalid file. No filename in command line arguments");
            return;
        }

        Database rectDB = new Database();
        CommandProcessor cmder = new CommandProcessor(rectDB);

        // The following pseudocode walks through a possible design for an
        // entrypoint for your rectangledb

        // setup a file for the file containing the commands
        // Open the file and scan through it (your may need a try catch here
        // take the first command line argument and opens that file
        // create a scanner object
        // create a command processor object
        // read the entire file and processes the commands
        // line by line
        // determines if the file has more lines to read
        // close the scanner

        // catch the exception if the file cannot be found
        // and output the correct information to the console
    }
}
