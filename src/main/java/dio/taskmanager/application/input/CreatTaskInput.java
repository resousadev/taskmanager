package dio.taskmanager.application.input;

import java.util.Optional;

public record CreatTaskInput(String title, Optional<String> description) {
}
