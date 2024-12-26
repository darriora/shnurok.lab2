package ru.ssau.tk.shnurok.lab2.exeptions;

public class RemoveIncorrectPoint extends RuntimeException {
    public RemoveIncorrectPoint() {}

    public RemoveIncorrectPoint(String message) {
        super(message);
    }
}
