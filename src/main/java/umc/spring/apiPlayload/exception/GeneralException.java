package umc.spring.apiPlayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.spring.apiPlayload.code.BaseErrorCode;
import umc.spring.apiPlayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {
    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
