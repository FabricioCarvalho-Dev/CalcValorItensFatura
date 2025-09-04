package com.exemplo.calculadora;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CalculadoraController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/calcular")
    public String calcular(@RequestParam("valores") String valores, Model model) {
        String[] linhas = valores.split("\\r?\\n");
        double total = 0;

        for (String linha : linhas) {
            linha = linha.trim().replace(",", ".");
            String[] partes = linha.split(" ");
            try {
                String ultimo = partes[partes.length - 1];
                double valor = Double.parseDouble(ultimo);
                total += valor;
            } catch (NumberFormatException e) {
                // ignora linhas sem número
            }
        }

        model.addAttribute("total", total);
        return "index";
    }
    @PostMapping("/calcularPercentual")
    public String calcularPercentual(@RequestParam double valor1,
                                     @RequestParam double valor2,
                                     Model model) {
        if (valor2 == 0) {
            model.addAttribute("resultado", "Não é possível dividir por zero!");
        } else {
            double percentual = (valor1 / valor2) * 100;
            model.addAttribute("resultado", valor1 + " é " + String.format("%.2f", percentual) + "% de " + valor2);
        }
        return "percentual";
    }

    @GetMapping("/percentual")
    public String mostrarPercentual() {
        return "percentual";
    }

}