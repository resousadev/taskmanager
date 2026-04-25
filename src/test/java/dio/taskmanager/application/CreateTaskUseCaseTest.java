package dio.taskmanager.application;

import dio.taskmanager.application.input.CreatTaskInput;
import dio.taskmanager.domain.Task;
import dio.taskmanager.domain.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CreateTaskUseCaseTest {

    @Mock
    TaskRepository taskRepository;

    @InjectMocks
    CreateTaskUseCase useCase;

    @Test
    void should_create_task_successufully() {
        // Given
        var input = new CreatTaskInput("Test Task", java.util.Optional.of("This is a test task"));

        when(taskRepository.save(any(Task.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // When
        var output = useCase.execute(input);

        // Then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Test Task", output.title());
        assertEquals(java.util.Optional.of("This is a test task"), output.description());

        verify(taskRepository, times(1)).save(any(Task.class));
    }
}