package br.com.mrmaia.superpeope.storage.adapters;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;

import java.util.List;
import java.util.stream.Collectors;

public class SuperPeopleDTOAdapter {
    //.name("Big Dude").planet("Zion").type("Hero").level(1L).currentExperience(1L)
    //                .nextLevelExperience(2L).charisma(5L).intelligence(5L).dexterity(5L)
    //                .strength(5L).wisdom(5L).constitution(5L).build();
    public static SuperPeopleDTO convertTo(SuperPeople superPeople) {

        return SuperPeopleDTO.builder().name(superPeople.getName()).planet(superPeople.getPlanet())
                .type(superPeople.getType()).level(superPeople.getLevel())
                .currentExperience((long) superPeople.getCurrentExperience())
                .nextLevelExperience(superPeople.getNextLevelExperience()).charisma(superPeople.getCharisma())
                .intelligence(superPeople.getIntelligence()).dexterity(superPeople.getDexterity())
                .strength(superPeople.getStrength()).wisdom(superPeople.getWisdom())
                .constitution(superPeople.getConstitution()).build();
    }

    public static List<SuperPeopleDTO> convertToList(List<SuperPeople> superPeopleList) {
        return superPeopleList.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }

}
