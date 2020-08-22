package org.cf.forgot.jdk.closeable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cf
 * @date 2020/8/16
 */
public class AutoCloseableDemo {

    public static void main(String[] args) {
        CloseAbleBean outBean = null;
        try {
            CloseAbleBean closeAbleBean = new CloseAbleBean();
            closeAbleBean.print();
            outBean = closeAbleBean;
            closeAbleBean = null;
            int n = 1/0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            outBean.print();
            try {
//                outBean.close();
                outBean = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class CloseAbleBean implements AutoCloseable {
        private List<Integer> list = new ArrayList(){
            {
                add(1);
                add(2);
                add(3);
            }
        };

        void print() {
            System.out.println("print list: " + list);
        }

        @Override
        public void close() throws Exception {
            System.out.println("closing......");
            list.clear();
        }
    }
}
