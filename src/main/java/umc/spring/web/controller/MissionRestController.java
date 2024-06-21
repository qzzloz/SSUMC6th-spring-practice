package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MissionCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
@Validated
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/create")
    public ApiResponse<MissionResponseDTO> createMission(@RequestBody @Valid MissionRequestDTO request,
                                                         @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = missionCommandService.addMission(request, storeId);
        return ApiResponse.onSuccess(MissionConverter.toMissionResponseDTO(mission));
    }

}
