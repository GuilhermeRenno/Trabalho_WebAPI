package com.fepi.Trabalho.Modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Paciente")
@Getter
@Setter
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private String nome;
    private String email;
    private String endereco;
    private int celular;
    private String tipoSanguineo;

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    private List<Tratamento> tratamentos = new ArrayList<>();

    @OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL)
    private List<Exame> exame = new ArrayList<>();
}

