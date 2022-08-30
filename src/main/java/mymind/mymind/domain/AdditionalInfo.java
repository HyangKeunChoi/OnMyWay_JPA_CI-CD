package mymind.mymind.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class AdditionalInfo {

    private String career;
    private String githubLink;

    public AdditionalInfo() {
    }

    public AdditionalInfo(String career, String githubLink) {
        this.career = career;
        this.githubLink = githubLink;
    }
}
