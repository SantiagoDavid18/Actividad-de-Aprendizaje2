package ec.edu.espe.openai.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String prompt;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String response;

    private Integer age;
    private String difficulty;

    // URL pública del audio
    @Column(name = "audio_url")
    private String audioUrl;

    // Relación con el perfil del niño
    @ManyToOne
    @JoinColumn(name = "child_profile_id")
    private ChildProfile childProfile;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public Activity() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }
    public String getAudioUrl() { return audioUrl; }
    public void setAudioUrl(String audioUrl) { this.audioUrl = audioUrl; }
    public ChildProfile getChildProfile() { return childProfile; }
    public void setChildProfile(ChildProfile childProfile) { this.childProfile = childProfile; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
