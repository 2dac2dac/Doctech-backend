package dac2dac.doctect.common.error.exception;

import dac2dac.doctect.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateException extends RuntimeException{
    private final ErrorCode code;

    public DuplicateException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}