package com.workouttracker.controller;

import com.workouttracker.model.Workout;
import com.workouttracker.service.WorkoutBusinessServiceInterface;
import com.workouttracker.service.WorkoutService;
import jakarta.servlet.http.HttpSession;
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
                                Model model,
                                HttpSession session) {
        if (result.hasErrors()) {
            return "pages/workout-creation";
        }

        // Get user ID from session
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        // Set the user ID on the workout
        workout.setUserId(userId);
        workoutService.createWorkout(workout);
        return "redirect:/workouts";
    }

    // List all workouts
    @GetMapping
    public String listWorkouts(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        // Cast service to access getUserWorkouts method
        WorkoutService workoutServiceImpl = (WorkoutService) workoutService;
        model.addAttribute("workouts", workoutServiceImpl.getUserWorkouts(userId));
        return "pages/workout-list";
    }
}
