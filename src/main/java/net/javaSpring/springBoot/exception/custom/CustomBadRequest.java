package net.javaSpring.springBoot.exception.custom;

public class CustomBadRequest extends Exception {

    public CustomBadRequest(String message) {
        super(message);
    }
    
}
