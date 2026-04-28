package dio.taskmanager.insfrastructure.http;

import dio.taskmanager.application.*;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskId;
import dio.taskmanager.insfrastructure.http.request.CreateTaskRequest;
import dio.taskmanager.insfrastructure.http.request.UpdateTaskRequest;
import dio.taskmanager.insfrastructure.http.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetTasksUseCase getTasksUseCase;
    private final GetTaskByIdUseCase getTaskByIdUseCase;
    private final DeleteTaskUseCase deleteTaskUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    @PostMapping
    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
        var input = request.toInput();
        TaskOutput output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }

    @GetMapping
    List<TaskResponse> getAllTasks() {
        return getTasksUseCase.execute()
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    TaskResponse getTaskById(@PathVariable UUID id) {
        var output = getTaskByIdUseCase.execute(new TaskId(id));
        return TaskResponse.from(output);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = NO_CONTENT)
    void deleteTask(@PathVariable UUID id) {
        deleteTaskUseCase.execute(new TaskId(id));
    }

    @PatchMapping("/{id}")
    TaskResponse updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequest request) {
        var input = request.toInput();
        var output = updateTaskUseCase.execute(new TaskId(id), input);
        return TaskResponse.from(output);
    }
}
