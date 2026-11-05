package com.ibmec.brinquedos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TDS_TB_Brinquedos")
public class Brinquedo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(name = "Nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "O tipo é obrigatório (ex.: bola, raquete, patins)")
    @Column(name = "Tipo", nullable = false)
    private String tipo;


    @NotNull(message = "A classificação (idade indicativa) é obrigatória")
    @Min(value = 0, message = "A classificação não pode ser negativa")
    @Max(value = 15, message = "A classificação deve ser de no máximo 15 anos")
    @Column(name = "Classificacao", nullable = false)
    private Integer classificacao;

    @NotBlank(message = "O tamanho é obrigatório")
    @Pattern(regexp = "P|M|G", message = "O tamanho deve ser P, M ou G")
    @Column(name = "Tamanho", nullable = false, length = 1)
    private String tamanho;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    @Column(name = "Preco", nullable = false)
    private BigDecimal preco;

    public Brinquedo() { }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Integer getClassificacao() { return classificacao; }
    public void setClassificacao(Integer classificacao) { this.classificacao = classificacao; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
}