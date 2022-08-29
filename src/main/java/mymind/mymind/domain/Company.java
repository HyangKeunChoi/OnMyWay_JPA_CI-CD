package mymind.mymind.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue
    @Column(name = "company_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "human_id")
    private Human human;

    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<TechStack> techStacks = new ArrayList<>();

    // 연관관계 편의 메소드
    public void setHuman(Human human) {
        this.human = human;
        human.getAppliedCompanys().add(this);
    }
}
