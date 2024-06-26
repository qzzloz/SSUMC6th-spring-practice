package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.web.dto.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMissionResponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission updateChallengeMission(MemberMissionRequestDTO request, Long memberId, Long missionId, MissionStatus status) {
        Optional<MemberMission> optionalMemberMission = memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId);

        if (optionalMemberMission.isPresent()) {
            MemberMission memberMission = optionalMemberMission.get();
            memberMission.setStatus(status);
            return memberMissionRepository.save(memberMission);
        } else {
            MemberMission newMM = MemberMission.builder()
                    .status(status)
                    .member(memberRepository.findById(memberId).get())
                    .mission(missionRepository.findById(missionId).get())
                    .build();

            return memberMissionRepository.save(newMM);
        }
    }
}
