package org.cf.forgot.lib.instrument.agent.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author cf
 * @date 2020/8/21
 */
public class HelloInstrument {
    private Logger logger = LoggerFactory.getLogger(HelloInstrument.class);

    public String sayHello() {
        String str = "hello instrument, " + new Date();
        try {
            logger.info("-----===1 666666====--------str: {}", str);
//            System.out.printf("-----====66666===--------str: %s \n", str);
//            int n = 1/0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
}
