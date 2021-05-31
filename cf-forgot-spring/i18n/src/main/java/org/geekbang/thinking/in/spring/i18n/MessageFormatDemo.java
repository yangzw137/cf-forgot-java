package org.geekbang.thinking.in.spring.i18n;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Description: todo
 * <p>
 * company: <a href=www.jd.com>www.jd.com</a>
 *
 * @author <a href=mailto:yangzhiwei@jd.com>cf</a>
 * @date 2021/5/30
 * @since todo
 */
public class MessageFormatDemo {
    public static void main(String[] args) throws ParseException {

//        msgFormatPaint();
//        numberFormatPaint();
        dateFormatPaint();
        timeFormatPaint();
        dateTimeFormatterDemo();
    }

    private static void dateTimeFormatterDemo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        LocalDate localeData = LocalDate.now();
        System.out.printf("DateTimeFormatter full style, date: %s\n", localeData.format(formatter));
        formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.printf("DateTimeFormatter full style, dateTime: %s\n", localDateTime.format(formatter));
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss:SSS");
//        localDateTime = LocalDateTime.now();
        System.out.printf("DateTimeFormatter customize pattern, dateTime: %s\n", localDateTime.format(formatter));
    }

    private static void timeFormatPaint() {
        DateFormat df = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        System.out.printf("default style, time: %s\n", df.format(new Date()));
        df = DateFormat.getTimeInstance(DateFormat.LONG);
        System.out.printf("long style, time: %s\n", df.format(new Date()));
        df = DateFormat.getTimeInstance(DateFormat.FULL);
        System.out.printf("full style, time: %s\n", df.format(new Date()));
    }

    private static void dateFormatPaint() {

        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault());
        System.out.printf("default style, date: %s\n", df.format(new Date()));
        df = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        System.out.printf("long style, date: %s\n", df.format(new Date()));
        df = DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault());
        System.out.printf("full style, date: %s\n", df.format(new Date()));

    }

    private static void numberFormatPaint() throws ParseException {
        int[] myNumber = {
//                "a",
//                "b"
                1234,
                123456
        };
        NumberFormat nf = NumberFormat.getInstance();
        for (int i = 0; i < myNumber.length; ++i) {
            System.out.println(nf.format(myNumber[i]) + "; ");
        }

        nf = NumberFormat.getInstance(Locale.FRENCH);
        Number nmb = nf.parse("1,234");
        System.out.printf("French 1,234: %s\n", nmb);

        nf = NumberFormat.getInstance(Locale.CHINA);
        nmb = nf.parse("1,234");
        System.out.printf("China 1,234: %s\n", nmb);

        nf = NumberFormat.getInstance(Locale.US);
        nmb = nf.parse("1,234");
        System.out.printf("US 1,234: %s\n", nmb);
    }

    private static void msgFormatPaint() {
        int planet = 7;
        String event = "a disturbance in the Force";

        String pattern1 = "At {1,time} on {1,date}, there was {2} on planet {0,number,integer}.";
        String pattern2 = "At {1,time} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        String pattern3 = "At {1,time, long} on {1,date,full}, there was {2} on planet {0,number,integer}.";
        String pattern4 = "At {1,time, full} on {1,date,long}, there was {2} on planet {0,number,integer}.";
        String result = MessageFormat.format(pattern1, planet, new Date(), event);
        System.out.println(result);
        result = MessageFormat.format(pattern2, planet, new Date(), event);
        System.out.println(result);
        result = MessageFormat.format(pattern3, planet, new Date(), event);
        System.out.println(result);
        result = MessageFormat.format(pattern4, planet, new Date(), event);
        System.out.println(result);
    }
}
