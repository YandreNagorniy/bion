package com.example.bionintelligence.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.bionintelligence.data.pojo.MinMaxListPojo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class MinMaxListLimitConverters {
    private static Gson gson = new Gson();

    @TypeConverter
    public static MinMaxListPojo stringToList(String data) {
        if (data == null) return null;

        Type listType = new TypeToken<MinMaxListPojo>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String listToString(MinMaxListPojo value) {
        return gson.toJson(value);
    }
}
