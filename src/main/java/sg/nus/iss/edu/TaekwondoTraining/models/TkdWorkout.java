package sg.nus.iss.edu.TaekwondoTraining.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TkdWorkout {
    
    // Defining workout
    private String name;
    private Integer duration;
    private Integer repetition;
    private Integer distance;
    private Integer sets;
    private String intensity;
    private Integer intensityScore;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    public Integer getRepetition() {
        return repetition;
    }
    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }
    public Integer getDistance() {
        return distance;
    }
    public void setDistance(Integer distance) {
        this.distance = distance;
    }
    public Integer getSets() {
        return sets;
    }
    public void setSets(Integer sets) {
        this.sets = sets;
    }
    public String getIntensity() {
        return intensity;
    }
    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
    public Integer getIntensityScore() {
        return intensityScore;
    }
    public void setIntensityScore(Integer intensityScore) {
        this.intensityScore = intensityScore;
    }

    // Create workout from JsonObject
    public static TkdWorkout createTW(JsonObject jo) {
        TkdWorkout workout = new TkdWorkout();
        workout.setName(jo.getString("name"));
        workout.setDuration(jo.getInt("duration"));
        workout.setRepetition(jo.getInt("repetition"));
        workout.setDistance(jo.getInt("distance"));
        workout.setIntensity(jo.getString("intensity"));
        workout.setIntensityScore(jo.getInt("intensityScore"));
        return workout;
    }

    // Creating JsonObject for workout
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("duration", duration)
                .add("repetition", repetition)
                .add("distance", distance)
                .add("intensity", intensity)
                .add("intensityScore", intensityScore)
                .build();
    }
}
