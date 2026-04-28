package dio.taskmanager.insfrastructure.http.request;

import dio.taskmanager.application.input.CreatTaskInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateTaskRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String title,
        Optional<@Size(max = 500) String> description)
{
    public CreatTaskInput toInput() {
        return new CreatTaskInput(title, description);
    }
}
