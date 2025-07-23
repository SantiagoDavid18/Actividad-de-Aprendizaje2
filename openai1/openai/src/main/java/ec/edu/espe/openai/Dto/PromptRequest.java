package ec.edu.espe.openai.Dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PromptRequest {
    @NotBlank(message = "El mensaje no puede estar vacío")
    private String message;
    private int age;
    private String difficulty;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
