package com.magic.ereal.business.util;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JsonUtil {
	public static String toJson(Object object) {
		Gson gson = new Gson();

		try {
			return gson.toJson(object);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, Class<T> classOfT) {
		Gson gson = new Gson();

		try {
			return gson.fromJson(json, classOfT);
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, Type typeOfT) {
		Gson gson = new Gson();

		try {
			return gson.fromJson(json, typeOfT);
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}
}
