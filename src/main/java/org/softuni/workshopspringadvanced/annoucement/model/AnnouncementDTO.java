package org.softuni.workshopspringadvanced.annoucement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnnouncementDTO {
    private Long id;
    private Instant createdOn;

    private Instant updatedOn;

    @NotNull
    private String title;


    @Size(min=10, message = "The descirption should be more than 10 characters.")
    private String description;

    public AnnouncementDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
