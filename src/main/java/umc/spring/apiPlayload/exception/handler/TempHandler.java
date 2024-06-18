package umc.spring.apiPlayload.exception.handler;

import umc.spring.apiPlayload.code.BaseErrorCode;
import umc.spring.apiPlayload.exception.GeneralException;

public class TempHandler extends GeneralException {
    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
