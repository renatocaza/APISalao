package br.com.apisalao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "agendamentos")
public class SchedulingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientEntity cliente;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private EmployeEntity funcionario;

    @ManyToOne
    @JoinColumn(name = "procedimento_id", nullable = false)
    private ProcedureEntity procedimento;

    @Column(name = "data_hora_inicio", nullable = false)
    private LocalDateTime dataHoraInicio;

    //essa coluna deve ser alimentada com a data de inicio + o tempo do procedimento.
    @Column(name = "data_hora_fim", nullable = false)
    private LocalDateTime dataHoraFim;
}
