package umc.spring.converter;

import org.springframework.validation.annotation.Validated;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

@Validated
public class MissionConverter {
    public static Mission toMission(MissionRequestDTO request){
        return Mission.builder()
                .point(request.getPoint())
                .dueDate(request.getDueDate())
                .missionSpec(request.getMissionSpec())
                .build();
    }

    public static MissionResponseDTO toMissionResponseDTO(Mission mission){
        return MissionResponseDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
