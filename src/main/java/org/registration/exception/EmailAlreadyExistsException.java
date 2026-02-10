package org.registration.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException() {
        super("El correo ya esta registrado");
    }
}
