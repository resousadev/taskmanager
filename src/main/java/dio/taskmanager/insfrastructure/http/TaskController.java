package dio.taskmanager.insfrastructure.http;

import dio.taskmanager.application.CreateTaskUseCase;
import dio.taskmanager.application.input.CreatTaskInput;
import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.insfrastructure.http.request.CreateTaskRequest;
import dio.taskmanager.insfrastructure.http.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping
    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
        var input = request.toInput();
        TaskOutput output = createTaskUseCase.execute(input);
        return TaskResponse.from(output);
    }
}
