package mymind.mymind.repository;

import mymind.mymind.domain.Human;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumanDataJpaRepository extends JpaRepository<Human, Long> {
}
