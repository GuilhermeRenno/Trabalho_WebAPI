package com.fepi.Trabalho.Modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Exame")
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JsonBackReference(value = "codigo_paciente")
    @JoinColumn(name = "codigo_paciente")
    private Paciente paciente;

    private String nomeExame;
    private String local;
    private String diaExame;
}
