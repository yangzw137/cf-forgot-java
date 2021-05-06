package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 */
public class HandlerDemo {

    public static void main(String[] args) throws IOException {
        URL url = new URL("x://./META-INF/default.properties");
        InputStream inputStream = url.openStream();
        String str = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
        System.out.println(str);
    }
}
