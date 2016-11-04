package com.follow.me.util;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by divya on 1/11/16.
 */
public class StringListConverter implements AttributeConverter<List<String>,String> {
    @Override
    public String convertToDatabaseColumn(List<String> strings) {
        String str = "";
        for(String x : strings){
            str = str + x + ",";
        }
        return str.substring(0,str.length()-1);
    }

    @Override
    public List<String> convertToEntityAttribute(String s) {
        return new ArrayList<>(Arrays.asList(s.split(","))) ;
    }
}
