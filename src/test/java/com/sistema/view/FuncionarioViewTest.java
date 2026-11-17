package com.sistema.view;

import com.sistema.model.FuncionarioCLT;
import com.sistema.repository.FuncionarioRepository;
import com.sistema.service.FuncionarioService;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FuncionarioViewTest {

    @Test
    void deveExibirListaERelatorioNoConsole() {
        FuncionarioRepository repository = new FuncionarioRepository();
        FuncionarioService service = new FuncionarioService(repository);
        service.cadastrarFuncionario(new FuncionarioCLT(1, "Ana", "111.111.111-11", 5000.0, 500.0));
        FuncionarioView view = new FuncionarioView(service);

        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        view.exibirLista(service.listarFuncionarios());
        view.exibirRelatorio(service.gerarRelatorioFolha());

        String texto = saida.toString();
        assertTrue(texto.contains("Ana"));
        assertTrue(texto.contains("5500.00"));
    }
}