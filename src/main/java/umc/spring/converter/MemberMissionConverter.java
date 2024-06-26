package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO toMemberMissionResponseDTO(MemberMission request) {
        return MemberMissionResponseDTO.builder()
                .missionId(request.getId())
                .status(request.getStatus())
                .build();
    }
}
