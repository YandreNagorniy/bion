package com.example.bionintelligence.data.database.converters;

import android.arch.persistence.room.TypeConverter;

import com.example.bionintelligence.data.model.TestPhasesModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class TestCultureConverters {

    private static Gson gson = new Gson();

    @TypeConverter
    public static List<TestPhasesModel> stringToTestPhasesModel(String data) {
        if (data == null) return Collections.emptyList();

        Type listType = new TypeToken<List<TestPhasesModel>>() {
        }.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String TestPhasesModelToString(List<TestPhasesModel> list) {
        return gson.toJson(list);
    }

}
