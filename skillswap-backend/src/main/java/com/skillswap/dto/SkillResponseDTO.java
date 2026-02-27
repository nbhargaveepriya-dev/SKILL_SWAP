package com.skillswap.dto;

public class SkillResponseDTO {

    private Long id;
    private String name;
    private String type;
    private Long userId;

    public SkillResponseDTO(Long id, String name, String type, Long userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getType() { return type; }

    public Long getUserId() { return userId; }
}
