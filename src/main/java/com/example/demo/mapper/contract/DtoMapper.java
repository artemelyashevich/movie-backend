package com.example.demo.mapper.contract;
import lombok.NonNull;

public interface DtoMapper<T, R> {
    @NonNull
    R convertFromDto(T dto);
}
