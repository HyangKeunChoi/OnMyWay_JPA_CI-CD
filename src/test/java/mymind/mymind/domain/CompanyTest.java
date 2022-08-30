package mymind.mymind.domain;

import mymind.mymind.repository.CompanyDataJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CompanyTest {

    @Autowired
    CompanyDataJpaRepository companyDataJpaRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("지원자가 지원한와 회사가 저장된 값과 같은지 테스트")
    @Transactional
    void appliedCompanyTest() {

        // given
        Human human = new Human();
        human.setUserName("최향근");
        Company company = new Company();
        company.setHuman(human);
        Company savedCompany = companyDataJpaRepository.save(company);

        // when
        Company findCompany = companyDataJpaRepository.findById(company.getId()).get();

        // then
        assertThat(findCompany.getId()).isEqualTo(savedCompany.getId());
    }

    @Test
    @DisplayName("회사에 기술 스택 추가하기")
    @Transactional
    @Rollback(value = false)
    void addComapnyStacks() {

        // given
        Company company = new Company();
        company.setName("네이버");

        // when
        Company savedCompany = companyDataJpaRepository.save(company);
        Company findCompany = companyDataJpaRepository.findById(savedCompany.getId()).get();

        // then
        assertThat(findCompany.getId()).isEqualTo(savedCompany.getId());
    }

    @Test
    @DisplayName("회사에 기술 스택 추가하기2")
    @Transactional
    @Rollback(value = false)
    void techStack2() {

        // given - CascadeType.ALL
        Human human = new Human();
        human.setUserName("최향근");
        human.setAge(30);

        Company company = new Company();
        company.setName("네이버");
        company.setHuman(human);

        // when
        Company savedCompany = companyDataJpaRepository.save(company);
        Company findCompany = companyDataJpaRepository.findById(savedCompany.getId()).get();

        // then
        assertThat(findCompany.getId()).isEqualTo(savedCompany.getId());
        assertThat(findCompany.getTechStacks().size()).isEqualTo(0);
    }
}