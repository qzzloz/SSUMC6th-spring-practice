package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.converter.MemberConverter;
import umc.spring.converter.MemberPreferConverter;
import umc.spring.domain.Food;
import umc.spring.domain.Member;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.mapping.MemberFood;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.MemberMissionRepository;
import umc.spring.repository.MemberRepository;
import umc.spring.web.dto.MemberRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Transactional
    public Member joinMember(MemberRequestDTO.joinDTO request){
        Member newMember = MemberConverter.toMember(request);
        List<Food> foodCategoryList = request.getPreferFood().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        List<MemberFood> MemberFoodList = MemberPreferConverter.toMemberFood(foodCategoryList);
        MemberFoodList.forEach(memberFood -> {memberFood.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    public Optional<Member> findMember(Long value) {
        return memberRepository.findById(value);
    }

    public Page<MemberMission> getMissionList(Long memberId, Integer page){
        Page<MemberMission> memberMissionPage = memberMissionRepository.findByMemberIdAndStatus(memberId, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
        return memberMissionPage;
    }
}
