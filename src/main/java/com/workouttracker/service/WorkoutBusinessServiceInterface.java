package com.workouttracker.service;

import com.workouttracker.model.Workout;

import java.util.List;

public interface WorkoutBusinessServiceInterface {

    boolean createWorkout(Workout workout);
    List<Workout> getAllWorkouts();

}
