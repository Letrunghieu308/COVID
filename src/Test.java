import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String f = "f0";
        int index = Integer.parseInt(f.substring(f.length() - 1));
        index++;
        System.out.println(index);
    }
}
