package br.com.mrmaia.superpeope.storage.apis.dto.requests;

import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPower;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuperPeopleDTO {

    private String name;

    private String planet;

    private String type;

    private Long level;

    private Long currentExperience;

    private Long nextLevelExperience;

    private Long charisma;

    private Long intelligence;

    private Long dexterity;

    private Long strength;

    private Long wisdom;

    private Long constitution;
}
// .id(1L).name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
//                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
//                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();