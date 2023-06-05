package br.com.mrmaia.superpeope.storage.adapters;

import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;

import java.util.List;
import java.util.stream.Collectors;

public class SuperPeopleAdapter {

    public static SuperPeople convertTo(SuperPeopleDTO superPeopleDTO) {
        return SuperPeople.builder().name(superPeopleDTO.getName()).planet(superPeopleDTO.getPlanet())
                .type(superPeopleDTO.getType()).level(superPeopleDTO.getLevel())
                .currentExperience(superPeopleDTO.getCurrentExperience())
                .nextLevelExperience(superPeopleDTO.getNextLevelExperience()).charisma(superPeopleDTO.getCharisma())
                .intelligence(superPeopleDTO.getIntelligence()).dexterity(superPeopleDTO.getDexterity())
                .strength(superPeopleDTO.getStrength()).wisdom(superPeopleDTO.getWisdom())
                .constitution(superPeopleDTO.getConstitution()).build();
    }

    public static List<SuperPeople> convertToList(List<SuperPeopleDTO> superPeopleDTO) {
        return superPeopleDTO.stream().map(item -> convertTo(item)).collect(Collectors.toList());
    }
}
