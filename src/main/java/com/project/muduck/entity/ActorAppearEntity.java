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
@Entity(name = "actor_appears")
@Table(name = "actor_appears")
public class ActorAppearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appearId;
    private Integer actorId;
    private String appearName;
    private String appearImage;
    
}
