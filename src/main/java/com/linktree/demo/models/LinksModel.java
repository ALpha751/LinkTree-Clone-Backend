package com.linktree.demo.models;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.*; // Changed from javax.persistence.*
import java.util.UUID;

@Entity
public class LinksModel {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private UUID id;

    private String displayName;

    private String URL;

    private String userId;

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return displayName;
    }

    public void setName(String disp_name) {
        this.displayName = disp_name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String user_id) {
        this.userId = user_id;
    }
}
