package com.workouttracker.service;

import com.workouttracker.model.Workout;
import com.workouttracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Workout management service using database.
 * Handles workout CRUD operations.
 *
 * @author Brandon Ortega, Aaron Starley
 * @version 2.0
 * @since Milestone 4
 */
@Service
public class WorkoutService implements WorkoutBusinessServiceInterface {

    @Autowired
    private WorkoutRepository workoutRepository;

    /**
     * Creates new workout in database.
     *
     * @param workout the workout to create
     * @return true if creation successful
     */
    @Override
    public boolean createWorkout(Workout workout) {
        workoutRepository.save(workout);
        System.out.println("Workout saved to database: " + workout.getExerciseName());
        return true;
    }

    /**
     * Gets all workouts from database.
     *
     * @return list of all workouts
     */
    @Override
    public List<Workout> getAllWorkouts() {
        List<Workout> workouts = new ArrayList<>();
        workoutRepository.findAll().forEach(workouts::add);
        return workouts;
    }

    /**
     * Gets workouts for specific user.
     *
     * @param userId the user ID
     * @return list of user's workouts
     */
    public List<Workout> getUserWorkouts(Long userId) {
        return workoutRepository.findByUserId(userId);
    }
}