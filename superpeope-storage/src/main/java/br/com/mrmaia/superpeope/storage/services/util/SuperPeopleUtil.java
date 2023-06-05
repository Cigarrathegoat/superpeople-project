package br.com.mrmaia.superpeope.storage.services.util;

import br.com.mrmaia.superpeope.storage.exceptions.BattleAttributeWithValueZeroException;
import br.com.mrmaia.superpeope.storage.exceptions.InvalidNameException;
import br.com.mrmaia.superpeope.storage.exceptions.SuperPeopleNotFoundException;
import br.com.mrmaia.superpeope.storage.exceptions.ExcessiveTotalBattleAttributesException;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;

import java.util.Arrays;
import java.util.List;

public class SuperPeopleUtil {

    public static Long attributeSum(Long strength, Long constitution,
                                    Long dexterity, Long intelligence,
                                    Long wisdom, Long charisma) {

        return strength + constitution + dexterity + intelligence + wisdom + charisma;
    }

    public static void superPeopleNameNullVerifier(String name) throws InvalidNameException {
        if (!StringUtil.validateStringIsNotNullOrBlank(name)) {
            throw new InvalidNameException("S01", "invalid name");
        }
    }

    public static void superPeopleFoundVerifier(List<SuperPeople> superPeopleList, String heroName)
            throws SuperPeopleNotFoundException {
        if (!StringUtil.validateStringIsNotNullOrBlank(heroName)) {
            throw new SuperPeopleNotFoundException("S04", "not found");
            //change this so that it checks for heroFind, and checks whether it is present in the List<>
        }
        boolean found = superPeopleList.stream().anyMatch(superPeople -> superPeople.getName().equals(heroName));
        if (!found) {
            throw new SuperPeopleNotFoundException("S04", "not found");
        }
    }

    public static void superPeopleTotalAttributeValueVerifier(Long totalAttributes, Long reference)
            throws ExcessiveTotalBattleAttributesException {
        if (totalAttributes > reference) {
            throw new ExcessiveTotalBattleAttributesException(
                    "S02", "Total battle attributes must not exceed 30.");
        }
    }

    public static void superPeopleNoZeroValuesVerifier(Long strength, Long constitution,
                                                       Long dexterity, Long intelligence,
                                                       Long wisdom, Long charisma)
            throws BattleAttributeWithValueZeroException {
        long[] superAttributes = {strength, constitution,
                dexterity, intelligence,
                wisdom, charisma};
        if (!Arrays.stream(superAttributes).noneMatch(value -> value == 0)) {
            throw new BattleAttributeWithValueZeroException(
                    "S03", "All battle attributes must have a value of at least 1.");
        }
    }

    public static Double battleExperienceCalculator(SuperPeople superPeople, boolean winner) {
        return winner ? winnerExperienceCalculator(superPeople) : loserExperienceCalculator(superPeople);
    }

    private static Double winnerExperienceCalculator(SuperPeople superPeople) {
        return superPeople.getCurrentExperience() + ((superPeople.getLevel() * 10) / 1.5);
    }

    private static Double loserExperienceCalculator(SuperPeople superPeople) {
        return superPeople.getCurrentExperience() + ((superPeople.getLevel() * 10) / 2.5);
    }


}
