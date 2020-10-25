package io.github.andersonstv;

public class Util {
    final public static String integerRegex = "^\\d+$";
    final public static String sep = System.lineSeparator();

    public static boolean isInteger(String str){
        return str.matches(integerRegex);
    }

}
