package common;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunction {


    public static String randomstring(int n) {
        var rand = new Random();
        Supplier<Integer> randNumber= () -> rand.nextInt(26);
        var result = Stream.generate(randNumber)
        .limit(n)
        .map(i -> 'a' + i)
        .map(i -> Character.toString(i))
        .collect(Collectors.joining());
        return result;
    }

    public static int randomNumber(int n) {
        return new Random().nextInt(n);
    }
}
