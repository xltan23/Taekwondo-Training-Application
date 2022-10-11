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
import sg.nus.iss.edu.TaekwondoTraining.models.TkdWorkout;
import sg.nus.iss.edu.TaekwondoTraining.repositories.TkdWorkoutRepository;

@Service
public class TkdWorkoutService {
    
    @Autowired
    private TkdWorkoutRepository tkdWorkRepo;

    // Retrieve temporary workout list from session
    public List<TkdWorkout> retrieveWorkout(String name) {
        Optional<String> opt = tkdWorkRepo.get(name);
        String payload;
        System.out.printf(">>> Retrieving session data for %s\n", name.toLowerCase());

        if (opt.isEmpty()) {
            return Collections.emptyList();
        } else {
            payload = opt.get();
            System.out.printf(">>> Session workout: %s\n", payload);
        }

        StringReader sr = new StringReader(payload);
        JsonReader jr = Json.createReader(sr);
        JsonArray workoutSess = jr.readArray();
        List<TkdWorkout> workoutList = new LinkedList<>();
        for (int i = 0; i < workoutSess.size(); i++) {
            JsonObject workout = workoutSess.getJsonObject(i);
            workoutList.add(TkdWorkout.createTW(workout));
        }
        return workoutList;
    }

    // Saving workout in temporary list for session
    public void save(String name, TkdWorkout newWorkout) {
        // Retrieve temporary workout list
        Optional<String> opt = tkdWorkRepo.get(name);
        List<TkdWorkout> workoutList = new LinkedList<>();
        String payload; 
        if (!opt.isEmpty()) {
            // If user's session exist, fill the workout list
            payload = opt.get();
            System.out.println(">>> Retrieving session data...\n");
            StringReader sr = new StringReader(payload);
            JsonReader jr = Json.createReader(sr);
            JsonArray workoutSess = jr.readArray();
            for (int i = 0; i < workoutSess.size(); i++) {
                JsonObject workout = workoutSess.getJsonObject(i);
                workoutList.add(TkdWorkout.createTW(workout));
            }
        } else {
            // If user's session is new, start with empty workout list 
        }
        workoutList.add(newWorkout);
        String newPayload = listToJson(workoutList);
        System.out.printf(">>> Saving new session data: %s\n", newPayload);
        tkdWorkRepo.save(name, newPayload);
    }

        // List to JsonArray method that supports up to 10 entries
        private String listToJson(List<TkdWorkout> list) {
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
}
