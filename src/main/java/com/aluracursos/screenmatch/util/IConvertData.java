package com.aluracursos.screenmatch.util;

public interface IConvertData {
    <T> T fromJson(String json, Class<T> tClass);
}
