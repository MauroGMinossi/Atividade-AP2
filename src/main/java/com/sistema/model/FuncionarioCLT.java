package com.sistema.model;

import com.sistema.exception.SalarioInvalidoException;

public class FuncionarioCLT extends Funcionario implements Tributavel {
    private final double adicionalBeneficios;

    public FuncionarioCLT(int codigo, String nome, String cpf, double salarioBase, double adicionalBeneficios) {
        super(codigo, nome, cpf, salarioBase);

        if (adicionalBeneficios < 0) {
            throw new SalarioInvalidoException("Adicional de beneficios não pode ser 0 ou negativo.");
        }
        this.adicionalBeneficios = adicionalBeneficios;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + adicionalBeneficios;
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.30;
    }
}