package com.example.demo.util;

import lombok.experimental.UtilityClass;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

@UtilityClass
public class Utils {

    public static void validateBindingResult(final BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            if (bindingResult instanceof BindException exception) {
                throw exception;
            } else {
                throw new BindException(bindingResult);
            }
        }
    }
}
