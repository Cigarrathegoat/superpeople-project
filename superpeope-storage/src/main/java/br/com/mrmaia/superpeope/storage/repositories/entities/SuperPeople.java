package br.com.mrmaia.superpeope.storage.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TB_SUPERPEOPLE")
@SequenceGenerator(name = "SEQ_SUPERPEOPLE")

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SuperPeople {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUPERPEOPLE")
    @Column(name = "ID_SUPERPEOPLE")
    private Long id;

    @Column(name = "DS_NAME")
    private String name;

    @Column(name = "DS_PLANET")
    private String planet;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "DS_SUPERPOWERS")
    private List<SuperPower> superPowers;

    @Column(name = "DS_LEVEL")
    private Long level = 1L;

    @Column(name = "DS_CURRENTEXPERIENCE")
    private double currentExperience;

    @Column(name = "DS_NEXTLEVELEXPERIENCE")
    private Long nextLevelExperience = 100L;

    @Column(name = "DS_TYPE")
    private String type;

    @Column(name = "DS_STRENGTH")
    private Long strength;

    @Column(name = "DS_CONSTITUTION")
    private Long constitution;

    @Column(name = "DS_DEXTERITY")
    private Long dexterity;

    @Column(name = "DS_INTELLIGENCE")
    private Long intelligence;

    @Column(name = "DS_WISDOM")
    private Long wisdom;

    @Column(name = "DS_CHARISMA")
    private Long charisma;

    public Long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public SuperPeople incrementLevel(SuperPeople superPeople) {
        return superPeople.builder().level(this.level + 1).build();
    }
}
