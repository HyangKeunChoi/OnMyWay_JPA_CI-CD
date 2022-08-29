package mymind.mymind.domain;

import mymind.mymind.repository.CompanyDataJpaRepository;
import mymind.mymind.repository.HumanPureJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HumanTest {
    @Autowired
    HumanPureJpaRepository humanPureJpaRepository;

    @Autowired
    CompanyDataJpaRepository companyDataJpaRepository;

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

        Company company = new Company();
        company.setHuman(human);
        company.setName("네이버");

        Company company1 = new Company();
        company1.setHuman(human);
        company1.setName("카카오");

        // when
        companyDataJpaRepository.save(company);
        companyDataJpaRepository.save(company1);

        List<Company> appliedCompany = companyDataJpaRepository.findAll();

        // then
        for (int i = 0; i < appliedCompany.size(); i++) {
            System.out.println(appliedCompany.get(i).getName());
        }

        assertThat(appliedCompany.size()).isEqualTo(2);
    }
}