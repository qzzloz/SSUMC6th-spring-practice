package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.spring.domain.enums.MissionStatus;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberMissionResponseDTO {

    private Long id;

    private Long missionId;

    private Long memberId;

    private MissionStatus status;
}
