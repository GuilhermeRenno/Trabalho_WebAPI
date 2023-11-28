package com.fepi.Trabalho.Modelos;


import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "Tratamentos")
@Getter
@Setter
public class Tratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JsonBackReference(value = "codigo_paciente")
    @JoinColumn(name = "codigo_paciente")
    private Paciente paciente;

    private String nomeTratamento;
    private String medicamento;
   // private Blob Receita;
    // private String Receita; //armazenando o url da imagem que ta no arquivo
    private String procedimentos;
    private String dataFinal;
}
