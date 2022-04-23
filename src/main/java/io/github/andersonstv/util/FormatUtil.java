package io.github.andersonstv.util;



public class FormatUtil {
    final public static String integerRegex = "^\\d+$";
    final public static String captureIntegerRegex = "(\\d+)";
    final public static String quotesRegex = "\"([^\"]*)\"";
    final public static String sep = System.lineSeparator();

    public static boolean isInteger(String str){
        return str.matches(integerRegex);
    }
    public static String validateDiscordLimit(String response){
        if (response.length() >= 2000){
            return "Response exceedds Discord character limit";
        }else {
            return response;
        }
    }
}
