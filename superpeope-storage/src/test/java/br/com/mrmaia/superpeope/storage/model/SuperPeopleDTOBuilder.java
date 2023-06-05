package br.com.mrmaia.superpeope.storage.model;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;

import java.util.List;

public class SuperPeopleDTOBuilder {

    public static SuperPeopleDTO superPeopleDTOSuccessBuilder() {
        return SuperPeopleDTO.builder()
                .name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).build();
    }

    public static SuperPeopleDTO superPeopleDTOInvalidNameExceptionErrorBuilder() {
        return SuperPeopleDTO.builder()
                .name("").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).build();
    }

    public static SuperPeopleDTO superPeopleDTOExcessiveTotalBattleAttributesExceptionErrorBuilder() {
        return SuperPeopleDTO.builder()
                .name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(7L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).build();
    }

    public static SuperPeopleDTO superPeopleDTOBattleAttributeWithValueZeroExceptionErrorBuilder() {
        return SuperPeopleDTO.builder()
                .name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(0L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).build();
    }
}
