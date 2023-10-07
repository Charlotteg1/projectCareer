package com.openai.models;

import com.openai.models.enums.Aspiration;
import com.openai.models.enums.JobTitle;
import com.openai.models.enums.LearningStyle;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="employees")
public class User {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    @Column
    private String name;
    @Column
    private String email;
    @Enumerated(EnumType.STRING)
    private JobTitle jobTitle;
    @Enumerated(EnumType.STRING)
    private LearningStyle learningStyle;
    @Enumerated(EnumType.STRING)
    private Aspiration aspiration;
    @Column
    private List<UserSkill> userSkills;

    public User(String name, String email, JobTitle jobTitle, LearningStyle learningStyle, Aspiration aspiration, List<UserSkill> userSkills) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.learningStyle = learningStyle;
        this.aspiration = aspiration;
        this.userSkills = userSkills;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public LearningStyle getLearningStyle() {
        return learningStyle;
    }

    public void setLearningStyle(LearningStyle learningStyle) {
        this.learningStyle = learningStyle;
    }

    public Aspiration getAspiration() {
        return aspiration;
    }

    public void setAspiration(Aspiration aspiration) {
        this.aspiration = aspiration;
    }

    public List<UserSkill> getUserSkills() {
        return userSkills;
    }

    public void setUserSkills(List<UserSkill> userSkills) {
        this.userSkills = userSkills;
    }
}
