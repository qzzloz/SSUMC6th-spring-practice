package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionCommandService;
import umc.spring.validation.annotation.AlreadyMission;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MemberMissionController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/{missionId}")
    public ApiResponse<MemberMissionResponseDTO> challengeMission(@RequestBody @Valid MemberMissionRequestDTO request,
                                                                  @AlreadyMission @ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                  @RequestParam(name = "status") MissionStatus status,
                                                                  @RequestParam(name = "memberId") Long memberId) {
        MemberMission memberMission = memberMissionCommandService.updateChallengeMission(request, memberId, missionId, status);
        return ApiResponse.onSuccess(MemberMissionConverter.toMemberMissionResponseDTO(memberMission));
    }
}
