package mymind.mymind.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
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
