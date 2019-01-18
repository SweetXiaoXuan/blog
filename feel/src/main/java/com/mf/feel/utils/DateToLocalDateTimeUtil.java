package com.mf.feel.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Slf4j
public class DateToLocalDateTimeUtil {

    /**
     * Date转换为LocalDateTime
     *
     * @param birth(1996.1)
     */
    public static int getAge(String birth, LocalDateTime meetingTime) {
        if (meetingTime == null || StringUtils.isEmpty(birth)) {
            return 0;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = sdf.parse(birth);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            LocalDate birthDate = localDateTime.toLocalDate();
            LocalDate meetingDate = meetingTime.toLocalDate();
            int age = birthDate.until(meetingDate).getYears();
            return age;
        } catch (ParseException e) {
            log.warn("birth转换错误: " + birth);
            return 0;
        }
    }

    public static LocalDateTime changeByStr(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = sdf.parse(time);
            Instant instant = date.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            return localDateTime;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String changeLoaclDateToString(LocalDateTime meetingTime) {
        String time = meetingTime.toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
        Date date = null;
        try {
            date = sdf.parse(time);
            return date.toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String changeString(LocalDateTime meetingTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTimeFormatter.format(meetingTime);
    }


    public static void main(String[] args) throws ParseException {

    }
}
