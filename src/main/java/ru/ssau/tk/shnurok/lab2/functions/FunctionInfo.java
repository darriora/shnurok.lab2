package ru.ssau.tk.shnurok.lab2.functions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface FunctionInfo {
    String name();
    int priority();
}
