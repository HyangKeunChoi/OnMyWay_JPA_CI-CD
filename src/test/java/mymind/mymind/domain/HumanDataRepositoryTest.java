package mymind.mymind.domain;

import mymind.mymind.repository.HumanDataJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HumanDataRepositoryTest {

    @Autowired
    HumanDataJpaRepository humanDataJpaRepository;

    @Test
    @Transactional
    @DisplayName("SpringDataJpa 실행 테스트")
    public void testHuman() {

        // given
        Human human = new Human();
        human.setUserName("최향근");
        Human savedHuman = humanDataJpaRepository.save(human);

        // when
        Human findHuman = humanDataJpaRepository.findById(savedHuman.getId()).get();

        // then
        assertThat(findHuman.getId()).isEqualTo(human.getId());
        assertThat(findHuman.getUserName()).isEqualTo(human.getUserName());
    }
}
