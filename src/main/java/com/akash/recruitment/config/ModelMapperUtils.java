package com.akash.recruitment.config;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ModelMapperUtils {

    public static final ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    public static <T, D> D map(T s, Class<D> d) {

        return mapper.map(s, d);
    }

    public static <D, T> List<D> mapAll(List<T> list, Class<D> d) {

        return list.stream().map(s -> map(s, d)).collect(Collectors.toList());
    }

}
