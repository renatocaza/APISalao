package br.com.apisalao.repository;

import br.com.apisalao.entity.EmployeEntity;
import br.com.apisalao.entity.SchedulingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SchedulingRepository extends JpaRepository<SchedulingEntity, Long> {

    List<SchedulingEntity> findByFuncionarioAndDataHoraInicioLessThanEqualAndDataHoraFimGreaterThanEqual(
            EmployeEntity funcionario,
            LocalDateTime dataHoraInicio,
            LocalDateTime dataHoraFim
    );
}
