package dio.taskmanager.insfrastructure.http.request;

import dio.taskmanager.application.input.CreatTaskInput;

import java.util.Optional;

public record CreateTaskRequest(String title, Optional<String> description) {
    public CreatTaskInput toInput() {
        return new CreatTaskInput(title, description);
    }
}
