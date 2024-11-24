package com.maonamassa.contractsystem;

//essa classe é responsável por converter um número decimal em uma string que representa esse número por extenso
//ela é usada para gerar contratos em PDF
//ela divide o número em parte inteira e parte decimal e converte cada parte separadamente
public class NumeroPorExtenso {

    //variáveis que armazenam a parte inteira e a parte decimal do número por extenso
    private String parteInteiraExtenso;
    private String parteDecimalExtenso;

    //arrays que contém as strings que representam os números de 0 a 9, de 10 a 90, de 10 a 19 e de 100 a 900
    //essas strings são usadas para converter os números em suas representações por extenso
    private static final String[] UNIDADES = {"", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
    private static final String[] DEZENAS = {"", "dez", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
    private static final String[] DEZ_A_DEZENOVE = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
    private static final String[] CENTENAS = {"", "cem", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"};

    //construtor que recebe um número decimal e converte a parte inteira e a parte decimal para extenso
    //obs: o número deve ser passado como uma string, com a parte inteira separada da parte decimal por um ponto
    public NumeroPorExtenso(String numero) {
        //divide o número em parte inteira e parte decimal usando o ponto como separador
        String[] partes = numero.split("\\.");
        int parteInteira = Integer.parseInt(partes[0]);
        int parteDecimal = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
        this.parteInteiraExtenso = converteParaExtenso(parteInteira);
        this.parteDecimalExtenso = (parteDecimal > 0) ? converteParaExtenso(parteDecimal) : "zero";
    }

    //método que converte um número inteiro para extenso
    //ele é chamado pelo construtor para converter a parte inteira e a parte decimal do número para extenso
    private static String converteParaExtenso(int numero) {

        if (numero == 0) {
            return "zero";
        } 
        else if (numero < 10) {
            return UNIDADES[numero];
        } 
        else if (numero < 20) {
            return DEZ_A_DEZENOVE[numero - 10];
        } 
        else if (numero < 100) {
            return DEZENAS[numero / 10] + (numero % 10 != 0 ? " e " + UNIDADES[numero % 10] : "");
        } 
        else if (numero < 1000) {
            return (numero == 100 ? "cem" : CENTENAS[numero / 100]) + 
                   (numero % 100 != 0 ? " e " + converteParaExtenso(numero % 100) : "");
        } 
        else if (numero < 1000000) {
            int milhar = numero / 1000;
            int resto = numero % 1000;
            return (milhar == 1 ? "mil" : converteParaExtenso(milhar) + " mil") + 
                   (resto != 0 ? " e " + converteParaExtenso(resto) : "");
        } 
        else {
            int milhao = numero / 1000000;
            int resto = numero % 1000000;
            return (milhao == 1 ? "um milhao" : converteParaExtenso(milhao) + " milhoes") + 
                   (resto != 0 ? " e " + converteParaExtenso(resto) : "");
        }
    }

    //gets das partes inteira e decimal do número por extenso
    //não há sets pois essas variáveis são definidas no construtor e não devem ser alteradas
    public String getParteInteiraExtenso() {
        return parteInteiraExtenso;
    }

    public String getParteDecimalExtenso() {
        return parteDecimalExtenso;
    }

}
