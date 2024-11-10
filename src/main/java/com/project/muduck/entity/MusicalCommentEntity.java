package com.project.muduck.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "musical_comments")
@Table(name = "musical_comments")
public class MusicalCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer musicalCommentId;
    private Integer musicalId;
    private String musicalCommentWriter;
    private String musicalCommentContent;
    private String musicalCommentCreactAt;
}
