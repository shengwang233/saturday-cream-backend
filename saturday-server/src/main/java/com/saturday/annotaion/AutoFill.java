package com.saturday.annotaion;

import com.saturday.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation used to indicate that a method
 * requires automatic filling of specific fields (e.g., audit fields).
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // INSERT or UPDATE
    OperationType value();
}
