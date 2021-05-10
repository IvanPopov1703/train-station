package ru.trainstation.exception;

import javax.annotation.PostConstruct;

public class NotFountException extends RuntimeException {

    public NotFountException() {
        super("Запись не найдена!");
    }
}
