package br.com.mrmaia.superpeope.storage.model;

import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;

import java.util.List;

public class SuperPeopleBuilder {

    public static SuperPeople superPeopleSuccessBuilder() {
        return SuperPeople.builder()
                .id(1L).name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();
    }

    public static SuperPeople superPeopleSuccessBuilder2() {
        return SuperPeople.builder()
                .id(2L).name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();
    }
/*
    public static SuperPeople superPeopleUpdateBuilder() {
        return SuperPeople.builder()
                .id(1L).name("Big Dude").planet("Zion").type("Hero").build();
    }
*/
    public static SuperPeople superPeopleInvalidNameExceptionErrorBuilder() {
        return SuperPeople.builder()
                .id(1L).name("").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();
    }

    public static SuperPeople superPeopleExcessiveTotalBattleAttributesExceptionErrorBuilder() {
        return SuperPeople.builder()
                .id(1L).name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(7L).intelligence(5L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();
    }

    public static SuperPeople superPeopleBattleAttributeWithValueZeroExceptionErrorBuilder() {
        return SuperPeople.builder()
                .id(1L).name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
                .nextLevelExperience(2L).charisma(5L).intelligence(0L).dexterity(5L)
                .strength(5L).wisdom(5L).constitution(5L).superPowers(List.of()).build();
    }
}
