import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    enum Color {
        GREEN("green");

        String colorCode;

        Color(String colorCode) {
            this.colorCode = colorCode;
        }

        public String getColorCode() {
            return colorCode;
        }
    }

    private class EchoService {
        public String echo(String msg) {
            return msg.toUpperCase(Locale.ROOT);
        }
    }

    @Test
    public void successEchoWithLoop() {
        // key-value struct
        Map<String, String> testingData = Map.of("guf", "GUF", "var", "VAR");
        var echoService = new EchoService();
        for (String inputMsg: testingData.keySet()) {
            var actualMsg = echoService.echo(inputMsg);
            assertEquals(testingData.get(inputMsg), actualMsg);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"guf, GUF", "var, VAR"})
    public void successEcho(String inputMsg, String expectedMsg) {
        var echoService = new EchoService();
        var actualMsg = echoService.echo(inputMsg);
        assertEquals(expectedMsg, actualMsg);
    }

    @ParameterizedTest
    @CsvSource(value = "test, 1, green")
    public void test(String userName, int iteration, String color) {
        System.out.printf("Username = %s, iteration = %d, color = %s",
                userName, iteration, color);
    }

    // unit -> dev
    // component -> dev
    // re -> qa
    // int -> qa
    // ручным тест -> qa
    // qa load -> qa

    @ParameterizedTest
    @CsvFileSource(files = "test.csv")
    public void testWithCsvFile(String id, int[] removeTeeth,
                                int price, int discount) {
        System.out.printf("Id = %s, price = %d, discount = %d", id,
                price, discount);
        System.out.println(Arrays.toString(removeTeeth));
    }

    @Test
    @ExtendWith(CustomCsvFileParser.class)
    public void successParseCsvFile(
            @CsvPojoAnnotation(filePath = "/Users/grisha/IdeaProjects/qa-open-less/src/test/resources/test.csv") List<CsvPojo> csvPojos) {
        System.out.println(csvPojos);
    }
}
