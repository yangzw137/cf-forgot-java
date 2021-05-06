package sun.net.www.protocol.x;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import sun.net.www.URLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/5
 * @since todo
 */
public class XURLConnection extends URLConnection {

    private Resource resource;

    public XURLConnection(URL url) {
        super(url);
        resource = new ClassPathResource(url.getPath());
    }

    @Override
    public void connect() throws IOException {

    }

    @Override
    public InputStream getInputStream() throws IOException {
        return resource.getInputStream();
    }
}
