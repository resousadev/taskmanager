package dio.taskmanager.application;

import dio.taskmanager.application.input.CreatTaskInput;
import dio.taskmanager.insfrastructure.repository.InMemoryTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CreateTaskUseCaseTest {

    CreateTaskUseCase useCase;

    @BeforeEach
    void setUp() {
        this.useCase = new CreateTaskUseCase(new InMemoryTaskRepository());
    }

    @Test
    void should_create_task_successufully() {
        // Given
        var input = new CreatTaskInput("Test Task", java.util.Optional.of("This is a test task"));

        // When
        var output = useCase.execute(input);

        // Then
        assertNotNull(output);
        assertNotNull(output.id());
        assertEquals("Test Task", output.title());
        assertEquals(java.util.Optional.of("This is a test task"), output.description());
    }
}