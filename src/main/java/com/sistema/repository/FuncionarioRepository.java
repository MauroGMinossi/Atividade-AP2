package com.sistema.repository;

import com.sistema.model.Funcionario;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioRepository {
    private final Map<Integer, Funcionario> funcionariosPorCodigo = new LinkedHashMap<>();

    public void salvar(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo. Tente novamente!!");
        }
        funcionariosPorCodigo.put(funcionario.getIdentificador(), funcionario);
    }

    public List<Funcionario> listarTodos() {
        return new ArrayList<>(funcionariosPorCodigo.values());
    }

    // Sobrecarga 1: busca por codigo
    public Funcionario buscarFuncionario(int codigo) {
        return funcionariosPorCodigo.get(codigo);
    }

    // Sobrecarga 2: busca por CPF
    public Funcionario buscarFuncionario(String cpf) {
        if (cpf == null || cpf.isBlank()) {
            return null;
        }
        for (Funcionario funcionario : funcionariosPorCodigo.values()) {
            if (cpf.equals(funcionario.getCpf())) {
                return funcionario;
            }
        }
        return null;
    }

    // Sobrecarga 3: busca por nome (exata ou parcial)
    public Funcionario buscarFuncionario(String nome, boolean buscaExata) {
        if (nome == null || nome.isBlank()) {
            return null;
        }
        String termo = nome.trim();
        for (Funcionario funcionario : funcionariosPorCodigo.values()) {
            String nomeAtual = funcionario.getNome();
            if (nomeAtual == null) {
                continue;
            }
            boolean corresponde = buscaExata
                    ? nomeAtual.equalsIgnoreCase(termo)
                    : nomeAtual.toLowerCase().contains(termo.toLowerCase());
            if (corresponde) {
                return funcionario;
            }
        }
        return null;
    }

    public boolean remover(int codigo) {
        return funcionariosPorCodigo.remove(codigo) != null;
    }
}