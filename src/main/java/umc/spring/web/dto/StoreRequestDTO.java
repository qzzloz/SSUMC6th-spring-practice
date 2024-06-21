package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class ReviewDTO{
        @NotNull
        Double score;
        @NotBlank
        String contents;
    }
}
