package ru.ssau.tk.shnurok.lab2.service.operations.interfaces;

import ru.ssau.tk.shnurok.lab2.functions.coredefenitions.MathFunction;

public interface DifferentialOperator <T extends MathFunction> {
    T derive(T function);
}
