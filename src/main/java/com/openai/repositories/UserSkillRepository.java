package com.openai.repositories;

import com.openai.models.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSkillRepository extends JpaRepository<UserSkill,Long> {
}
