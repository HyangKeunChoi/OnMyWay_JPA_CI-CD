package mymind.mymind.domain;

import mymind.mymind.repository.HumanRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HumanTest {

    @Autowired
    HumanRepository humanRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    public void testHuman() {
        Human human = new Human();
        human.setUserName("최향근");
        Long saveId = humanRepository.save(human);

        Human findHuman = humanRepository.findById(saveId);
        assertThat(findHuman.getId()).isEqualTo(human.getId());
    }
}