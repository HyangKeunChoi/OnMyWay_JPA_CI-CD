package mymind.mymind.repository;

import lombok.RequiredArgsConstructor;
import mymind.mymind.domain.Human;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class HumanPureJpaRepository {

    private final EntityManager entityManager;

    public Long save(Human human) {
        entityManager.persist(human);
        return human.getId();
    }

    public Human findById(Long id) {
        return entityManager.find(Human.class, id);
    }
}
