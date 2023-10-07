package com.openai.models;

import com.openai.models.enums.JobTitle;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class JobPositions {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long positionId;

    private JobTitle jobTitle;

    private int band;
    private int minExperience;

    
}
