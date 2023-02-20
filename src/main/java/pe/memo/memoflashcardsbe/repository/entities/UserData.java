package pe.memo.memoflashcardsbe.repository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserData {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 191)
    private String name;

    @Column(name = "email", length = 191)
    private String email;

    @Column(name = "email_verified_at")
    private Instant emailVerifiedAt;

    @Column(name = "password", length = 191)
    private String password;

    @Lob
    @Column(name = "two_factor_secret")
    private String twoFactorSecret;

    @Lob
    @Column(name = "two_factor_recovery_codes")
    private String twoFactorRecoveryCodes;

    @Column(name = "remember_token", length = 100)
    private String rememberToken;

    @Column(name = "profile_photo_path", length = 2048)
    private String profilePhotoPath;

    @Column(name = "type", length = 12)
    private String type;

    @Column(name = "student_type_id", columnDefinition = "TINYINT UNSIGNED")
    private Short studentTypeId;

    @Column(name = "university_id", columnDefinition = "TINYINT UNSIGNED")
    private Short universityId;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(Instant.now());
        this.setUpdatedAt(Instant.now());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedAt(Instant.now());
    }
}