package sg.nus.iss.edu.TaekwondoTraining.services;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    
    // Low Intensity Duration Calculator (Poomsae)
    public Integer PoomsaeLowDurationCalculator(Integer duration, Integer sets) {
        double is = 0;
        if (duration <= 45) {
            is = duration*sets*0.5; 
        } else if (duration > 45 && duration <= 90) {
            is = duration*sets*0.8;
        } else {
            is = duration*sets*1.2;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // Low Intesity Repetition Calculator (Poomsae)
    public Integer PoomsaeLowRepetitionCalculator(Integer repetition, Integer sets) {
        double is = 0;
        if (repetition <= 30) {
            is = repetition*sets*1.5; 
        } else if (repetition > 30 && repetition <= 50) {
            is = repetition*sets*1.8;
        } else {
            is = repetition*sets*2.2;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // Moderate Intensity Duration Calculator (Poomsae)
    public Integer PoomsaeModDurationCalculator(Integer duration, Integer sets) {
        double is = 0;
        if (duration <= 30) {
            is = duration*sets*0.8; 
        } else if (duration > 30 && duration <= 60) {
            is = duration*sets*1.1;
        } else {
            is = duration*sets*1.5;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // Moderate Intesity Repetition Calculator (Poomsae)
    public Integer PoomsaeModRepetitionCalculator(Integer repetition, Integer sets) {
        double is = 0;
        if (repetition <= 25) {
            is = repetition*sets*1.8; 
        } else if (repetition > 25 && repetition <= 40) {
            is = repetition*sets*2.1;
        } else {
            is = repetition*sets*2.5;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // High Intensity Duration Calculator (Poomsae)
    public Integer PoomsaeHighDurationCalculator(Integer duration, Integer sets) {
        double is = 0;
        if (duration <= 20) {
            is = duration*sets*2.5; 
        } else if (duration > 20 && duration <= 40) {
            is = duration*sets*3;
        } else {
            is = duration*sets*4;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // High Intesity Repetition Calculator (Poomsae)
    public Integer PoomsaeHighRepetitionCalculator(Integer repetition, Integer sets) {
        double is = 0;
        if (repetition <= 15) {
            is = repetition*sets*4.5; 
        } else if (repetition > 15 && repetition <= 30) {
            is = repetition*sets*5;
        } else {
            is = repetition*sets*6;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // Extreme Intensity Duration Calculator (Poomsae)
    public Integer PoomsaeExtremeDurationCalculator(Integer duration, Integer sets) {
        double is = 0;
        if (duration <= 10) {
            is = duration*sets*20; 
        } else if (duration > 10 && duration <= 20) {
            is = duration*sets*25;
        } else {
            is = duration*sets*30;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }

    // Extreme Intesity Repetition Calculator (Poomsae)
    public Integer PoomsaeExtremeRepetitionCalculator(Integer repetition, Integer sets) {
        double is = 0;
        if (repetition <= 5) {
            is = repetition*sets*50; 
        } else if (repetition > 5 && repetition <= 10) {
            is = repetition*sets*55;
        } else {
            is = repetition*sets*60;
        }
        Integer intensityScore = (int)is;
        return intensityScore;
    }
}