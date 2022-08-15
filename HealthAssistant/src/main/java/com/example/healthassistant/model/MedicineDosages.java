package com.example.healthassistant.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MedicineDosages {
    private Long userId;
    private long dosagesId;
    private String medicinename;
    private String timesInADay;
    private String timesPerWeek;
    private String dosageQuantity;

    @Basic
    @Column(name = "UserId")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "DosagesId")
    public long getDosagesId() {
        return dosagesId;
    }

    public void setDosagesId(long dosagesId) {
        this.dosagesId = dosagesId;
    }

    @Column(name = "Medicine")
    public String getMedicinename() {
        return medicinename;
    }

    public void setMedicinename(String medicinename) {
        this.medicinename = medicinename;
    }

    //    @Basic
//    @Column(name = "Medicine")
//    public String getMedicine() {
//        return medicine;
//    }
//
//    public void setMedicine(String medicine) {
//        this.medicine = this.medicine;
//    }

    @Basic
    @Column(name = "TimesInADay")
    public String getTimesInADay() {
        return timesInADay;
    }

    public void setTimesInADay(String timesInADay) {
        this.timesInADay = timesInADay;
    }

    @Basic
    @Column(name = "TimesPerWeek")
    public String getTimesPerWeek() {
        return timesPerWeek;
    }

    public void setTimesPerWeek(String timesPerWeek) {
        this.timesPerWeek = timesPerWeek;
    }

    @Basic
    @Column(name = "DosageQuantity")
    public String getDosageQuantity() {
        return dosageQuantity;
    }

    public void setDosageQuantity(String dosageQuantity) {
        this.dosageQuantity = dosageQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDosages that = (MedicineDosages) o;
        return dosagesId == that.dosagesId &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(medicinename, that.medicinename) &&
                Objects.equals(timesInADay, that.timesInADay) &&
                Objects.equals(timesPerWeek, that.timesPerWeek) &&
                Objects.equals(dosageQuantity, that.dosageQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, dosagesId, medicinename, timesInADay, timesPerWeek, dosageQuantity);
    }
}
