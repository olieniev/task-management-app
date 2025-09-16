package com.olieniev.taskmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "projects")
@Getter
@Setter
@EqualsAndHashCode
@SQLDelete(sql = "UPDATE projects SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;
    @Column(nullable = false)
    private boolean isDeleted = false;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.INITIATED;

    public enum Status {
        INITIATED,
        IN_PROGRESS,
        COMPLETED
    }
}
