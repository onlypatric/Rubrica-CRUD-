import java.time.LocalDate;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        String s = "29/11/2006";
        System.out.println(s.matches("\\d{2}/\\d{2}/\\d{4}"));
    }
}