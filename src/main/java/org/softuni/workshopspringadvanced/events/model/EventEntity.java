package org.softuni.workshopspringadvanced.events.model;

import org.softuni.workshopspringadvanced.events.EventType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "events")
public class EventEntity {
    private Long id;
    private String title;
    private String description;
    private EventType eventType;
    private Instant occurrence;

    public EventEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @NotNull
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type")
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    @NotNull
    @Column(name = "occurrence")
    public Instant getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Instant occurrence) {
        this.occurrence = occurrence;
    }
}
