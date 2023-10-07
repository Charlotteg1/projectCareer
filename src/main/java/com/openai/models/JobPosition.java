package com.openai.models;

import com.openai.models.enums.JobTitle;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class JobPosition {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;

    @Column
    private int band;
    @Column
    private int minExperience;

    @ManyToMany
    @JoinTable(name = "job_position_skills", joinColumns = @JoinColumn(name = "position_id"), inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skillsNeeded;

    public JobPosition(JobTitle jobTitle, int band, int minExperience, List<Skill> skillsNeeded) {
        this.jobTitle = jobTitle;
        this.band = band;
        this.minExperience = minExperience;
        this.skillsNeeded = skillsNeeded;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }

    public List<Skill> getSkillsNeeded() {
        return skillsNeeded;
    }

    public void setSkillsNeeded(List<Skill> skillsNeeded) {
        this.skillsNeeded = skillsNeeded;
    }
}
