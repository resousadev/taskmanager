package dio.taskmanager.application;

import dio.taskmanager.application.input.CreatTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTaskUseCase {

    private final TaskRepository taskRepository;

    public TaskOutput execute(CreatTaskInput input) {
        Task task = new Task(input.title(), input.description());
        var savedTask = taskRepository.save(task);

        return TaskOutput.from(savedTask);
    }
}
