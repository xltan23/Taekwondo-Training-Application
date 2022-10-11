package sg.nus.iss.edu.TaekwondoTraining.services;

import java.io.StringReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.nus.iss.edu.TaekwondoTraining.models.TkdSummary;
import sg.nus.iss.edu.TaekwondoTraining.models.TkdWorkout;
import sg.nus.iss.edu.TaekwondoTraining.repositories.TkdSummaryRepository;

@Service
public class TkdSummaryService {
    
    @Autowired
    private TkdSummaryRepository tkdSumRepo;

    // Retrieve full workout archive from Redis
    public List<TkdSummary> retrieveArchive(String name) {
        Optional<String> opt = tkdSumRepo.get(name);
        String payload;
        System.out.printf(">>> Retrieving archive for %s\n", name.toLowerCase());

        if (opt.isEmpty()) {
            return Collections.emptyList();
        } else {
            payload = opt.get();
            System.out.printf(">>> All workout summary: %s\n", payload);
        }

        StringReader sr = new StringReader(payload);
        JsonReader jr = Json.createReader(sr);
        JsonArray archiveArray = jr.readArray();
        List<TkdSummary> archiveList = new LinkedList<>();
        for (int i = 0; i < archiveArray.size(); i++) {
            JsonObject archive = archiveArray.getJsonObject(i);
            archiveList.add(TkdSummary.createTWS(archive));
        }
        return archiveList;
    }

    // Saving workout summary in workout archive in Redis
    public void save(String name, TkdSummary newSummary) {
        // Retrieve workout archive
        Optional<String> opt = tkdSumRepo.get(name);
        List<TkdSummary> archiveList = new LinkedList<>();
        String payload;
        if (!opt.isEmpty()) {
            // If user's records exist, fill archiveList
            payload = opt.get();
            System.out.println(">>> Retrieving all workout archive...\n");
            StringReader sr = new StringReader(payload);
            JsonReader jr = Json.createReader(sr);
            JsonArray archiveArray = jr.readArray();
            for (int i = 0; i < archiveArray.size(); i++) {
                JsonObject archive = archiveArray.getJsonObject(i);
                archiveList.add(TkdSummary.createTWS(archive));
            }
        } else {
            // If user's record absent (i.e. new user), start with empty archive list
        }
        archiveList.add(newSummary);
        String newPayload = listToJson(archiveList);
        System.out.printf(">>> Saving new workout archive: %s\n", newPayload);
        tkdSumRepo.save(name, newPayload);
    }

    // List to JsonArray method that supports up to 10 entries
    private String listToJson(List<TkdSummary> list) {
        int size = list.size();
        String newPayload = "";
        switch(size) {
            case 1:
                JsonArray ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 2:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 3:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 4:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 5:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 6:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 7:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 8:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 9:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 10:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 11:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 12:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 13:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 14:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 15:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 16:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .add(list.get(15).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 17:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .add(list.get(15).toJson())
                    .add(list.get(16).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 18:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .add(list.get(15).toJson())
                    .add(list.get(16).toJson())
                    .add(list.get(17).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 19:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .add(list.get(15).toJson())
                    .add(list.get(16).toJson())
                    .add(list.get(17).toJson())
                    .add(list.get(18).toJson())
                    .build();
                newPayload = ja.toString();
                break;
            case 20:
                ja = Json.createArrayBuilder()
                    .add(list.get(0).toJson())
                    .add(list.get(1).toJson())
                    .add(list.get(2).toJson())
                    .add(list.get(3).toJson())
                    .add(list.get(4).toJson())
                    .add(list.get(5).toJson())
                    .add(list.get(6).toJson())
                    .add(list.get(7).toJson())
                    .add(list.get(8).toJson())
                    .add(list.get(9).toJson())
                    .add(list.get(10).toJson())
                    .add(list.get(11).toJson())
                    .add(list.get(12).toJson())
                    .add(list.get(13).toJson())
                    .add(list.get(14).toJson())
                    .add(list.get(15).toJson())
                    .add(list.get(16).toJson())
                    .add(list.get(17).toJson())
                    .add(list.get(18).toJson())
                    .add(list.get(19).toJson())
                    .build();
                newPayload = ja.toString();
                break;
        }
        return newPayload;
    }   

    // Calculator to sum up all intensity values in workout list
    public Integer totalIntensityCalculator(List<TkdWorkout> workoutList) {
        Integer totalIntensityScore = 0;
        for (int i = 0; i < workoutList.size(); i++) {
            Integer intensityScore = workoutList.get(i).getIntensityScore();
            totalIntensityScore += intensityScore;
        }
        return totalIntensityScore;
    }

    // Intensity to determine intensity level
    public String intensityLevelCalculator(Integer totalIntensityScore) {
        if (totalIntensityScore <= 3000) {
            return "low";
        } else if (totalIntensityScore > 3000 && totalIntensityScore <= 6000) {
            return "moderate";
        } else if (totalIntensityScore > 6000 && totalIntensityScore <= 10000) {
            return "high";
        } else {
            return "extreme";
        }
    }
}
