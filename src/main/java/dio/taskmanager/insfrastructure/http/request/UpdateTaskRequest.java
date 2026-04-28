package dio.taskmanager.insfrastructure.http.request;

import dio.taskmanager.application.input.UpdateTaskInput;
import dio.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskRequest(
        Optional<String> status,
        Optional<String> title,
        Optional<String> description
) {
    public UpdateTaskInput toInput() {
        return new UpdateTaskInput(status.map(TaskStatus::valueOf), title, description);
    }
}
