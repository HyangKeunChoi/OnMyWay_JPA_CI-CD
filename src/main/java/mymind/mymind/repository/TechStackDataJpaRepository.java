package mymind.mymind.repository;

import mymind.mymind.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechStackDataJpaRepository extends JpaRepository<TechStack, Long> {
}
