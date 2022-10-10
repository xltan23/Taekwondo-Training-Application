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

    public Integer ArmsDurationCalculator(String intensity, Integer duration, Integer sets) {
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

    public Integer ArmsRepetitionCalculator(String intensity, Integer repetition, Integer sets) {
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

    public Integer CoreDurationCalculator(String intensity, Integer duration, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = duration*sets;
        } else if (intensity.equals("moderate")) {
            is = duration*sets*2.5;
        } else if (intensity.equals("high")) {
            is = duration*sets*4;
        } else if (intensity.equals("extreme")) {
            is = duration*sets*5.5;
        } else {
            
        }

        Integer intensityScore = (int)is;
        return intensityScore;
    }

    public Integer CoreRepetitionCalculator(String intensity, Integer repetition, Integer sets) {
        double is = 0;

        if (intensity.equals("low")) {
            is = repetition*sets*2.5;
        } else if (intensity.equals("moderate")) {
            is = repetition*sets*3;
        } else if (intensity.equals("high")) {
            is = repetition*sets*5;
        } else if (intensity.equals("extreme")) {
            is = repetition*sets*10;
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

    public String MessageGenerator(Integer totalIntensityScore) {
        if (totalIntensityScore <= 500) {
            return "ARE YOU EVEN TRYING??!!?!";
        } else if (totalIntensityScore > 500 && totalIntensityScore <= 1500) {
            return "Not quite enough! You might want to attempt another session...";
        } else if (totalIntensityScore > 1500 && totalIntensityScore <= 3000) {
            return "Seems like a chill session today. Aim to work more next time!";
        } else if (totalIntensityScore > 3000 && totalIntensityScore <= 5000) {
            return "Pretty decent job! Sufficient to maintain your fitness.";
        } else if (totalIntensityScore > 5000 && totalIntensityScore <= 8000) {
            return "Fantastic job! Keep up this intensity & good things are coming";
        } else if (totalIntensityScore > 8000 && totalIntensityScore <= 10000) {
            return "Excellent work! Another step closer to greatness";
        } else {
            return "Well done for hitting the maximum intensity today! +1 to Perfection";
        }
    }
}