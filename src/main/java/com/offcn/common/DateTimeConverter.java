package com.offcn.common;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = simpleDateFormat.parse(source);
            return date;
        }catch (ParseException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
