package com.skillswap.controller;
import java.util.List;


import com.skillswap.dto.SkillResponseDTO;
import com.skillswap.model.Skill;
import com.skillswap.service.SkillService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @PostMapping("/user/{userId}")
    public SkillResponseDTO addSkill(@PathVariable Long userId,
                                     @RequestBody Skill skill) {

        Skill savedSkill = skillService.addSkillToUser(userId, skill);

        return new SkillResponseDTO(
                savedSkill.getId(),
                savedSkill.getName(),
                savedSkill.getType(),
                savedSkill.getUser().getId()
        );
    }
    @GetMapping("/user/{userId}")
    public List<SkillResponseDTO> getAllSkillsByUser(@PathVariable Long userId) {

        return skillService.getSkillsByUser(userId)
                .stream()
                .map(skill -> new SkillResponseDTO(
                        skill.getId(),
                        skill.getName(),
                        skill.getType(),
                        skill.getUser().getId()
                ))
                .toList();
    }

    
    @GetMapping("/user/{userId}/type/{type}")
    public List<SkillResponseDTO> getUserSkillsByType(
            @PathVariable Long userId,
            @PathVariable String type) {

        return skillService.getSkillsByUserAndType(userId, type)
                .stream()
                .map(skill -> new SkillResponseDTO(
                        skill.getId(),
                        skill.getName(),
                        skill.getType(),
                        skill.getUser().getId()
                ))
                .toList();
    }
    @DeleteMapping("/{skillId}")
    public String deleteSkill(@PathVariable Long skillId) {
        skillService.deleteSkill(skillId);
        return "Skill deleted successfully";
    }
    
    @PutMapping("/{skillId}")
    public SkillResponseDTO updateSkill(@PathVariable Long skillId,
                                        @RequestBody Skill skill) {

        Skill updated = skillService.updateSkill(skillId, skill);

        return new SkillResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getType(),
                updated.getUser().getId()
        );
    }
    
    @GetMapping("/match/{userId}")
    public List<SkillResponseDTO> findMatches(@PathVariable Long userId) {

        return skillService.findMatches(userId)
                .stream()
                .map(skill -> new SkillResponseDTO(
                        skill.getId(),
                        skill.getName(),
                        skill.getType(),
                        skill.getUser().getId()
                ))
                .toList();
    }




}
