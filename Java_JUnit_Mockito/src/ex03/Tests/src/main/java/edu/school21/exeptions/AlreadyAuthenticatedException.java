package edu.school21.exeptions;

public class AlreadyAuthenticatedException extends RuntimeException {
    public AlreadyAuthenticatedException(){
    }
    public AlreadyAuthenticatedException(String message) {
        super(message);
    }
}
