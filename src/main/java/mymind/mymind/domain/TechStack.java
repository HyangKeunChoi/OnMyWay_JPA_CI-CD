package mymind.mymind.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class TechStack {

    @Id @GeneratedValue
    @Column(name = "tech_stack_id")
    private Long id;

    private String techName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    void addStack(TechStack techStack) {
        this.company.getTechStacks().add(techStack);
    }
}
