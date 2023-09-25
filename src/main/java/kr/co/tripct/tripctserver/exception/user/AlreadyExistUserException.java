package kr.co.tripct.tripctserver.exception.user;


import kr.co.tripct.tripctserver.exception.StatusType;
import kr.co.tripct.tripctserver.exception.common.CustomException;

public class AlreadyExistUserException extends CustomException {

    private final StatusType status;

    private static final String message = "이미 해당 이메일로 회원가입 한 유저입니다.";

    public AlreadyExistUserException() {
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