package org.example.expoarrayquicksort;


import org.apache.commons.math3.distribution.ExponentialDistribution;

public class ExpoImp implements ExpoInt {

    @Override
    public double[] generateRandomNumbers(int dataSize, double mean, double variance) {
        double[] randomNumbers = new double[dataSize];
        ExponentialDistribution exponentialDistribution = new ExponentialDistribution(mean);
        for (int i = 0; i < dataSize; i++) {
            randomNumbers[i] = exponentialDistribution.sample();
        }
        return randomNumbers;
    }
}

