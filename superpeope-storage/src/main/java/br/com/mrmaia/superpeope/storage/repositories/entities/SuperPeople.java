package br.com.mrmaia.superpeope.storage.repositories.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long level;

    @Column(name = "DS_CURRENTEXPERIENCE")
    private Long currentExperience;

    @Column(name = "DS_NEXTLEVELEXPERIENCE")
    private Long nextLevelExperience;

    @Column(name = "DS_TYPE")
    private String type;

    public Long getId() { return id; }

    public void setId(long id) { this.id = id; }
}
