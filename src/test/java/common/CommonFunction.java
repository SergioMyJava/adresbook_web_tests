package common;

import java.util.Random;

public class CommonFunction {


    public static String randomstring(int n) {
        var rand = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rand.nextInt(26));
        }
        return result;
    }
}
