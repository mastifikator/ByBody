package com.ByBody.TrainingPlanner.repo;

import com.ByBody.TrainingPlanner.models.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
