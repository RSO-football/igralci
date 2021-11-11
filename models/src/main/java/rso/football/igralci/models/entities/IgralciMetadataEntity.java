package rso.football.igralci.models.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "igralci_metadata")
@NamedQueries(value =
        {
                @NamedQuery(name = "IgralciMetadataEntity.getAll",
                        query = "SELECT im FROM IgralciMetadataEntity im")
        })
public class IgralciMetadataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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