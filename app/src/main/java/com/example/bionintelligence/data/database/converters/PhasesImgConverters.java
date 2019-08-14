package com.example.bionintelligence.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class PhasesImgConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<Integer> stringToInteger(String data) {
        if (data == null) return Collections.emptyList();

        Type listType = new TypeToken<List<Integer>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String DoubleToInteger(List<Integer> list) {
        return gson.toJson(list);
    }
}
