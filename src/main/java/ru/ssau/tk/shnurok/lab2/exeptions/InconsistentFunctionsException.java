package ru.ssau.tk.shnurok.lab2.exeptions;

public class InconsistentFunctionsException extends RuntimeException {
    public InconsistentFunctionsException() {
    }
    public InconsistentFunctionsException(String message) {
        super(message);
    }
}
