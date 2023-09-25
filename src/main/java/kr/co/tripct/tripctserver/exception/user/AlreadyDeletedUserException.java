package kr.co.tripct.tripctserver.exception.user;


import kr.co.tripct.tripctserver.exception.StatusType;
import kr.co.tripct.tripctserver.exception.common.CustomException;

public class AlreadyDeletedUserException extends CustomException {

    private final StatusType status;

    private static final String message = "유저의 정보가 이미 존재합니다";

    public AlreadyDeletedUserException() {
        super(message);
        this.status = StatusType.ALREADY_USER_EXIST;
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
