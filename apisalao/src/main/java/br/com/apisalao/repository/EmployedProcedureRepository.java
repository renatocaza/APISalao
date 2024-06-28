package br.com.apisalao.repository;

import br.com.apisalao.entity.EmployeProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployedProcedureRepository extends JpaRepository<EmployeProcedureEntity, Long> {
}
