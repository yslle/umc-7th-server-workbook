package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.common.BaseEntity;
import umc.spring.domain.mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private LocalDate deadline;

    @Column(nullable = false)
    private Integer reward;

    @Column(nullable = false)
    private String ownerCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @Override
    public String toString() {
        return "Mission {" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", deadline='" + deadline + '\'' +
                ", reward=" + reward + '\'' +
                '}';
    }

    public void setStore(Store store){
        if (this.store != null) {
            store.getMissionList().remove(this);
        }
        this.store = store;
        store.getMissionList().add(this);
    }
}
