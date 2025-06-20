package com.literalura;

import com.literalura.service.ConsumoGutendexAPI;

public class Principal {
    public static void main(String[] args) {
        ConsumoGutendexAPI api = new ConsumoGutendexAPI();
        String resposta = api.buscarLivros("Dom Quixote");
        System.out.println(resposta);
    }


}
