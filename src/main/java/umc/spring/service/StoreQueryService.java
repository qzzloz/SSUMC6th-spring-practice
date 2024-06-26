package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Optional<Store> findBy(Long value) {
        return storeRepository.findById(value);
    }

    public Page<Review> getReviewList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        Page<Review> storeReviewPage = reviewRepository.findAllReviewByStore(store, PageRequest.of(page, 10));
        return storeReviewPage;
    }

    public Page<Mission> getMissionList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        Page<Mission> storeMissionPage = missionRepository.findAllMissionByStore(store, PageRequest.of(page, 10));
        return storeMissionPage;
    }

    public Page<Review> getMemberReviewList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId).get();
        Page<Review> memberReviewPage = reviewRepository.findAllReviewByMember(member, PageRequest.of(page, 10));
        return memberReviewPage;
    }
}
