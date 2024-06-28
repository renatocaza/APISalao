package br.com.apisalao.repository;

import br.com.apisalao.entity.ProcedureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcedureRepository extends JpaRepository<ProcedureEntity, Long> {
}
