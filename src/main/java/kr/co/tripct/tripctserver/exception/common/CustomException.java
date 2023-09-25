package kr.co.tripct.tripctserver.exception.common;


import kr.co.tripct.tripctserver.exception.StatusType;

public abstract class CustomException extends RuntimeException {

    public abstract StatusType getStatus();

    public abstract String getMessage();

    public CustomException(String message) {
        super(message);
    }
}
