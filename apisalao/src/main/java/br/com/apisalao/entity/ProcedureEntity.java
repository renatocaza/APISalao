package br.com.apisalao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Getter
@Setter
@Table(name = "procedimentos")
public class ProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="nome",nullable = false)
    private String nome;

    @Column(name ="descricao")
    private String descricao;

    @Column(name ="duracao",nullable = false)
    private Time duracao;

    @Column(name ="preco",nullable = false)
    private BigDecimal preco;

}
