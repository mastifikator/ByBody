package com.ByBody.TrainingPlanner.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title, anons;
    @Lob
    private String howToDoIt, howNotToDoIt;
    @Lob
    private String advantages, contraindications, complication;
    private String mainPhotoPath;
    private int views;

    public Exercise(String title, String anons, String howToDoIt, String howNotToDoIt, String advantages, String contraindications, String complication, String mainPhotoPath) {
        this.title = title;
        this.anons = anons;
        this.howToDoIt = howToDoIt;
        this.howNotToDoIt = howNotToDoIt;
        this.advantages = advantages;
        this.contraindications = contraindications;
        this.complication = complication;
        this.mainPhotoPath = mainPhotoPath;
    }
}
