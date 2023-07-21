package co.id.bankbsi.middlewaresnippet.common.util;

import java.util.Objects;

public class StringUtil {

    public static String trimString(String input, int trimVlaue){
        if (input.length() <= trimVlaue){
            return input;
        }else return input.substring(0,trimVlaue);
    }

    public static <T> T isEmptyThenNull(T input){
        return Objects.isNull(input)? null : input;
    }

    public static String eliminateNonNumber(String input){
        return input.replaceAll("[^\\d]", "");
    }

    //limit String to n character
    public static String limitString(String input, int limit){
        if (input.length() <= limit){
            return input;
        }else return input.substring(0,limit);
    }
}
