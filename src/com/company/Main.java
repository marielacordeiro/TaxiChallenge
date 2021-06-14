package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // atributos da classe main
        String data, nome, telefone, logradouro, latitude, longitude;
        int codigo, numero;
        double distancia = 0.0;
        String line = "";
        String splitBy = ";";
        String option = "";
        Pontos[] arrayPontos = new Pontos[379];
        List<String> columns = new ArrayList<>();
        Double[] localUser = new Double[2];

        try {
            // lendo o arquivo csv com a classe BufferedReader utlizando o método readLine()
            BufferedReader br = new BufferedReader(new FileReader("../pontos_taxi.csv"));
            line = br.readLine();
            columns = Arrays.asList(line.split(splitBy)); // lista os títulos que estão na primeira linha do arquivo
            int i = 0;

            while ((line = br.readLine()) != null) // lê o arquivo até não haver mais linhas
            {
                String[] details = line.split(splitBy); // divido a string pelos pontos e vírgulas
                // atribui os valores do csv no objeto ponto
                data = details[0];
                codigo = Integer.parseInt(details[1]);
                nome = details[2];
                telefone = details[3];
                logradouro = details[4];
                numero = Integer.parseInt(details[5].replaceAll("^\"|\"$", ""));
                latitude = (details[6]).replaceAll("^\"|\"$", "");
                longitude = (details[7]).replaceAll("^\"|\"$", "");

                // instancia o objeto ponto
                Pontos ponto = new Pontos(data, codigo, nome, telefone, logradouro, numero, latitude, longitude,
                        distancia);

                arrayPontos[i] = ponto;
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (option != "4") { // criação do menu e validação das opções
            Scanner myObj = new Scanner(System.in);
            System.out.println(" === MENU ===\n" + "   1.  Listar todos os pontos de taxi\n"
                    + "   2.  Informar minha localização\n" + "   3.  Encontrar pontos próximos\n"
                    + "   4. Buscar pontos por logradouro\n" + "   5. Terminar programa");
            option = myObj.nextLine();

            switch (option) {
                case "1":
                    listData(arrayPontos);
                    break;
                case "2":
                    setDistance(localUser);
                    break;
                case "3":
                    encontrarPontos(localUser, arrayPontos);
                    break;
                case "4":
                    buscarLogradouro(arrayPontos);
                    break;
                case "5":
                    System.out.println("Programa finalizado, até logo!");
                    return;
                default:
                    System.out.println("Esta não é uma opção válida, por favor informe um número entre 1 e 4.");

            }
        }
    }

    // método que lista todos os dados do arquivo
    private static void listData(Pontos[] arrayPontos) {
        for (int i = 0; i < arrayPontos.length; i++) {
            System.out.println("Dados dos pontos de taxi: " + arrayPontos[i]);
        }
    }

    // método que armazena as coordenadas geográficas do usuário
    private static void setDistance(Double[] localUser) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Informe sua latitude: ");
        String latitude = myObj.nextLine();
        latitude = latitude.replaceAll(",", ".");
        localUser[0] = Double.parseDouble(latitude);

        System.out.println("Informe sua longitude: ");
        String longitude = myObj.nextLine();
        longitude = longitude.replaceAll(",", ".");
        localUser[1] = Double.parseDouble(longitude);

        System.out.println("Localização armazenada.");
    }

    // agrupa o processo de definir os 3 pontos de taxi mais perto do usuário
    private static void encontrarPontos(Double[] localUser, Pontos[] arrayPontos) {
        defineDistancia(localUser, arrayPontos);
        ordena(arrayPontos);

        for (int i = 0; i < 3; i++) {
            System.out.println("Os 3 pontos de taxi mais perto de você são: " + arrayPontos[i].getNome());
        }
    }

    // calcula a distância entre o usuário e cada ponto de taxi
    public static void defineDistancia(Double[] localUser, Pontos[] arrayPontos) {
        Double newinputLat = localUser[0];
        Double newinputLong = localUser[1];
        for (int i = 0; i < arrayPontos.length; i++) {
            Double pontoLat = Double.parseDouble(arrayPontos[i].getLatitude().replaceAll(",", "."));
            Double pontoLong = Double.parseDouble(arrayPontos[i].getLongitude().replace(",", "."));
            arrayPontos[i].setDistancia(arrayPontos[i].Haversine(newinputLat, newinputLong, pontoLat, pontoLong));
        }
    }

    // ordena as distâncias do método defineDistancia com o algorítmo bubble sort
    public static void ordena(Pontos[] arrayPontos) {
        Pontos aux;
        for (int k = 0; k < arrayPontos.length - 1; k++) {
            for (int i = 0; i < arrayPontos.length - 1 - k; i++) {
                if (arrayPontos[i].getDistancia() > arrayPontos[i + 1].getDistancia()) {
                    aux = arrayPontos[i];
                    arrayPontos[i] = arrayPontos[i + 1];
                    arrayPontos[i + 1] = aux;
                }
            }
        }
    }

    // filtra e exibe os pontos com base no logradourodigitado
    private static void buscarLogradouro(Pontos[] arrayPontos) {
        System.out.println("Informe o nome do logradouro desejado: ");
        Scanner in = new Scanner(System.in);
        String endereco = in.nextLine();

        for (int i = 0; i < arrayPontos.length; i++) {
            if (arrayPontos[i].getLogradouro().contains(endereco.toUpperCase())) {
                System.out.println(
                        "Nome do local: " + arrayPontos[i].getNome() + ", Endereço: " + arrayPontos[i].getLogradouro());
            }
        }
    }
}
