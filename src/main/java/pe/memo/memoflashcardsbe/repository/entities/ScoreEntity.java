package pe.memo.memoflashcardsbe.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scores")
public class ScoreEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "score", length = 10)
    private String score;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "repetitions", columnDefinition = "INT UNSIGNED")
    private Long repetitions;

    @Column(name = "last_interval", columnDefinition = "INT UNSIGNED")
    private Long lastInterval;

    @Column(name = "repetition_date")
    private Instant repetitionDate;

    @Column(name = "last_ease_factor", columnDefinition = "DOUBLE(22) UNSIGNED")
    private Double lastEaseFactor;

    @PrePersist
    void prePersist() {
        this.createdAt = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
        this.repetitionDate = Instant.now();
        this.repetitions = this.repetitions + 1L;
    }
}