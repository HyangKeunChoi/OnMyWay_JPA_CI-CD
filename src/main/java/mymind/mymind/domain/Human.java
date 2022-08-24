package mymind.mymind.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Human {

    public Human() {
    }

    @Id
    @GeneratedValue
    private Long id;

    private int age;

    private String userName;

    @Embedded
    AdditionalInfo additionalInfo;
}
