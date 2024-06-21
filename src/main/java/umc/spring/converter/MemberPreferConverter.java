package umc.spring.converter;

import umc.spring.domain.Food;
import umc.spring.domain.mapping.MemberFood;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {
    public static List<MemberFood> toMemberFood(List<Food> foodCategoryList) {
        return foodCategoryList.stream()
                .map(food ->
                        MemberFood.builder()
                                .food(food)
                                .build()
                ).collect(Collectors.toList());
    }
}
