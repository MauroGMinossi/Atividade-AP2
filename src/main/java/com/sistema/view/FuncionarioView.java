package com.sistema.view;

import com.sistema.model.Funcionario;
import com.sistema.service.FuncionarioService;

import java.util.List;

public class FuncionarioView {
    private final FuncionarioService service;

    public FuncionarioView(FuncionarioService service) {
        this.service = service;
    }

    public void exibirLista(List<Funcionario> funcionarios) {
        if (funcionarios == null || funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
            return;
        }
        for (Funcionario f : funcionarios) {
            System.out.println(service.exibirDadosCompletos(f.getIdentificador()));
        }
    }

    public void exibirRelatorio(String relatorio) {
        System.out.println(relatorio);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}