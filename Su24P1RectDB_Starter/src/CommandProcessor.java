import java.util.regex.Pattern;

/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-11
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database rectDB;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param dataIn
     *            the database object to manipulate
     */
    public CommandProcessor(Database dataIn) {
        rectDB = dataIn;
    }


    /**
     * This method parses keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions.
     * These actions are performed on specified objects and include insert,
     * remove,
     * regionsearch, search, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        if (arr.length == 0) {
            return;
        }
        String command = arr[0]; // the command will be the first of these
                                 // elements
        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace

        // if there is unncessary white space in front, so 0 is null, skip
        if (arr[0].equals("")) {
            // delete the first element
            String[] temp = new String[arr.length - 1];
            for (int i = 0; i < temp.length; i++) {
                temp[i] = arr[i + 1];
            }
            arr = temp;
            command = arr[0];

        }
        if (command.equals("insert")) {
            if (arr.length != 6) {
                System.out.println("Invalid number of parameters.");
                return;
            }
            Rectangle rect = new Rectangle(Integer.parseInt(arr[2].trim()),
                Integer.parseInt(arr[3].trim()), Integer.parseInt(arr[4]
                    .trim()), Integer.parseInt(arr[5].trim()));
            rectDB.insert(new KVPair<String, Rectangle>(arr[1], rect));
        }
        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove")) {
            // checks the number of white space delimited strings in the line
            int numParam = arr.length - 1;
            if (numParam == 1) {
                // Calls remove by name
                rectDB.remove(arr[1]);
            }
            else if (numParam == 4) {
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                rectDB.remove(Integer.parseInt(arr[1].trim()), Integer.parseInt(
                    arr[2].trim()), Integer.parseInt(arr[3].trim()), Integer
                        .parseInt(arr[4].trim()));
            }

        }
        else if (command.equals("regionsearch")) {
            // calls the regionsearch method for a set of coordinates
            // the string integers in the line will be trimmed of whitespace
            rectDB.regionsearch(Integer.parseInt(arr[1].trim()), Integer
                .parseInt(arr[2].trim()), Integer.parseInt(arr[3].trim()),
                Integer.parseInt(arr[4].trim()));
        }
        else if (command.equals("intersections")) {
            // calls the intersections method, no parameters to be passed
            // (see the intersections JavaDoc in the Database class for more
            // information)
            rectDB.intersections();

        }
        else if (command.equals("search")) {
            // calls the search method for a name of object
            rectDB.search(arr[1]);
        }
        else if (command.equals("dump")) {
            // calls the dump method for the database, takes no parameters
            // (see the dump() JavaDoc in the Database class for more
            // information)
            rectDB.dump();
        }
        else {
            // the first white space delimited string in the line is not
            // one of the commands which can manipulate the database,
            // a message will be written to the console
            System.out.println("Unrecognized command.");
        }
    }

}
