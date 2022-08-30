package mymind.mymind.domain;

import mymind.mymind.repository.CompanyDataJpaRepository;
import mymind.mymind.repository.HumanDataJpaRepository;
import mymind.mymind.repository.HumanPureJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HumanTest {
    @Autowired
    HumanPureJpaRepository humanPureJpaRepository;

    @Autowired
    CompanyDataJpaRepository companyDataJpaRepository;

    @Autowired
    HumanDataJpaRepository humanDataJpaRepository;

    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void clean() {
        companyDataJpaRepository.deleteAll();
        humanDataJpaRepository.deleteAll();
    }

    @Test
    @Transactional
    @DisplayName("Jpa 실행 테스트")
    public void testHuman() {

        // given
        Human human = new Human();
        human.setUserName("최향근");
        Long saveId = humanPureJpaRepository.save(human);

        // when
        Human findHuman = humanPureJpaRepository.findById(saveId);

        // then
        assertThat(findHuman.getId()).isEqualTo(human.getId());
    }

    @Test
    @Transactional
    @DisplayName("지원한 회사들")
    public void testAppliedCompanys() {
        // given
        Human human = new Human();
        human.setUserName("최향근");

        Company company1 = new Company();
        company1.setName("네이버");

        Company company2 = new Company();
        company2.setName("카카오");

        human.apply(company1);
        human.apply(company2);

        // when
        humanDataJpaRepository.save(human);

        List<Company> appliedCompany = companyDataJpaRepository.findAll();

        // then
        for (int i = 0; i < appliedCompany.size(); i++) {
            System.out.println(appliedCompany.get(i).getName());
        }

        assertThat(appliedCompany.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("지원한 회사의 기술스택들, batch size조정")
    @Transactional
    @Rollback(value = false)
    void techStack3() {

        // given
        Human human1 = new Human();
        human1.setUserName("최향근1");

        Human human2 = new Human();
        human2.setUserName("최향근2");

        Company company1 = new Company();
        company1.setName("네이버");

        Company company2 = new Company();
        company2.setName("카카오");

        human1.apply(company1);
        human1.apply(company2);

        human2.apply(company2);

        TechStack techStack1 = new TechStack();
        techStack1.setTechName("Java");

        TechStack techStack2 = new TechStack();
        techStack2.setTechName("Kotlin");

        TechStack techStack3 = new TechStack();
        techStack3.setTechName("Spring");

        TechStack techStack4 = new TechStack();
        techStack4.setTechName("JPA");

        company1.addStack(techStack1);
        company1.addStack(techStack4);

        company2.addStack(techStack2);
        company2.addStack(techStack3);

        companyDataJpaRepository.save(company1);
        companyDataJpaRepository.save(company2);

        entityManager.flush();
        entityManager.clear();

        List<Human> allHuman = humanDataJpaRepository.findAll();

        assertThat(allHuman.size()).isEqualTo(2);

        for (Human human : allHuman) {
            for (int i = 0; i < human.getAppliedCompanys().size(); i++) {
                System.out.println("=====================");
                List<TechStack> techStacks = human.getAppliedCompanys().get(i).getTechStacks();
                techStacks.stream().map(t -> t.getTechName()).forEach(System.out::println);
                System.out.println("=====================");
            }
        }

    }
}