import java.time.ZonedDateTime;

/**
 * @author Mr. Hao
 * @date 2021-07-27   14:42
 */
public class T2 {
    public static void main(String[] args) {
        //2021-07-27T14:43:15.366+08:00[Asia/Shanghai]
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
