package sg.nus.iss.edu.TaekwondoTraining.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class TkdSummary {
    
    // Defining workout summary
    private String name;
    private String time;
    private Integer totalCount;
    private Integer lowCount;
    private Integer modCount;
    private Integer highCount;
    private Integer extremeCount;
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
        public Integer getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
    public Integer getLowCount() {
        return lowCount;
    }
    public void setLowCount(Integer lowCount) {
        this.lowCount = lowCount;
    }
    public Integer getModCount() {
        return modCount;
    }
    public void setModCount(Integer modCount) {
        this.modCount = modCount;
    }
    public Integer getHighCount() {
        return highCount;
    }
    public void setHighCount(Integer highCount) {
        this.highCount = highCount;
    }
    public Integer getExtremeCount() {
        return extremeCount;
    }
    public void setExtremeCount(Integer extremeCount) {
        this.extremeCount = extremeCount;
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
        workoutSummary.setTotalCount(jo.getInt("totalCount"));
        workoutSummary.setLowCount(jo.getInt("lowCount"));
        workoutSummary.setModCount(jo.getInt("modCount"));
        workoutSummary.setHighCount(jo.getInt("highCount"));
        workoutSummary.setExtremeCount(jo.getInt("extremeCount"));
        workoutSummary.setTotalIntensityScore(jo.getInt("totalIntensityScore"));
        workoutSummary.setIntensityLevel(jo.getString("intensityLevel"));
        return workoutSummary;
    }

    // Creating JsonObject from workout summary
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("time", time)
                .add("totalCount", totalCount)
                .add("lowCount", lowCount)
                .add("modCount", modCount)
                .add("highCount", highCount)
                .add("extremeCount", extremeCount)
                .add("totalIntensityScore", totalIntensityScore)
                .add("intensityLevel", intensityLevel)
                .build();
    }
}
