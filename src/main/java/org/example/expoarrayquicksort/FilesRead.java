package org.example.expoarrayquicksort;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilesRead {
    // Méthode pour lire les données à partir d'un fichier CSV et les stocker dans un tableau
    public static double[] readCSV(String directory, String filename) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(directory + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());}
        }
        double[] data = new double[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            data[i] = Double.parseDouble(lines.get(i));
        }
        return data;
    }


    // Méthode pour écrire les données d'un tableau dans un fichier CSV dans un répertoire spécifique
    public static void writeCSV(double[] data, String directory, String filename) throws IOException {
        // Vérifie si le répertoire existe, s'il n'existe pas, le crée
        File dir = new File(directory);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("Could not create directory: " + directory);
            }
        }

        // Écriture des données dans le fichier CSV
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + "/" + filename))) {
            for (double value : data) {
                writer.write(Double.toString(value));
                writer.newLine();
            }
        }
    }

    // Méthode pour convertir les données en meilleur cas en utilisant l'algorithme de tri rapide (quicksort)
    public static void convertingDataToBestCase(String directoryGenerate, String directoryBestCase,
                         String nameOfFileAverageCase, String nameOfFileBestCase) throws IOException {
        double[] dataArray = readCSV(directoryGenerate, nameOfFileAverageCase);
       // double[] sortedArray = new QuickSort().sort(dataArray);
        double[] sortedArray = SortAscending.sort(dataArray);

        writeCSV(sortedArray, directoryBestCase, nameOfFileBestCase);
    }

    // Méthode pour convertir les données en pire cas en utilisant l'algorithme de tri rapide (quicksort)
    public static void convertingDataToWorstCase(String directoryGenerate, String directoryWorstCase,
                   String nameOfFileAverageCase, String nameOfFileWorstCase) throws IOException {
        double[] dataArray = readCSV(directoryGenerate, nameOfFileAverageCase);
       // double[] sortedArray = new Sortending().sort(dataArray);
        double[] sortedArray = SortDescending.sort(dataArray);

        writeCSV(sortedArray, directoryWorstCase, nameOfFileWorstCase);
    }

    // Méthode pour calculer le temps d'exécution de l'algorithme de tri pour une liste donnée
    public static long calculeTimeOfSort(String nameOfCsv, String directoryGenerate) {
        long time = -1;
        int numRepetitions = 20;
        long totalTime = 0;
        long averageTime =0 ;

        try {
            double[] dataArray = readCSV(directoryGenerate, nameOfCsv);
            for (int i = 0; i < numRepetitions; i++) {

                long t0 = System.nanoTime();
                //  long t0 = System.currentTimeMillis();
                // Arrays.sort(dataArray);
                SortAscending.sort(dataArray);
                //  SortDescending.sort(dataArray) ;
                long t1 = System.nanoTime();
                //   long t1 = System.currentTimeMillis();
                time = (t1 - t0)/1000000 ;
                totalTime+= time;
            }
             averageTime= totalTime / numRepetitions;
           // System.out.println("Average time for 10 Repetitions: "+ nameOfCsv+" : " + averageTime + " ms");
         //   System.out.println("Sorting time for " + nameOfCsv + ": " + time + " ms");
            } catch(IOException e){
                System.err.println("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
            }

        return averageTime;
    }



}
