package com.sistema.model;

import com.sistema.exception.CodigoIdentificadorInvalidoException;
import com.sistema.exception.SalarioInvalidoException;

public abstract class Funcionario implements Identificavel {
    private final int codigo;
    private final String nome;
    private final String cpf;
    private final double salarioBase;

    protected Funcionario(int codigo, String nome, String cpf, double salarioBase) {
        if (codigo <= 0) {
            throw new CodigoIdentificadorInvalidoException("Código identificador deve ser maior que zero!");
        }
        if (salarioBase <= 0) {
            throw new SalarioInvalidoException("Salário base deve ser maior que zero!");
        }
        this.codigo = codigo;
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
    }

    public int getIdentificador() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public abstract double calcularRemuneracao();
}