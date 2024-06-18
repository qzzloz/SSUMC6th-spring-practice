package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPlayload.code.status.ErrorStatus;
import umc.spring.apiPlayload.exception.handler.TempHandler;

@Service
@RequiredArgsConstructor
public class TempQueryServiceImpl implements TempQueryService {
    @Override
    public void CheckFlag(Integer flag) {
        if(flag==1){
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
        }
    }
}
