package org.example.expoarrayquicksort;
public class Matrixe{
    public static double[][][][] matrixApproach(int[] dataSizes, double[] means, double[] variances) {
        double[][][][] randomNumbersMatrices = new double[dataSizes.length][means.length][variances.length][];
        // Generate random numbers for each combination of mean, variance, and data siz
       ExpoInt expoInt = new ExpoImp() ;
        for (int i = 0; i < dataSizes.length; i++) {
            for (int j = 0; j < means.length; j++) {
                for (int k = 0; k < variances.length; k++) {
                    randomNumbersMatrices[i][j][k] =
                            expoInt.generateRandomNumbers(dataSizes[i], means[j], variances[k]);
                }
            }
        }
        return randomNumbersMatrices;
    }
}
