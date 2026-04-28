package dio.taskmanager.application;

import dio.taskmanager.application.input.UpdateTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateTaskUseCase {
    private final TaskRepository repository;

    public UpdateTaskUseCase(TaskRepository repository) {
        this.repository = repository;
    }

     public TaskOutput execute(TaskId id, UpdateTaskInput input) {
         var task = repository.findById(id)
                 .orElseThrow(() -> new TaskNotFoundException(id));

         task.update(input.title(), input.description(), input.status());
         Task updated = repository.save(task);
         return TaskOutput.from(updated);
     }
}
