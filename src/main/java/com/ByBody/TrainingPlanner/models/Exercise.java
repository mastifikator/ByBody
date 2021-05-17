package com.ByBody.TrainingPlanner.models;

import javax.persistence.*;

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
    private String mainPhotoPath, mainVideoPath;
    private int views;

    public Exercise(String title, String anons, String howToDoIt, String howNotToDoIt, String advantages, String contraindications, String complication, String mainPhotoPath, String mainVideoPath) {
        this.title = title;
        this.anons = anons;
        this.howToDoIt = howToDoIt;
        this.howNotToDoIt = howNotToDoIt;
        this.advantages = advantages;
        this.contraindications = contraindications;
        this.complication = complication;
        this.mainPhotoPath = mainPhotoPath;
        this.mainVideoPath = mainVideoPath;
    }

    public Exercise() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnons() {
        return anons;
    }

    public void setAnons(String anons) {
        this.anons = anons;
    }

    public String getHowToDoIt() {
        return howToDoIt;
    }

    public void setHowToDoIt(String howToDoIt) {
        this.howToDoIt = howToDoIt;
    }

    public String getHowNotToDoIt() {
        return howNotToDoIt;
    }

    public void setHowNotToDoIt(String howNotToDoIt) {
        this.howNotToDoIt = howNotToDoIt;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
    }

    public String getMainPhotoPath() {
        return mainPhotoPath;
    }

    public void setMainPhotoPath(String mainPhotoPath) {
        this.mainPhotoPath = mainPhotoPath;
    }

    public String getMainVideoPath() {
        return mainVideoPath;
    }

    public void setMainVideoPath(String mainVideoPath) {
        this.mainVideoPath = mainVideoPath;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
