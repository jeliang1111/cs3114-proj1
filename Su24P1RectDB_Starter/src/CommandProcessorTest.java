import student.TestCase;

/**
 * This class tests the CommandProcessor class.
 * Test each possible command on its bounds,
 * if applicable to ensure they work properly.
 * Also test passing improper command to ensure
 * all class functionalities work as intended.
 * 
 * @author Justin Liang jeliang1111
 * @author Timothy Palamarchuk timka3
 * @version 2024-06-11
 */
public class CommandProcessorTest extends TestCase {
    private CommandProcessor processor;

    /**
     * The setUp() method will be called automatically before
     * each test and reset whatever the test modified. For this
     * test class, only a new database object is needed, so
     * creat a database here for use in each test case.
     */
    public void setUp() {
        Database rectDB = new Database();
        processor = new CommandProcessor(rectDB);
    }


    /**
     * Test the insert command.
     */
    public void testInsert() {
        // testing command
        String command = "insert A 1 2 3 4";
        processor.processor(command);
        assertFuzzyEquals("Rectangle accepted: (A, 1, 2, 3, 4)", systemOut()
            .getHistory());

        // Invalid inserts with negatives
        command = "insert A -1 2 3 4";
        processor.processor(command);
        String[] history = systemOut().getHistory().split("\n");
        String lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, -1, 2, 3, 4)", lastLine);

        command = "insert A 1 -2 3 4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, 1, -2, 3, 4)", lastLine);

        command = "insert A 1 2 -3 4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, 1, 2, -3, 4)", lastLine);

        command = "insert A 1 2 3 -4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, 1, 2, 3, -4)", lastLine);

        // Invalid inserts out of bounds
        command = "insert A 1 2 3 1024";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, 1, 2, 3, 1024)", lastLine);

        command = "insert A 1 2 1024 4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (A, 1, 2, 1024, 4)", lastLine);

        // Invalid name
        command = "insert 1 1 2 3 4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle rejected: (1, 1, 2, 3, 4)", lastLine);
    }


    /**
     * Test the remove command.
     */
    public void testRemove() {
        String command = "insert A 1 2 3 4";
        processor.processor(command);
        command = "remove A";
        processor.processor(command);
        String[] history = systemOut().getHistory().split("\n");
        String lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle removed: (A, 1, 2, 3, 4)", lastLine);

        // Invalid removes
        command = "remove A";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle not found: (A)", lastLine);

        command = "remove 1 2 3 4";
        processor.processor(command);
        history = systemOut().getHistory().split("\n");
        lastLine = history[history.length - 1];
        assertFuzzyEquals("Rectangle not found: (1, 2, 3, 4)", lastLine);
    }

}
