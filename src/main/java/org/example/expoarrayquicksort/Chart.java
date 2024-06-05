package org.example.expoarrayquicksort;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Chart extends Application {
    double[] means = {1.0, 2.0, 3.0};
    double[] variances = {0.25, 0.5, 0.75};
    int[] dataSizes = {120000, 140000, 160000};
    long timeOfSortAverage;
    long timeOfSortBest;
    long timeOfSortWorst;
    long totalaverage;
    long totalBest;
    long totalworst;
    public static void appendToCSV(String fileName, String fileNameBest,
                                   String fileNameWorst, long timeOfSortAverage, long timeOfSortBest, long timeOfSortWorst) {
        String csvFileName = "resultaTimeOfsort/sort_times.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName, true))) {
            writer.write("Average Time taken to sort " + fileName + ": " + timeOfSortAverage +
                    " milliseconds");
            writer.newLine();
            writer.write("Best Time taken to sort " + fileNameBest + ": " + timeOfSortBest +
                    " milliseconds");
            writer.newLine();
            writer.write("Worst Time taken to sort " + fileNameWorst + ": " + timeOfSortWorst +
                    " milliseconds");
            writer.newLine();
            writer.write("\n");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void visualise(String fileName, long timeOfSortAverage,
                                 long timeOfSortBest, long timeOfSortWorst) {
        String csvFileName = "resultaTimeOfsort/chart.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileName, true))) {
            writer.write(fileName + " : " + "averageCase : " + timeOfSortAverage + " ms " + "bestCase" +
                    " : " + timeOfSortBest + " ms " + "worstCase : " + timeOfSortWorst + " ms ");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage stage) {
        // Create the X and Y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Data Size and Case");
        yAxis.setLabel("Time (ms)");

        // Create the bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sorting Time Comparison");

        // Create data series for best, average, and worst cases
        XYChart.Series<String, Number> bestSeries = new XYChart.Series<>();
        bestSeries.setName("Best Case");
        XYChart.Series<String, Number> averageSeries = new XYChart.Series<>();
        averageSeries.setName("Average Case");
        XYChart.Series<String, Number> worstSeries = new XYChart.Series<>();
        worstSeries.setName("Worst Case");

        for (int i = 0; i < dataSizes.length; i++) {
            totalaverage = 0;
            totalBest = 0;
            totalworst = 0;

            for (int j = 0; j < means.length; j++) {
                for (int k = 0; k < variances.length; k++) {
                    String directoryGenerate = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataAverageCaseFiles/";
                    String directoryBestCase = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataBestCaseFiles/";
                    String directoryWorstCase = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataWorstCaseFiles/";

                    String fileName = "results_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                    String fileNameBest = "Bestresults_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                    String fileNameWorst = "Worstresults_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                    timeOfSortAverage = FilesRead.calculeTimeOfSort(fileName, directoryGenerate);
                    timeOfSortBest = FilesRead.calculeTimeOfSort(fileNameBest, directoryBestCase);
                    timeOfSortWorst = FilesRead.calculeTimeOfSort(fileNameWorst, directoryWorstCase);

                    totalaverage += timeOfSortAverage;
                    totalBest += timeOfSortBest;
                    totalworst += timeOfSortWorst;
                }
            }
            System.out.println("total time sort best pour data size "+dataSizes[i]+" est: "+totalBest);
            System.out.println("total time sort average pour data size "+dataSizes[i]+" est: "+totalaverage);
            System.out.println("total time sort worst pour data size "+dataSizes[i]+" est: "+totalworst);
            // Add data to the series
            bestSeries.getData().add(new XYChart.Data<>(" "+ dataSizes[i], totalBest));
            averageSeries.getData().add(new XYChart.Data<>(" " + dataSizes[i], totalaverage));
            worstSeries.getData().add(new XYChart.Data<>(" " + dataSizes[i], totalworst));
        }

        // Add series to the chart
        barChart.getData().addAll(bestSeries, averageSeries, worstSeries);

        // Create and set the scene
        Scene scene = new Scene(barChart, 1200, 800);
        stage.setScene(scene);
        stage.show();
    }

        public static void main(String[] args) throws IOException {
            double[] means = {1.0, 2.0, 3.0};
            double[] variances = {0.25, 0.5, 0.75};
            int[] dataSizes = {120000, 140000, 160000};
            long timeOfSortAverage ;
            long timeOfSortBest ;
            long timeOfSortWorst ;

            // Utilisez un tableau pour stocker les résultats
            double[][][][] result = Matrixe.matrixApproach(dataSizes, means, variances);
            // Appel de la méthode pour écrire la matrice dans des fichiers CSV
            FileWrite.writeMatrixToCSV(result, dataSizes, means, variances);
            for (int i = 0; i < dataSizes.length; i++) {
                for (int j = 0; j < means.length; j++) {
                    for (int k = 0; k < variances.length; k++) {
                        String directoryGenerate = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataAverageCaseFiles/";
                        String directoryBestCase = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataBestCaseFiles/";
                        String directoryWorstCase = "C:/Users/admin/IdeaProjects/Expo-array-quicksort/DataWorstCaseFiles/";

                        String fileName = "results_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                        String fileNameBest = "Bestresults_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";
                        String fileNameWorst = "Worstresults_datasize_" + dataSizes[i] + "_mean_" + means[j] + "_variance_" + variances[k] + ".csv";

                        // Lire les données à partir du fichier CSV et les stocker dans un tableau
                        double[] array = FilesRead.readCSV(directoryGenerate, fileName);
                        // Convertir les données en meilleur cas en utilisant le tri rapide
                        FilesRead.convertingDataToBestCase(directoryGenerate, directoryBestCase, fileName, fileNameBest);
                        // Convertir les données en pire cas en utilisant le tri rapide
                        FilesRead.convertingDataToWorstCase(directoryGenerate, directoryWorstCase, fileName, fileNameWorst);
                        // Mesurer le temps de tri pour chaque cas
                        timeOfSortAverage = FilesRead.calculeTimeOfSort(fileName, directoryGenerate);
                        timeOfSortBest = FilesRead.calculeTimeOfSort(fileNameBest, directoryBestCase);
                        timeOfSortWorst = FilesRead.calculeTimeOfSort(fileNameWorst, directoryWorstCase);



                        // Créer le répertoire s'il n'existe pas
                        File directory = new File("resultaTimeOfsort");
                        if (!directory.exists()) {
                            directory.mkdirs();
                        }
                        // Écrire les résultats dans les fichiers CSV
                        appendToCSV(fileName, fileNameBest, fileNameWorst, timeOfSortAverage, timeOfSortBest, timeOfSortWorst);
                        // Visualiser les résultats
                        String fileName1 = " datasize : " + dataSizes[i] + " mean : " + means[j] + " variance : " + variances[k];
                        visualise(fileName1, timeOfSortAverage, timeOfSortBest, timeOfSortWorst);
                    }
                }





            }
                launch(args);
            }
        }


