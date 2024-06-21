package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Review addReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request) {
        Review newReview = StoreConverter.toReview(request);

        newReview.setMember(memberRepository.findById(memberId).get());
        newReview.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(newReview);
    }

    public Optional<Store> findBy(Long value) {
        return storeRepository.findById(value);
    }
}
