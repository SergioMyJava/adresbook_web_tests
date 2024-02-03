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
import java.util.ArrayList;
import java.util.List;


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

    private List generateContakts() {
        var result = new ArrayList<UserData>();

        for (int i = 0; i < count; i++) {
            result.add(new UserData().userWithFullNameAdressMobile(CommonFunction.randomstring(i * 10),
                    CommonFunction.randomstring(i * 10), CommonFunction.randomstring(i * 10),
                    CommonFunction.randomstring(i * 10)));
        }
        return result;

    }

    private List generateGroups() {
        var result = new ArrayList<GroupData>();

        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withHeader(CommonFunction.randomstring(i * 10))
                    .withFooter(CommonFunction.randomstring(i * 10))
                    .withName(CommonFunction.randomstring(i * 10)));
        }
        return result;
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
