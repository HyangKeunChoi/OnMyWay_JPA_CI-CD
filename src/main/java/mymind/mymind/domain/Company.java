package mymind.mymind.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "compayn_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "human_id")
    private Human human;

    // 연관관계 편의 메소드
    public void setHuman(Human human) {
        this.human = human;
        human.getAppliedCompanys().add(this);
    }
}
