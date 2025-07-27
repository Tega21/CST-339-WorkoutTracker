package com.workouttracker.controller;

import com.workouttracker.model.Workout;
import com.workouttracker.service.WorkoutBusinessServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutBusinessServiceInterface workoutService;

    // Show create workout form
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("workout", new Workout());
        return "pages/workout-creation";
    }

    // Handle form submission
    @PostMapping("/create")
    public String createWorkout(@Valid @ModelAttribute("workout") Workout workout,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "pages/workout-creation";
        }

        workoutService.createWorkout(workout);
        return "redirect:/workouts";
    }

    // List all workouts
    @GetMapping
    public String listWorkouts(Model model) {
        model.addAttribute("workouts", workoutService.getAllWorkouts());
        return "pages/workout-list";
    }
}
