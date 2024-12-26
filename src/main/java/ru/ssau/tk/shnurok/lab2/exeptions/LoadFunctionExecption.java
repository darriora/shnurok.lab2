package ru.ssau.tk.shnurok.lab2.exeptions;

public class LoadFunctionExecption extends RuntimeException {
    public LoadFunctionExecption() {
        super();
    }

    public LoadFunctionExecption(String message) {
        super(message);
    }
}
