package ua.kiev.sinenko.otpservice.otp.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {
    public static Calendar currentTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        return calendar;
    }

    public static Calendar expireTime(Long durationSeconds) {
        Calendar calendar = new GregorianCalendar();
        Long miliseconds = durationSeconds * 1000;
        Date expire = new Date(new Date().getTime() + miliseconds);
        calendar.setTime(expire);
        return calendar;
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.currentTime().getTime());
        System.out.println(DateUtils.expireTime(600L).getTime());
    }
}
