import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        IntervalProcessing validScanner = null;

        // Work with interval scanner
        while (validScanner == null) {
            try {
                System.out.println("Enter the interval: ");
                String interval = scan.nextLine().trim();
                validScanner = new IntervalProcessing(interval);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }

        // Perform LR parsing and validate expression input
        LRParser parser = new LRParser();
        float result = 0;
        String expression = null;

        // Loop for expression scanner
        while (true) {
            System.out.println("Please enter the expression: ");
            try {
                expression = scan.nextLine().trim();
                result = (float) parser.evaluate(expression); // Cast result to float for interval check
                break; // Exit loop when a valid expression is provided
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + " Please try again.");
            }
        }

        // Check if the result is within the interval
        boolean isInInterval = IntervalProcessing.checkInterval(result, validScanner.getTokens());
        String checkInterval = isInInterval ? "Yes" : "No";

        System.out.println("Result of the expression: \"" + expression + "\" inside the interval is: " + checkInterval);
        scan.close();
    }
}
