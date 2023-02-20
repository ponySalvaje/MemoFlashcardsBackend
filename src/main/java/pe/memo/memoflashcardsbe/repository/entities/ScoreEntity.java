package pe.memo.memoflashcardsbe.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

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
        this.lastInterval = 0L;
        this.repetitions = 0L;
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = Instant.now();
        this.repetitionDate = Instant.now();
        this.repetitions = this.repetitions + 1L;
    }
}