package mymind.mymind.repository;

import mymind.mymind.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDataJpaRepository extends JpaRepository<Company, Long> {
}
