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
import org.springframework.data.domain.PageImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberCommandService;
import umc.spring.service.StoreQueryService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.web.dto.MemberRequestDTO;
import umc.spring.web.dto.MemberResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Validated
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.joinDTO request){
        // @Valid 어노테이션이 MethodArgumentNotValidException를 발생시킵니다.
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "내가 작성한 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저(나)의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    @Validated
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                              @CheckPage @RequestParam(name = "page") Integer page){
        int pageIndex = page - 1;
        Page<Review> temp = storeQueryService.getMemberReviewList(memberId, pageIndex);
        return ApiResponse.onSuccess(StoreConverter.toReviewPreViewListDTO(temp));
    }

    @GetMapping("/{memberId}/missions")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "내가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "유저(나)의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    @Validated
    public ApiResponse<StoreResponseDTO.MissionPreViewListDTO> getMissionList(@ExistMember @PathVariable(name = "memberId") Long memberId,
                                                                             @CheckPage @RequestParam(name = "page") Integer page){
        int pageIndex = page - 1;
        Page<MemberMission> temp = memberCommandService.getMissionList(memberId, pageIndex);
        List<Mission> missions = temp.stream()
                .map(MemberMission::getMission)
                .collect(Collectors.toList());
        Page<Mission> missionPage = new PageImpl<>(missions, temp.getPageable(), temp.getTotalElements());

        return ApiResponse.onSuccess(StoreConverter.toMissionPreViewListDTO(missionPage));
    }
}
