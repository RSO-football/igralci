package rso.football.igralci.lib;

import java.time.Instant;

public class IgralciMetadata {

    private Integer igralecId;
    private String name;
    private String description;


    public Integer getIgralecId() {
        return igralecId;
    }

    public void setIgralecId(Integer igralecId) {
        this.igralecId = igralecId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
