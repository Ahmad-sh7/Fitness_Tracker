package com.example.myfitnesstracker.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mooddata")
public class MoodData {
    @PrimaryKey(autoGenerate = true)
    int satisfiedMeter;
    int calmMeter;
    int hppinessMeter;
    int excitedMeter;
    int energyMeter;
    int sleepyMeter;
    int negative_events;
    int positive_events;
    boolean alone;
    String place;
    int satisfiedrate;
    int failurerate;
    int impulsively;
    int aggressive;
    String notes;





    public MoodData(int satisfiedMeter, int calmMeter, int hppinessMeter, int excitedMeter, int energyMeter, int sleepyMeter, int negative_events, int positive_events ,
                    boolean alone, String place,int satisfiedrate, int failurerate,int impulsively,int aggressive, String notes ) {
        this.satisfiedMeter = satisfiedMeter;
        this.calmMeter = calmMeter;
        this.hppinessMeter = hppinessMeter;
        this.excitedMeter = excitedMeter;
        this.energyMeter = energyMeter;
        this.sleepyMeter = sleepyMeter;
        this.negative_events = negative_events;
        this.positive_events = positive_events;
        this.alone = alone;
        this.place = place;
        this.satisfiedrate = satisfiedrate;
        this.failurerate = failurerate;
        this.impulsively = impulsively;
        this.aggressive = aggressive;
        this.notes = notes;
    }

    public int getSatisfiedMeter() {
        return satisfiedMeter;
    }

    public void setSatisfiedMeter(int satisfiedMeter) {
        this.satisfiedMeter = satisfiedMeter;
    }

    public int getCalmMeter() {
        return calmMeter;
    }

    public void setCalmMeter(int calmMeter) {
        this.calmMeter = calmMeter;
    }

    public int getHppinessMeter() {
        return hppinessMeter;
    }

    public void setHppinessMeter(int hppinessMeter) {
        this.hppinessMeter = hppinessMeter;
    }

    public int getExcitedMeter() {
        return excitedMeter;
    }

    public void setExcitedMeter(int excitedMeter) {
        this.excitedMeter = excitedMeter;
    }

    public int getEnergyMeter() {
        return energyMeter;
    }

    public void setEnergyMeter(int energyMeter) {
        this.energyMeter = energyMeter;
    }

    public int getSleepyMeter() {
        return sleepyMeter;
    }

    public void setSleepyMeter(int sleepyMeter) {
        this.sleepyMeter = sleepyMeter;
    }

    public void setNegative_events(int negative_events) { this.negative_events = negative_events; }

    public void setPositive_events(int positive_events) { this.positive_events = positive_events; }

    public void setAlone(boolean alone) { this.alone = alone; }

    public void setPlace(String place) { this.place = place; }

    public void setSatisfiedrate(int satisfiedrate) { this.satisfiedrate = satisfiedrate; }

    public void setFailurerate(int failurerate) { this.failurerate = failurerate; }

    public void setImpulsively(int impulsively) { this.impulsively = impulsively; }

    public void setAggressive(int aggressive) { this.aggressive = aggressive; }

    public void setNotes(String notes) { this.notes = notes; }

    public int getNegative_events() { return negative_events; }

    public int getPositive_events() { return positive_events; }

    public boolean isAlone() { return alone; }

    public String getPlace() { return place; }

    public int getSatisfiedrate() { return satisfiedrate; }

    public int getFailurerate() { return failurerate; }

    public int getImpulsively() { return impulsively; }

    public int getAggressive() { return aggressive; }

    public String getNotes() { return notes; }
}
