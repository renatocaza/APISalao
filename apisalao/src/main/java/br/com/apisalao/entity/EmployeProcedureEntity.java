package br.com.apisalao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Getter
@Setter
@Table(name = "funcionario_procedimento")
public class EmployeProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private EmployeEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "procedimento_id", nullable = false)
    private ProcedureEntity procedimento;
    
}
