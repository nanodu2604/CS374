import java.util.regex.Matcher;
import java.util.regex.Pattern;

class IntervalProcessing {
    private String[] tokens;
    private static final String INTERVAL_REGEX = "(\\[|\\()[0-9]*\\.[0-9]+,[0-9]*\\.[0-9]+(\\]|\\))$";

    public IntervalProcessing(String interval_str) throws IllegalArgumentException {
        Pattern pattern = Pattern.compile(IntervalProcessing.INTERVAL_REGEX);
        Matcher matcher = pattern.matcher(interval_str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid interval format");
        }
        this.tokens = parseTokens(interval_str);
    }

    private String[] parseTokens(String interval_str) throws IllegalArgumentException {
        String startBracket = interval_str.substring(0, 1);
        String endBracket = interval_str.substring(interval_str.length() - 1);
        String[] values = interval_str.substring(1, interval_str.length() - 1).split(",");
        if(Float.valueOf(values[0])>=Float.valueOf(values[1])){
            throw new IllegalArgumentException(values[0]+" cannot be greater or equal to "+ values[1]+".");
        }
        return new String[] { startBracket, values[0], values[1], endBracket };
    }

    public static boolean checkInterval(float number, String[] tokens) {
        float start = Float.parseFloat(tokens[1]);
        float end = Float.parseFloat(tokens[2]);

        if ("(".equals(tokens[0]) && number <= start) {
            return false;
        } else if ("[".equals(tokens[0]) && number < start) {
            return false;
        }

        if ("]".equals(tokens[3]) && number > end) {
            return false;
        } else if (")".equals(tokens[3]) && number >= end) {
            return false;
        }

        return true;
    }

    public String[] getTokens() {
        return this.tokens;
    }
}
