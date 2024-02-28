package edu.school21.exeptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
    }
    public EntityNotFoundException(String message){
        super(message);
    }
}
