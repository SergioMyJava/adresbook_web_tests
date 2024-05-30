package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunction;
import model.GroupData;
import model.UserData;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-c"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();

        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        }
        if ("contacts".equals(type)) {
            return generateContakts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

    private Object generateContakts() {
        return generateData(()-> new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(10),
                CommonFunction.randomstring(10),
                CommonFunction.randomstring( 10),
                CommonFunction.randomstring(10)));
    }

    private Object generateGroups() {
        return generateData(()-> new GroupData()
                .withHeader(CommonFunction.randomstring(10))
                .withFooter(CommonFunction.randomstring(10))
                .withName(CommonFunction.randomstring(10)));

    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }
}
