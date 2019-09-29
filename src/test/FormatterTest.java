package test;

import java.util.Formatter;

/**
 * ClassName FormatterTest
 *
 * @author Dylan
 * @description TODO
 * @createDate 2019-09-16 17:41
 */
public class FormatterTest {
    public static void main(String[] args) {
        byte[] hash = "12345".getBytes();
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        System.out.println(result);
        formatter.close();
    }

}
