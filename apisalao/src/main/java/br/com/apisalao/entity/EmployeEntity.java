package br.com.apisalao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "funcionarios")
public class EmployeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long funcId;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "data_criacao")
	private LocalDate inclusao;

	@Column(name = "data_manut")
	private LocalDate manutencao;

	@OneToMany(mappedBy = "funcionario")
	private List<SchedulingEntity> agendamentos;
}
