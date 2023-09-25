package kr.co.tripct.tripctserver.exception.study;


import kr.co.tripct.tripctserver.exception.StatusType;
import kr.co.tripct.tripctserver.exception.common.CustomException;

public class PostNotFoundException extends CustomException {

    private final StatusType status;
    private static final String message = "해당 아이디를 가진 게시글이 없습니다. 아이디 값을 다시 한번 확인하세요.";
    public PostNotFoundException() {
        super(message);
        this.status = StatusType.USER_NOT_FOUND;
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
