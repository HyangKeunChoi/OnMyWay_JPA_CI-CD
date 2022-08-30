package mymind.mymind.domain;

import mymind.mymind.repository.CompanyDataJpaRepository;
import mymind.mymind.repository.HumanDataJpaRepository;
import mymind.mymind.repository.TechStackDataJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TechStackTest {

    @Autowired
    CompanyDataJpaRepository companyDataJpaRepository;

    @Autowired
    HumanDataJpaRepository humanDataJpaRepository;

    @Autowired
    TechStackDataJpaRepository techStackDataJpaRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("회사에 기술 스택 추가하기")
    @Transactional
    @Rollback(value = false)
    void addtechStack() {

        // given
        Human human = new Human();
        human.setUserName("최향근");
        human.setAge(30);

        AdditionalInfo additionalInfo = new AdditionalInfo();
        additionalInfo.setCareer("2.5");
        additionalInfo.setGithubLink("hyangkeunchoi");

        human.setAdditionalInfo(additionalInfo);

        Company company = new Company();
        company.setName("네이버");

        human.apply(company);

        TechStack techStack1 = new TechStack();
        techStack1.setTechName("Java");
        techStack1.setCompany(company);

        TechStack techStack2 = new TechStack();
        techStack2.setTechName("Spring");
        techStack2.setCompany(company);

        company.addStack(techStack1);
        company.addStack(techStack2);

        companyDataJpaRepository.save(company);

        entityManager.flush();
        entityManager.clear();

        // when
        Company findCompany = companyDataJpaRepository.findById(1L).get();

        for (int i = 0; i < findCompany.getTechStacks().size(); i++) {
            System.out.println(findCompany.getTechStacks().get(i).getTechName());
        }

        // then
        assertThat(findCompany.getTechStacks().size()).isEqualTo(2);
    }
}