package org.cf.forgot.lib.instrument.agent.model;

import java.util.Date;

/**
 * @author cf
 * @date 2020/8/21
 */
public class HelloInstrument {

    public String sayHello() {
        String str = "hello instrument, " + new Date();
        try {
            System.out.println(str);
            int n = 1/0;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
}
