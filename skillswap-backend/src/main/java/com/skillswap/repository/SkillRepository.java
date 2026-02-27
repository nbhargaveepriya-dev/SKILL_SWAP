package com.skillswap.repository;

import com.skillswap.model.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    List<Skill> findByUserId(Long userId);

    List<Skill> findByUserIdAndType(Long userId, String type);
    
    List<Skill> findByNameAndType(String name, String type);

}
