package umc.spring.domain;

import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.enums.Gender;
import umc.spring.domain.mapping.MemberFood;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    private LocalDate birth;

    @Column(nullable = false, length = 20)
    private String address;

    @Column(nullable = false, length = 20)
    private String nowLoc;

    @ColumnDefault("0")
    private Integer point;

    private String email;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();
}
