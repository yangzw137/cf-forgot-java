package test;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;


/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2020/10/22
 * @since todo
 */
public class LauncherMain {
    private static final Logger logger = LoggerFactory.getLogger(LauncherMain.class);

    public static void main(String[] args) {
        logger.info("create tomcat server begin...");
//        Bootstrap bootstrap = new Bootstrap();
        try {
//            bootstrap.start();

//            Bootstrap.main(new String[]{"start"});
            Tomcat tomcat = createWebServer();
            tomcat.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("create tomcat server end...");
    }

    private static Tomcat createWebServer() {
        Tomcat tomcat = new Tomcat();
        File baseDir = new File("./tomcat");
        tomcat.setBaseDir(baseDir.getAbsolutePath());
        Connector connector = new Connector("HTTP/1.1");
        connector.setPort(9898);
//        connector.setThrowOnFailure(true);
        tomcat.getService().addConnector(connector);
//        customizeConnector(connector);
        tomcat.setConnector(connector);
        tomcat.getHost().setAutoDeploy(false);
        tomcat.getServer().setPort(8989);
//        configureEngine(tomcat.getEngine());
//        for (Connector additionalConnector : this.additionalTomcatConnectors) {
//            tomcat.getService().addConnector(additionalConnector);
//        }
//        prepareContext(tomcat.getHost(), initializers);
//        return getTomcatWebServer(tomcat);
//        tomcat.getService().addConnector();
        return tomcat;
    }

//    private void configureEngine(Engine engine) {
//        engine.setBackgroundProcessorDelay(this.backgroundProcessorDelay);
//        for (Valve valve : this.engineValves) {
//            engine.getPipeline().addValve(valve);
//        }
//    }
}
