package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionCommandService;
import umc.spring.service.StoreQueryService;
import umc.spring.validation.annotation.AlreadyMission;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMission;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

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
