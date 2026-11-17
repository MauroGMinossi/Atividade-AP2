package com.sistema;

import com.sistema.model.Funcionario;
import com.sistema.model.FuncionarioCLT;
import com.sistema.model.FuncionarioComissionado;
import com.sistema.model.FuncionarioPJ;
import com.sistema.repository.FuncionarioRepository;
import com.sistema.service.FuncionarioService;
import com.sistema.view.FuncionarioView;

public class Main {
    public static void main(String[] args) {
        // Inicialização das camadas (Injeção de dependências manual)
        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService service = new FuncionarioService(repository);
        FuncionarioView view = new FuncionarioView(service);

        try {
            // Criando funcionários de diferentes categorias (Polimorfismo)
            Funcionario clt = new FuncionarioCLT(1, "Ana Silva", "111.111.111-11", 5000.0, 1000.0);
            Funcionario pj = new FuncionarioPJ(2, "Bruno Costa", "222.222.222-22", 1000.0, 500.0, 4);
            Funcionario comissionado = new FuncionarioComissionado(3, "Carlos Lima", "333.333.333-33", 1000.0, 5000.0, 10.0);

            // Cadastrando no sistema através do Service
            service.cadastrarFuncionario(clt);
            service.cadastrarFuncionario(pj);
            service.cadastrarFuncionario(comissionado);

            // Exibindo dados e relatórios através da View
            view.exibirLista(service.listarFuncionarios());
            System.out.println();

            // Exemplo de cálculo da folha de pagamento total
            System.out.println("=== Total da Folha de Pagamento ===");
            System.out.println("R$ " + service.calcularTotalFolha());

        } catch (RuntimeException e) {
            view.exibirMensagem("Erro: " + e.getMessage());
        }
    }
}