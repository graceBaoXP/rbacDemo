package cn.grace.exception;

public class UsersException extends RuntimeException{

    public UsersException() {
    }

    public UsersException(String message) {
        super(message);
    }

    public UsersException(String message, Throwable cause) {
        super(message, cause);
    }
}
