package br.com.apisalao.repository;

import br.com.apisalao.entity.ClientEntity;
import br.com.apisalao.entity.EmployeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<EmployeEntity, Long> {
    boolean existsByEmail(String email);
}
