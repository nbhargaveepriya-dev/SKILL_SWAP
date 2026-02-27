package com.skillswap.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.skillswap.model.Skill;
import com.skillswap.model.User;
import com.skillswap.repository.SkillRepository;
import com.skillswap.repository.UserRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public SkillService(SkillRepository skillRepository,
                        UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }

    public Skill addSkillToUser(Long userId, Skill skill) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        skill.setUser(user);

        return skillRepository.save(skill);
    }
    
    public List<Skill> getSkillsByUser(Long userId) {
        return skillRepository.findByUserId(userId);
    }

    public List<Skill> getSkillsByUserAndType(Long userId, String type) {
        return skillRepository.findByUserIdAndType(userId, type);
    }
    public void deleteSkill(Long skillId) {
        if (!skillRepository.existsById(skillId)) {
            throw new RuntimeException("Skill not found");
        }
        skillRepository.deleteById(skillId);
    }
    public Skill updateSkill(Long skillId, Skill updatedSkill) {

        Skill existingSkill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        existingSkill.setName(updatedSkill.getName());
        existingSkill.setType(updatedSkill.getType());

        return skillRepository.save(existingSkill);
    }
    
    public List<Skill> findMatches(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Skill> userOfferedSkills = skillRepository
                .findByUserIdAndType(userId, "OFFERED");

        List<Skill> matches = new java.util.ArrayList<>();

        for (Skill offered : userOfferedSkills) {

            List<Skill> requestedByOthers =
                    skillRepository.findByNameAndType(
                            offered.getName(),
                            "REQUESTED"
                    );

            for (Skill skill : requestedByOthers) {
                if (!skill.getUser().getId().equals(userId)) {
                    matches.add(skill);
                }
            }
        }

        return matches;
    }




}
