package kr.co.tripct.tripctserver.exception.user;


import kr.co.tripct.tripctserver.exception.StatusType;
import kr.co.tripct.tripctserver.exception.common.CustomException;

public class UserNotAccessRightException extends CustomException {

    private final StatusType status;

    private static final String message = "접근권한이 없는 유저입니다";

    public UserNotAccessRightException() {
        super(message);
        this.status = StatusType.ACCESS_RIGHT_FAILED;
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