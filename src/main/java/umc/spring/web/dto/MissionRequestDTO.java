package umc.spring.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MissionRequestDTO {

    @NotNull
    private Double point;

    @NotNull
    private LocalDate dueDate;

    @NotBlank
    private String missionSpec;
}
