package com.workouttracker.repository;

import com.workouttracker.model.Workout;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WorkoutRepository extends CrudRepository<Workout, Long> {
    List<Workout> findByUserId(Long userId);
}