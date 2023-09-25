package kr.co.tripct.tripctserver.exception.user;


import kr.co.tripct.tripctserver.exception.StatusType;
import kr.co.tripct.tripctserver.exception.common.CustomException;

public class UserIllegalStateException extends CustomException {
    private final StatusType status;
    private static final String message = "";

    public UserIllegalStateException() {
        super(message);
        this.status = StatusType.BAD_REQUEST;
    }


    @Override
    public StatusType getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}