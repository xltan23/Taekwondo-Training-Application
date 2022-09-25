package sg.nus.iss.edu.TaekwondoTraining.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TkdSummary {
    
    // Defining workout summary
    private String name;
    private String time;
    private Integer totalIntensityScore;
    private String intensityLevel;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public Integer getTotalIntensityScore() {
        return totalIntensityScore;
    }
    public void setTotalIntensityScore(Integer totalIntensityScore) {
        this.totalIntensityScore = totalIntensityScore;
    }
    public String getIntensityLevel() {
        return intensityLevel;
    }
    public void setIntensityLevel(String intensityLevel) {
        this.intensityLevel = intensityLevel;
    }

    // Creating workout summary from JsonObject
    public static TkdSummary createTWS(JsonObject jo) {
        TkdSummary workoutSummary = new TkdSummary();
        workoutSummary.setName(jo.getString("name"));
        workoutSummary.setTime(jo.getString("time"));
        workoutSummary.setTotalIntensityScore(jo.getInt("totalIntensityScore"));
        workoutSummary.setIntensityLevel(jo.getString("intensityLevel"));
        return workoutSummary;
    }

    // Creating JsonObject from workout summary
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("time", time)
                .add("totalIntensityScore", totalIntensityScore)
                .add("intensityLevel", intensityLevel)
                .build();
    }
}
