package com.workouttracker.service;

import com.workouttracker.model.Workout;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkoutService implements WorkoutBusinessServiceInterface {

    private List<Workout> workouts = new ArrayList<>();
    private long nextId = 1;

    @Override
    public boolean createWorkout(Workout workout) {
        workout.setId(nextId++);
        workouts.add(workout);
        System.out.println("Workout created: " + workout.getExerciseName());
        return true;
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workouts;
    }
}
