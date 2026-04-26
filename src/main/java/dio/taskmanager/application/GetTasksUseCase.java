package dio.taskmanager.application;

import dio.taskmanager.application.output.TaskOutput;
import dio.taskmanager.domain.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTasksUseCase {
    private final TaskRepository repository;

    public GetTasksUseCase(TaskRepository repository) {
        this.repository = repository;
    }

    public List<TaskOutput> execute() {
        var tasks = repository.findAll();
        return tasks.stream()
                .map(TaskOutput::from)
                .toList();
    }
}
