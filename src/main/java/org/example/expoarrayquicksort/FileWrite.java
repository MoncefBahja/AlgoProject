package org.example.expoarrayquicksort;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileWrite {
    public static void writeMatrixToCSV(double[][][][] randomNumbersMatrices, int[] dataSizes, double[] means, double[] variances) {
        // Création du dossier pour stocker les fichiers s'il n'existe pas
        File directory = new File("DataAverageCaseFiles");
        if (!directory.exists()) {
            directory.mkdirs(); // Crée le répertoire et ses parents s'ils n'existent pas
        }
        for (int i = 0; i < dataSizes.length; i++) {
            for (int j = 0; j < means.length; j++) {
                for (int k = 0; k < variances.length; k++) {
                    String fileName = "results_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                    File file = new File(directory, fileName);
                    try {
                        // Création d'un FileWriter et d'un PrintWriter pour écrire dans le fichier CSV
                        FileWriter fileWriter = new FileWriter(file);
                        PrintWriter printWriter = new PrintWriter(fileWriter);
                        // Récupération de la liste de nombres aléatoires à partir de la matrice
                        double[] randomNumbersArray = randomNumbersMatrices[i][j][k];

                        // Écriture de chaque nombre aléatoire dans le fichier
                        for (double randomNumber : randomNumbersArray) {
                            printWriter.println(randomNumber);
                        }

                        // Fermeture du PrintWriter après l'écriture dans le fichier
                        printWriter.close();
                        System.out.println("File '" + file.getAbsolutePath() + "' created successfully.");
                    } catch (IOException e) {
                        // En cas d'erreur lors de l'écriture dans le fichier, une exception est levée
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
