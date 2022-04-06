import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCsvFileParser implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(CsvPojoAnnotation.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) throws ParameterResolutionException {
        var csvPojoAnnotation = parameterContext.getParameter().getDeclaredAnnotation(CsvPojoAnnotation.class);
        List<String> fileLines = null;
        try {
            fileLines = Files.readAllLines(Path.of(csvPojoAnnotation.filePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CsvPojo> result = new ArrayList<>();

        for (String line : fileLines) {
            if (line.startsWith("#")) continue;
            String[] columns = line.split(",");
            List<Integer> teeths = Stream.of(columns[1].split(" "))
                    .filter(numberStr -> !Objects.equals(numberStr, ""))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            result.add(new CsvPojo(columns[0], teeths, Integer.parseInt(columns[2]), Integer.parseInt(columns[3])));
        }


        return result;
    }
}
