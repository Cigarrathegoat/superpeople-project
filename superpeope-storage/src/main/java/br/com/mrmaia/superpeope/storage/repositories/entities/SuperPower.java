package br.com.mrmaia.superpeope.storage.repositories.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_SUPERPOWER")
@SequenceGenerator(name = "SEQ_SUPERPOWER")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperPower {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SUPERPOWER")
    @Column(name = "ID_SUPERPOWER")
    private Long id;

    @Column(name = "DT_NAME")
    private String name;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}


}
