package org.geekbang.thinking.in.spring.i18n;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * 动态（更新）资源 {@link org.springframework.context.MessageSource} 实现
 * <p>
 * 实现步骤：
 * <p>
 * 1. 定位资源位置（properties 文件）
 * 2. 初始化 properties 对象
 * 3. 实现 {@link AbstractMessageSource#resolveCode } 方法
 * 4. 监听资源文件 （java NIO 2）{@link java.nio.file.WatchService}
 * 5. 
 * </p>
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/30
 * @since todo
 */
public class DynamicResourceMessageDemo extends AbstractMessageSource implements ResourceLoaderAware {

    private final static String FILENAME = "msg.properties";
    private final static String LOCATION = "classpath:/META-INF/" + FILENAME;
    private final static Charset CHARSET = Charset.forName("UTF-8");
    private ResourceLoader resourceLoader;
    private Properties messageProperties;
    private Resource messagePropertiesResource;

    private ExecutorService executor = new ThreadPoolExecutor(1,1, 60, TimeUnit.SECONDS, new SynchronousQueue<>());

    public DynamicResourceMessageDemo() {
        messageProperties = loadMessageProperties();
        onMessagePropertiesChange();
    }

    private void onMessagePropertiesChange() {
        if (messagePropertiesResource.isFile()) {
            try {
                File file = messagePropertiesResource.getFile();
                Path filePath = file.toPath();
                Path dirPath = filePath.getParent();
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watchService = fileSystem.newWatchService();
                dirPath.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                processMessageSourceChange(watchService);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    private void processMessageSourceChange(WatchService watchService) {
        executor.submit(() -> {
           while (true) {
               WatchKey watchKey = watchService.take();
               try {
                   if (watchKey.isValid()) {
                       Path dirPath = (Path) watchKey.watchable();
                       for (WatchEvent event : watchKey.pollEvents()) {
                           Path fileRelativePath = (Path) event.context();
                           if (!FILENAME.endsWith(fileRelativePath.toString())) {
                               continue;
                           }
                           System.out.printf("change file relative path: %s \n", fileRelativePath);
                           Path filePath = dirPath.resolve(fileRelativePath);
                           System.out.printf("change file path: %s \n", filePath);

                           Properties properties = new Properties();
                           properties.load(new FileReader(filePath.toFile()));

                           synchronized (messageProperties) {
                               messageProperties.clear();
                               messageProperties.putAll(properties);
                           }
                           paint();
                       }
                   }
               }finally {
                    watchKey.reset();
               }
           }
        });
    }


    private Properties loadMessageProperties() {
        resourceLoader = getResourceLoader();
        messagePropertiesResource = resourceLoader.getResource(LOCATION);
        EncodedResource encodedResource = new EncodedResource(messagePropertiesResource, CHARSET);
        Properties msgProperties = new Properties();
        try(Reader reader = encodedResource.getReader()) {
            msgProperties.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msgProperties;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String pattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(pattern)) {
            return new MessageFormat(pattern, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private ResourceLoader getResourceLoader() {
        return resourceLoader != null ? resourceLoader : (resourceLoader = new DefaultResourceLoader());
    }

    private void paint() {
        String msg = getMessage("name", new Object[]{}, Locale.getDefault());
        System.out.printf("name: %s\n", msg);
    }

    public static void main(String[] args) {
        DynamicResourceMessageDemo demo = new DynamicResourceMessageDemo();
       demo.paint();
    }
}
