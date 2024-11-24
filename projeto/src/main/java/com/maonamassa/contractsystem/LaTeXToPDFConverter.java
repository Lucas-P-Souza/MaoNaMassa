package com.maonamassa.contractsystem;

import java.io.*;

public class LaTeXToPDFConverter {

    private String valorInteiro;
    private String valorDecimal;

    public void convertToPDF(Contrato contrato) {
        // Obtém o diretório da classe atual
        String currentDir = System.getProperty("user.dir");
        String baseDir = currentDir + File.separator + "MaoNaMassa";

        // Caminho do arquivo .tex original (template)
        String templateFilePath = baseDir + File.separator + "template.tex";

        // Caminho do arquivo .tex modificado
        String modifiedFilePath = baseDir + File.separator + contrato.getId() + "_contrato_final.tex";

        // Lê o conteúdo do arquivo template.tex
        String content;
        try {
            content = readTemplateFile(templateFilePath);
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo template .tex: " + e.getMessage());
            return;
        }

        // Substitui os placeholders pelas variáveis do contrato
        NumeroPorExtenso numero = new NumeroPorExtenso(contrato.getValorCombinado());
        valorInteiro = numero.getParteInteiraExtenso();
        valorDecimal = numero.getParteDecimalExtenso();

        content = content.replace("(Nome da parte contratante)", contrato.getNomeContratante())
                         .replace("(numero do CPF/CNPJ)", contrato.getCpfCnpjContratante())
                         .replace("(endereco)", contrato.getEnderecoContratante())
                         .replace("(Nome da parte contratada)", contrato.getNomeProfissional())
                         .replace("(numero do CPF/CNPJ)", contrato.getCpfCnpjProfissional())
                         .replace("(endereco)", contrato.getEnderecoProfissional())
                         .replace("(descrever detalhadamente os serviços a serem prestados)", contrato.getDescricaoDetalhada())
                         .replace("valor_cheio", contrato.getValorCombinado())
                         .replace("valor_inteiro", valorInteiro)
                         .replace("valor_decimal", valorDecimal);

        // Salva o arquivo .tex modificado
        try {
            saveToFile(modifiedFilePath, content);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o arquivo modificado: " + e.getMessage());
            return;
        }

        // Comando para executar pdflatex no arquivo modificado
        String command = "pdflatex -output-directory=" + baseDir + " " + modifiedFilePath;

        try {
            // Inicia o processo para executar o pdflatex
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Lê a saída do processo
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor(); // Aguarda a conclusão do processo

            if (exitCode == 0) {
                System.out.println("PDF gerado com sucesso! Arquivo disponível em: " + baseDir);
            } else {
                System.out.println("Erro na geração do PDF. Código de saída: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao executar o comando pdflatex: " + e.getMessage());
        }
    }

    // Método para ler o conteúdo do arquivo template .tex
    private String readTemplateFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    // Método para salvar o conteúdo no arquivo .tex modificado
    private void saveToFile(String filePath, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        }
    }
}
