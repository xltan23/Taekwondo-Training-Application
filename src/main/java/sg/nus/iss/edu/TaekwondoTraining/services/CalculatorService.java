package sg.nus.iss.edu.TaekwondoTraining.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public Integer PoomsaeDurationCalculator(String intensity, Integer duration, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = duration*sets*0.7;
        } else if (intensity.equals("moderate")) {
            is = duration*sets;
        } else if (intensity.equals("high")) {
            is = duration*sets*1.2;
        } else if (intensity.equals("extreme")) {
            is = duration*sets*1.5;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }

    public Integer PoomsaeRepetitionCalculator(String intensity, Integer repetition, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = repetition*sets*0.7;
        } else if (intensity.equals("moderate")) {
            is = repetition*sets;
        } else if (intensity.equals("high")) {
            is = repetition*sets*1.2;
        } else if (intensity.equals("extreme")) {
            is = repetition*sets*1.5;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }

    public Integer WorkoutDurationCalculator(String intensity, Integer duration, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = duration*sets;
        } else if (intensity.equals("moderate")) {
            is = duration*sets*1.5;
        } else if (intensity.equals("high")) {
            is = duration*sets*2;
        } else if (intensity.equals("extreme")) {
            is = duration*sets*2.5;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }

    public Integer WorkoutRepetitionCalculator(String intensity, Integer repetition, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = repetition*sets;
        } else if (intensity.equals("moderate")) {
            is = repetition*sets*2.5;
        } else if (intensity.equals("high")) {
            is = repetition*sets*6;
        } else if (intensity.equals("extreme")) {
            is = repetition*sets*15;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }

    public Integer RunningDistanceCalculator(String intensity, Integer duration, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = duration*sets*0.5;
        } else if (intensity.equals("moderate")) {
            is = duration*sets;
        } else if (intensity.equals("high")) {
            is = duration*sets*1.5;
        } else if (intensity.equals("extreme")) {
            is = duration*sets*2.5;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }
}