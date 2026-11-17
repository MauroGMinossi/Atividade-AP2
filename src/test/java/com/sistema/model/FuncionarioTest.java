package com.sistema.model;

import com.sistema.exception.SalarioInvalidoException;
import com.sistema.exception.CodigoIdentificadorInvalidoException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FuncionarioTest {

    @Test
    void deveLancarExcecaoQuandoCodigoIdentificadorForZeroOuNegativo() {
        assertThrows(CodigoIdentificadorInvalidoException.class,
                () -> new FuncionarioCLT(0, "Ana", "111.111.111-11", 5000.0, 500.0));
        assertThrows(CodigoIdentificadorInvalidoException.class,
                () -> new FuncionarioPJ(-1, "Bruno", "222.222.222-22", 2000.0, 500.0, 2));
    }

    @Test
    void deveLancarExcecaoQuandoSalarioBaseForZeroOuNegativo() {
        assertThrows(SalarioInvalidoException.class,
                () -> new FuncionarioCLT(1, "Ana", "111.111.111-11", 0.0, 500.0));
        assertThrows(SalarioInvalidoException.class,
                () -> new FuncionarioComissionado(2, "Carlos", "333.333.333-33", -1000.0, 10000.0, 10.0));
    }

    @Test
    void deveCalcularRemuneracaoDeFuncionarioCLT() {
        Funcionario clt = new FuncionarioCLT(1, "Ana", "111.111.111-11", 5000.0, 1000.0);
        assertEquals(6000.0, clt.calcularRemuneracao(), 0.01);
    }

    @Test
    void deveCalcularRemuneracaoDeFuncionarioPJ() {
        Funcionario pj = new FuncionarioPJ(2, "Bruno", "222.222.222-22", 1000.0, 500.0, 4);
        assertEquals(3000.0, pj.calcularRemuneracao(), 0.01);
    }

    @Test
    void deveCalcularRemuneracaoDeFuncionarioComissionado() {
        Funcionario comissionado = new FuncionarioComissionado(3, "Carlos", "333.333.333-33", 1000.0, 5000.0, 10.0);
        assertEquals(1500.0, comissionado.calcularRemuneracao(), 0.01);
    }

    @Test
    void deveCalcularFolhaComPolimorfismo() {
        List<Funcionario> funcionarios = List.of(
                new FuncionarioCLT(1, "Ana", "111.111.111-11", 5000.0, 1000.0),
                new FuncionarioPJ(2, "Bruno", "222.222.222-22", 1000.0, 500.0, 4),
                new FuncionarioComissionado(3, "Carlos", "333.333.333-33", 1000.0, 5000.0, 10.0)
        );

        double totalFolha = funcionarios.stream()
                .mapToDouble(Funcionario::calcularRemuneracao)
                .sum();

        assertEquals(10500.0, totalFolha, 0.01);
    }
}