package com.sistema.repository;

import com.sistema.model.Funcionario;
import com.sistema.model.FuncionarioCLT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FuncionarioRepositoryTest {
    private FuncionarioRepository repository;
    private Funcionario ana;

    @BeforeEach
    void setUp() {
        repository = new FuncionarioRepository();
        ana = new FuncionarioCLT(1, "Ana Silva", "111.111.111-11", 5000.0, 500.0);
        repository.salvar(ana);
    }

    @Test
    void deveBuscarPorCodigoCpfENomeComSobrecarga() {
        assertEquals(ana, repository.buscarFuncionario(1));
        assertEquals(ana, repository.buscarFuncionario("111.111.111-11"));
        assertEquals(ana, repository.buscarFuncionario("Ana Silva", true));
        assertEquals(ana, repository.buscarFuncionario("ana", false));
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {
        assertNull(repository.buscarFuncionario(99));
        assertNull(repository.buscarFuncionario("000.000.000-00"));
        assertNull(repository.buscarFuncionario("Inexistente", true));
    }

    @Test
    void deveRemoverFuncionarioExistente() {
        assertTrue(repository.remover(1));
        assertTrue(repository.listarTodos().isEmpty());
        assertFalse(repository.remover(1));
    }
}