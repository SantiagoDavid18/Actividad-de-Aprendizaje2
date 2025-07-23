package ec.edu.espe.openai.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "child_profiles")
public class ChildProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int age;

    private String diagnosis; // opcional

    private double progress = 0.0; // porcentaje de avance

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User parentOrTherapist; // relación con usuario

    private LocalDateTime createdAt = LocalDateTime.now();
}

