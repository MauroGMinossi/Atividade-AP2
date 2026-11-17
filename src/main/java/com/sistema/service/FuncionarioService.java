package com.sistema.service;

import com.sistema.model.Funcionario;
import com.sistema.model.Tributavel;
import com.sistema.repository.FuncionarioRepository;

import java.util.List;
import java.util.Locale;

public class FuncionarioService {
    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repositório não pode ser nulo!");
        }
        this.repository = repository;
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        if (funcionario == null) {
            throw new IllegalArgumentException("Funcionário não pode ser nulo! Faça novamente");
        }
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do funcionário é obrigatório!");
        }
        if (!cpfValido(funcionario.getCpf())) {
            throw new IllegalArgumentException("CPF inválido.");
        }
        repository.salvar(funcionario);
    }

    private boolean cpfValido(String cpf) {
        if (cpf == null) {
            return false;
        }
        String digitos = cpf.replaceAll("\\D", "");
        return digitos.length() == 11;
    }

    public List<Funcionario> listarFuncionarios() {
        return repository.listarTodos();
    }

    public Funcionario buscarPorCodigo(int codigo) {
        return repository.buscarFuncionario(codigo);
    }

    public Funcionario buscarPorCpf(String cpf) {
        return repository.buscarFuncionario(cpf);
    }

    public Funcionario buscarPorNome(String nome, boolean buscaExata) {
        return repository.buscarFuncionario(nome, buscaExata);
    }

    public boolean removerFuncionario(int codigo) {
        return repository.remover(codigo);
    }

    public double calcularRemuneracaoFuncionario(int codigo) {
        Funcionario funcionario = repository.buscarFuncionario(codigo);
        if (funcionario == null) {
            return 0.0;
        }
        return funcionario.calcularRemuneracao();
    }

    public double calcularTotalFolha() {
        return repository.listarTodos().stream()
                .mapToDouble(Funcionario::calcularRemuneracao)
                .sum();
    }

    public String exibirDadosCompletos(int codigo) {
        Funcionario f = repository.buscarFuncionario(codigo);
        if (f == null) {
            return "Funcionário não encontrado.";
        }
        double tributos = (f instanceof Tributavel tributavel)
                ? tributavel.calcularTributos()
                : 0.0;

        return String.format(
                Locale.US,
                "Código: %d | Nome: %s | CPF: %s | Tipo: %s | Salário Base: %.2f | Remuneração: %.2f | Tributos: %.2f",
                f.getIdentificador(),
                f.getNome(),
                f.getCpf(),
                f.getClass().getSimpleName(),
                f.getSalarioBase(),
                f.calcularRemuneracao(),
                tributos
        );
    }

    public String gerarRelatorioFolha() {
        List<Funcionario> funcionarios = repository.listarTodos();
        if (funcionarios.isEmpty()) {
            return "Nenhum funcionário cadastrado.";
        }

        StringBuilder sb = new StringBuilder("=== RELATÓRIO DE FOLHA DE PAGAMENTO ===\n");
        for (Funcionario f : funcionarios) {
            sb.append(String.format(
                    Locale.US,
                    "- [%d] %s (%s) -> Remuneração: R$ %.2f%n",
                    f.getIdentificador(),
                    f.getNome(),
                    f.getClass().getSimpleName(),
                    f.calcularRemuneracao()
            ));
        }
        sb.append(String.format(Locale.US, "Total da Folha: R$ %.2f", calcularTotalFolha()));
        return sb.toString();
    }
}