package com.sistema.model;

import com.sistema.exception.SalarioInvalidoException;

public class FuncionarioPJ extends Funcionario implements Tributavel {
    private final double valorPorProjeto;
    private final int projetosRealizados;

    public FuncionarioPJ(int codigo, String nome, String cpf, double salarioBase, double valorPorProjeto, int projetosRealizados) {
        super(codigo, nome, cpf, salarioBase);

        if (valorPorProjeto < 0) {
            throw new SalarioInvalidoException("Valor por projeto não pode ser negativo.");
        }
        if (projetosRealizados < 0) {
            throw new SalarioInvalidoException("A quantidade de projetos realizados não pode ser negativa.");
        }

        this.valorPorProjeto = valorPorProjeto;
        this.projetosRealizados = projetosRealizados;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + (valorPorProjeto * projetosRealizados);
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.05;
    }
}