package mymind.mymind.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Human extends BaseAbstractEntity {

    @Id
    @GeneratedValue
    @Column(name = "human_id")
    private Long id;

    private int age;

    private String userName;

    @Embedded
    AdditionalInfo additionalInfo;
}
