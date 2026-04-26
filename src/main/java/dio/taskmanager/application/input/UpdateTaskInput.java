package dio.taskmanager.application.input;

import dio.taskmanager.domain.TaskStatus;

import java.util.Optional;

public record UpdateTaskInput(
        Optional<TaskStatus> status,
        Optional<String> title,
        Optional<String> description) {
}
