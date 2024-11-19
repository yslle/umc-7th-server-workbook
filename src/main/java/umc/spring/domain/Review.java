package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Float rating;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(length = 100)
    private String reply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
    private List<ReviewImage> reviewImageList = new ArrayList<>();

    @Override
    public String toString() {
        return "Review {" +
                "rating=" + rating+ '\'' +
                ", content='" + content + '\'' +
                ", member=" + member.getName() + '\'' +
                ", store=" + store.getName() +
                '}';
    }

    public void setMember(Member member){
        if(this.member != null) {
            member.getReviewList().remove(this);
        }
        this.member = member;
        member.getReviewList().add(this);
    }
    public void setStore(Store store){
        if (this.store != null) {
            store.getReviewList().remove(this);
        }
        this.store = store;
        store.getReviewList().add(this);
    }
}
