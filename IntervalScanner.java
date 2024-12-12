import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**


public class IntervalScanner {

    // Regular expression to match intervals (either with parentheses or brackets)
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Read the user input
        System.out.print("Enter an interval (e.g., (1, 10) or [1, 10]): ");
        String input = scanner.nextLine().trim();

        // Check if the input matches the regular expression
        if (isValidInterval(input)) {
            System.out.println("Valid interval: " + input);
        } else {
            System.out.println("Invalid interval format.");
        }

        scanner.close();
    }

    // Method to check if the input matches the interval regex
    public static boolean isValidInterval(String input) {
        Pattern pattern = Pattern.compile(INTERVAL_REGEX);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}
 **/

class IntervalScanner{
    String input_string;
    String final INTERVAL_REGEX = "^(\[|\()[0-9]*\.[0-9]+,[0-9]*.[0-9]+(\]|\))$"gm; 
    IntevalScanner(string input_string){
        this.input_string=input_string;
    }
}