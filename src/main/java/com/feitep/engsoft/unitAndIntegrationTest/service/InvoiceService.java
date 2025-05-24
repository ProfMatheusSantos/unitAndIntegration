package com.feitep.engsoft.unitAndIntegrationTest.service;

public class InvoiceService {

    public double calcularValorFinal(double valorBase) {
        double taxa = 0.0;
        double valorFinal = 0.0;

        try {
            if (valorBase < 0) {
                throw new IllegalArgumentException("O valor da fatura não pode ser negativo.");
            }

            if (valorBase <= 1000) {
                taxa = 0.05; // 5%
            } else if (valorBase <= 5000) {
                taxa = 0.10; // 10%
            } else {
                taxa = 0.15; // 15%
            }

            valorFinal = valorBase + (valorBase * taxa);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
        }

        return valorFinal;
    }
}
