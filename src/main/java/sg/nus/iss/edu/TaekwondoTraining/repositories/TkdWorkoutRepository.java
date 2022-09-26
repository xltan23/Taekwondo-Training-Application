package sg.nus.iss.edu.TaekwondoTraining.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TkdWorkoutRepository {
    
    // Set Timeout duration for workout
    @Value("${workout.cache.duration}")
    private Long cacheTime;

    @Autowired
    @Qualifier("redislab")
    private RedisTemplate<String,String> redisTemplate;

    // Set Temporary Redis Key: {user}-workout
    // Set Temporary Redis Value: Workout payload
    public void save(String name, String payload) {
        ValueOperations<String,String> valueOp = redisTemplate.opsForValue();
        String userKey = "%s-workout".formatted(name.toLowerCase());
        valueOp.set(userKey, payload, cacheTime);
    }

    // Retrieve Box of Payload (Empty or Filled)
    public Optional<String> get(String name) {
        ValueOperations<String,String> valueOp = redisTemplate.opsForValue();
        String userKey = "%s-workout".formatted(name.toLowerCase());
        String userPayload = valueOp.get(userKey);
        if (null == userPayload) {
            return Optional.empty();
        }
        return Optional.of(userPayload);
    }
}
