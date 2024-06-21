package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.service.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/reivews")
    public ApiResponse<StoreResponseDTO.WriteReviewDTO> writeReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                    @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                    @ExistMember @RequestParam(name = "memberId") Long memberId) {
        Review review = storeCommandService.addReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toWriteReviewDTO(review));
    }
}
