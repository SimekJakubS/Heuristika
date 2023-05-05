package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Heuristika {

    /*
    * n = 500 > pocet
    * r = 350 > min pocet pred. v batohu
    * k 9500  > min hmotnost
    */

    final int n = 500;
    final int r = 350;
    final int k = 9500;

    ArrayList<Integer> hmotnosti = new ArrayList<Integer>();
    ArrayList<Integer> ceny = new ArrayList<Integer>();

    public Heuristika() {
        nacitajUdaje("H2_c.txt", ceny);
        nacitajUdaje("H2_a.txt", hmotnosti);
        vkladajDoBatohu(hmotnosti, ceny);
    }

    private void vkladajDoBatohu(ArrayList<Integer> hmotnosti, ArrayList<Integer> ceny) {
        int indexMin;
        int hmotnostBatohu = 0;
        int cenaBatohu = 0;
        int pocetPredmetov = 0;

        try {
            FileWriter myWriter = new FileWriter("batoh.txt");

            while (hmotnostBatohu <= k && pocetPredmetov <= r) {

                indexMin = ceny.indexOf(Collections.min(ceny));
                hmotnostBatohu = hmotnostBatohu + hmotnosti.get(indexMin);
                cenaBatohu = cenaBatohu + ceny.get(indexMin);
                pocetPredmetov++;

                myWriter.write(ceny.get(indexMin) + "\n");

                ceny.remove(indexMin);
                hmotnosti.remove(indexMin);
            }
            myWriter.write("--Hmotnost: " + hmotnostBatohu + "\n");
            myWriter.write("--Pocet predmetov: " + pocetPredmetov + "\n");
            myWriter.write("--Cena: " + cenaBatohu + "\n");

            System.out.println("--Hmotnost: " + hmotnostBatohu);
            System.out.println("--Pocet predmetov: " + pocetPredmetov);
            System.out.println("--Cena: " + cenaBatohu);

            myWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private void nacitajUdaje(String subor, ArrayList<Integer> output) {

        try {
            File suborInput = new File(subor);
            Scanner myReader = new Scanner(suborInput);
            while (myReader.hasNextInt()) {
                int data = myReader.nextInt();
                output.add(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}