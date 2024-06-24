package umc.spring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StoreQueryService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public Optional<Store> findBy(Long value) {
        return storeRepository.findById(value);
    }

    public Page<Review> getReviewList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId).get();
        Page<Review> storePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return storePage;
    }
}
