package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    Distance location;

    public static void main(String[] args) {

        String line = "";
        String splitBy = ";";
        List<String> columns = new ArrayList<>();
        String[][] data = new String[379][];
        String option = "";
        Distance distance = new Distance(0, 0);

        try {
            // parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(
                    new FileReader("/Users/marielacordeiro/Downloads/TaxiChallenge/pontos_taxi.csv"));
            line = br.readLine();
            columns = Arrays.asList(line.split(splitBy)); // Returns a fixed-size list backed by the specified array.
            System.out.println(columns);
            int i = 0;

            while ((line = br.readLine()) != null) // returns a Boolean value
            {
                String[] details = line.split(splitBy);
                data[i] = details;// use comma as separator
                i++;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (option != "4") {
            Scanner myObj = new Scanner(System.in); // Create a Scanner object
            System.out.println(" === MENU ===\n" + "   1.  Listar todos os pontos de taxi\n"
                    + "   2.  Informar minha localização\n" + "   3.  Encontrar pontos próximos\n"
                    + "   4. Buscar pontos por logradouro\n" + "   5. Terminar programa");
            option = myObj.nextLine();

            switch (option) {
                case "1":
                    listData(data);
                    break;
                case "2":
                    setDistance(distance);
                    break;
                case "3":
                    // encontrarPontos();
                    break;
                case "4":
                    // buscarLogradouro();
                case "5":
                    System.out.println("See you!");
                    return;
                default:
                    System.out.println("That is not an option, please write a number between 1 and 4");

            }

        }

    }

    private static void listData(String[][] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.println("Dados dos pontos de taxi: " + Arrays.toString(data[i]));
        }
    }

    private static void setDistance(Distance distance) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("write your latitude city");
        String latitude = myObj.nextLine();
        distance.setLatitude(Double.parseDouble(latitude));

        System.out.println("write your longitude city");
        String longitude = myObj.nextLine();
        distance.setLongitude(Double.parseDouble(longitude));
    }
}
