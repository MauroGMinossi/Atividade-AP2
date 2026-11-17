package com.sistema.model;

import com.sistema.exception.SalarioInvalidoException;

public class FuncionarioComissionado extends Funcionario implements Tributavel {
    private final double totalVendas;
    private final double percentualComissao;

    public FuncionarioComissionado(int codigo, String nome, String cpf, double salarioBase, double totalVendas, double percentualComissao) {
        super(codigo, nome, cpf, salarioBase);

        if (totalVendas < 0) {
            throw new SalarioInvalidoException("O total de vendas não pode ser negativo.");
        }
        if (percentualComissao < 0 || percentualComissao > 100) {
            throw new SalarioInvalidoException("O percentual de comissão deve estar entre 0 e 100.");
        }

        this.totalVendas = totalVendas;
        this.percentualComissao = percentualComissao;
    }

    @Override
    public double calcularRemuneracao() {
        return getSalarioBase() + (totalVendas * (percentualComissao / 100.0));
    }

    @Override
    public double calcularTributos() {
        return calcularRemuneracao() * 0.08;
    }
}