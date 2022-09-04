package jjfactory.shook.busniess.domain.store;

import jjfactory.shook.busniess.request.store.StoreCreate;
import jjfactory.shook.busniess.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Store extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Comment("가게 등급")
    @Column(nullable = false)
    private String grade;

    private int productCount;

    @Builder
    public Store(String name, String grade, int productCount) {
        this.name = name;
        this.grade = grade;
        this.productCount = productCount;
    }

    public static Store create(StoreCreate dto){
        return Store.builder()
                .name(dto.getName())
                .grade("1")
                .productCount(0)
                .build();
    }

    public void incProductCount() {
        this.productCount += 1;
    }

    public void decProductCount() {
        this.productCount -= 1;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
