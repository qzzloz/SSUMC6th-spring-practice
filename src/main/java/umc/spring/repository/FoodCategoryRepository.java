package umc.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Food;

public interface FoodCategoryRepository extends JpaRepository<Food, Long> {

}
