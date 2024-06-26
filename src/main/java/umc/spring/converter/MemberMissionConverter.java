package umc.spring.converter;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO toMemberMissionResponseDTO(MemberMission request) {
        return MemberMissionResponseDTO.builder()
                .missionId(request.getId())
                .status(request.getStatus())
                .build();
    }
}
