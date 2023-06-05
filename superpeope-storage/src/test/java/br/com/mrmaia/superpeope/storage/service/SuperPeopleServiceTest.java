package br.com.mrmaia.superpeope.storage.service;

import br.com.mrmaia.superpeope.storage.exceptions.*;
import br.com.mrmaia.superpeope.storage.model.SuperPeopleBuilder;
import br.com.mrmaia.superpeope.storage.services.ISuperPowerService;
import br.com.mrmaia.superpeope.storage.services.impl.SuperPeopleService;
import br.com.mrmaia.superpeope.storage.repositories.ISuperPeopleRepository;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPower;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SuperPeopleServiceTest {

    @Mock
    ISuperPeopleRepository superPeopleRepository;

    @Mock
    ISuperPowerService superPowerService;

    @Mock
    Logger log;

    @InjectMocks
    SuperPeopleService superPeopleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testSaveSuccess()
            throws InvalidNameException, ExcessiveTotalBattleAttributesException,
            BattleAttributeWithValueZeroException {
        when(superPeopleRepository.save(any())).thenReturn(SuperPeople.builder().id(1L).build());
        SuperPeople result = superPeopleService.save(
                SuperPeople.builder()
                        .name("Big Man").level(1L).currentExperience(1L)
                        .nextLevelExperience(1L).planet("Big Planet")
                        .superPowers(List.of(SuperPower.builder().id(1L).build()))
                        .type("hero").strength(5L).constitution(5L).dexterity(5L)
                        .intelligence(5L).wisdom(5L).charisma(5L)
                        .build());
        Assertions.assertNotNull(result);
    }

    @Test
    void testSaveEmptyNameExceptionError()
            throws InvalidNameException, ExcessiveTotalBattleAttributesException,
            BattleAttributeWithValueZeroException {
        InvalidNameException thrown = Assertions.assertThrows(InvalidNameException.class, () -> {
            superPeopleService.save(
                    SuperPeople.builder()
                            .name("").level(1L).currentExperience(1L)
                            .nextLevelExperience(1L).planet("Big Planet")
                            .superPowers(List.of(SuperPower.builder().id(1L).build()))
                            .type("hero").strength(5L).constitution(5L).dexterity(5L)
                            .intelligence(5L).wisdom(5L).charisma(5L)
                            .build());
        });

        Assertions.assertEquals("S01", thrown.getCode());
        Assertions.assertEquals("invalid name", thrown.getMessage());
    }


    @Test
    void testSaveNullNameError() throws InvalidNameException, ExcessiveTotalBattleAttributesException,
            BattleAttributeWithValueZeroException {
        InvalidNameException thrown = Assertions.assertThrows(InvalidNameException.class, () -> {
            superPeopleService.save(SuperPeople.builder()
                    .name(null).level(1L).currentExperience(1L)
                    .nextLevelExperience(1L).planet("Big Planet")
                    .superPowers(List.of(SuperPower.builder().id(1L).build()))
                    .type("hero").strength(5L).constitution(5L).dexterity(5L)
                    .intelligence(5L).wisdom(5L).charisma(5L)
                    .build());
        });
        Assertions.assertEquals("S01", thrown.getCode());
        Assertions.assertEquals("invalid name", thrown.getMessage());
    }


    @Test
    void testSaveExcessiveTotalBattleAttributesExceptionError()
            throws InvalidNameException, ExcessiveTotalBattleAttributesException,
            BattleAttributeWithValueZeroException {
        ExcessiveTotalBattleAttributesException thrown = Assertions.assertThrows(
                ExcessiveTotalBattleAttributesException.class, () -> {
                    superPeopleService.save(SuperPeople.builder()
                            .name("Big Man").level(1L).currentExperience(1L)
                            .nextLevelExperience(1L).planet("Big Planet")
                            .superPowers(List.of(SuperPower.builder().id(1L).build()))
                            .type("hero").strength(5L).constitution(5L).dexterity(5L)
                            .intelligence(5L).wisdom(6L).charisma(5L)
                            .build());
                });

        Assertions.assertEquals("S02", thrown.getCode());
        Assertions.assertEquals("Total battle attributes must not exceed 30.", thrown.getMessage());
    }

    @Test
    void testBattleAttributeWithValueZeroExceptionError()
            throws InvalidNameException, ExcessiveTotalBattleAttributesException,
            BattleAttributeWithValueZeroException {
        BattleAttributeWithValueZeroException thrown = Assertions.assertThrows(
                BattleAttributeWithValueZeroException.class, () -> {
                    superPeopleService.save(SuperPeople.builder()
                            .name("Big Man").level(1L).currentExperience(1L)
                            .nextLevelExperience(1L).planet("Big Planet")
                            .superPowers(List.of(SuperPower.builder().id(1L).build()))
                            .type("hero").strength(5L).constitution(5L).dexterity(5L)
                            .intelligence(5L).wisdom(0L).charisma(5L)
                            .build());
                });

        Assertions.assertEquals("S03", thrown.getCode());
        Assertions.assertEquals("All battle attributes must have a value of at least 1.",
                thrown.getMessage());
    }

    @Test
    void findSuperPeopleByNameSuccess() throws SuperPeopleNotFoundException {
        when(superPeopleRepository.findSuperPeopleByName(any()))
                .thenReturn(List.of(
                                SuperPeople.builder()
                                        .name("Big Man").level(1L).currentExperience(1L)
                                        .nextLevelExperience(1L).planet("Big Planet")
                                        .superPowers(List.of(SuperPower.builder().id(1L).build()))
                                        .type("hero").strength(5L).constitution(5L).dexterity(5L)
                                        .intelligence(5L).wisdom(5L).charisma(5L)
                                        .build()
                        )
                );
        List<SuperPeople> superPeopleFound = superPeopleService.findSuperPeopleByName("Big Man");
        Assertions.assertNotNull(superPeopleFound);
    }

    @Test
    void findSuperPeopleByNameSuperPeopleNotFoundExceptionError()
            throws SuperPeopleNotFoundException {
        when(superPeopleRepository.findSuperPeopleByName(any())).thenReturn(List.of());
        SuperPeopleNotFoundException thrown = Assertions.assertThrows(
                SuperPeopleNotFoundException.class, () -> {
                    superPeopleService.findSuperPeopleByName("Big Man");
                }
        );
        Assertions.assertEquals("S04", thrown.getCode());
        Assertions.assertEquals("not found", thrown.getMessage());
    }

    @Test
    void testListAllSuccess() {
        when(superPeopleRepository.findAll()).thenReturn(List.of());
        List<SuperPeople> superPeopleList = superPeopleService.listAll();
        Assertions.assertNotNull(superPeopleList);
    }

    @Test
    void testDeleteSuccess() throws SuperPeopleNotFoundException {
        when(superPeopleRepository.findById(any())).thenReturn(Optional.of(
                SuperPeople.builder()
                        .name("Big Man").level(1L).currentExperience(1L)
                        .nextLevelExperience(1L).planet("Big Planet")
                        .superPowers(List.of(SuperPower.builder().id(1L).build()))
                        .type("hero").strength(5L).constitution(5L).dexterity(5L)
                        .intelligence(5L).wisdom(5L).charisma(5L)
                        .build()
        ));
        superPeopleService.delete(SuperPeople.builder()
                .name("Big Man").level(1L).currentExperience(1L)
                .nextLevelExperience(1L).planet("Big Planet")
                .superPowers(List.of(SuperPower.builder().id(1L).build()))
                .type("hero").strength(5L).constitution(5L).dexterity(5L)
                .intelligence(5L).wisdom(5L).charisma(5L)
                .build());
    }

    @Test
    void testDeleteSuperPeopleNotFoundExceptionError() throws SuperPeopleNotFoundException {
        when(superPeopleRepository.findById(any())).thenReturn(Optional.empty());
        SuperPeopleNotFoundException thrown = Assertions.assertThrows(
                SuperPeopleNotFoundException.class, () -> {
                    superPeopleService.delete(SuperPeople.builder()
                            .name("Big Man").level(1L).currentExperience(1L)
                            .nextLevelExperience(1L).planet("Big Planet")
                            .superPowers(List.of(SuperPower.builder().id(1L).build()))
                            .type("hero").strength(5L).constitution(5L).dexterity(5L)
                            .intelligence(5L).wisdom(5L).charisma(5L)
                            .build());
                });
        Assertions.assertEquals("S04", thrown.getCode());
        Assertions.assertEquals("not found", thrown.getMessage());
    }


    @Test
    void testUpdateSuccess() throws SuperPeopleNotFoundException, InvalidNameException,
            BattleAttributeWithValueZeroException, ExcessiveTotalBattleAttributesException {
        when(superPeopleRepository.findById(any())).thenReturn(Optional.of(SuperPeople.builder()
                        .name("Big Man").level(1L).currentExperience(1L)
                        .nextLevelExperience(1L).planet("Big Planet")
                        .superPowers(List.of(SuperPower.builder().id(1L).build()))
                        .type("hero").strength(5L).constitution(5L).dexterity(5L)
                        .intelligence(5L).wisdom(5L).charisma(5L)
                        .build()
                )
        );
        when(superPeopleRepository.save(any(SuperPeople.class))).thenReturn(
                SuperPeople.builder()
                        .name("Big Man").planet("Big Planet").type("Hero")
                        .build()
        );
        SuperPeople result = superPeopleService.update(
                SuperPeople.builder()
                        .name("Big Man").planet("Big Planet").type("Hero")
                        .build()
        );
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateSuperPersonNotFoundExceptionError() throws SuperPeopleNotFoundException {
        when(superPeopleRepository.findById(any())).thenReturn(Optional.empty());
        SuperPeopleNotFoundException thrown = Assertions.assertThrows(SuperPeopleNotFoundException.class,
                () -> {
                    superPeopleService.update(SuperPeople.builder()
                            .name("Big Man").planet("Big Planet").type("Hero").build());
                });
        Assertions.assertEquals("S04", thrown.getCode());
        Assertions.assertEquals("not found", thrown.getMessage());
    }
    // public SuperPeople experienceAndLevelApplier(SuperPeople superPeople, Long xpGained)
    //            throws SuperPeopleNotFoundException {
    //        log.info("initialized xpAndLevelApplier");
    //        experienceAdder(superPeople, xpGained);
    //        nextLevelCalculator(superPeople);
    //        log.info("successfully concluded xpAndLevelApplier");
    //        return superPeople;
    //    }
    @Test
    void testExperienceAndLevelApplierSuccess() throws InvalidNameException, SuperPeopleNotFoundException {
        var heroTested = SuperPeopleBuilder.superPeopleSuccessBuilder();
        when(superPeopleRepository.findById(1L)).thenReturn(Optional.of(heroTested));
        superPeopleService.experienceAndLevelApplier(heroTested.getId(), 100L, true);
    }

    //void testUpdateSuperPersonNotFoundExceptionError() throws SuperPeopleNotFoundException {
    //        when(superPeopleRepository.findById(any())).thenReturn(Optional.empty());
    //        SuperPeopleNotFoundException thrown = Assertions.assertThrows(SuperPeopleNotFoundException.class,
    //                () -> {
    //                    superPeopleService.update(SuperPeople.builder()
    //                            .name("Big Man").planet("Big Planet").type("Hero").build());
    //                });
    //        Assertions.assertEquals("S04", thrown.getCode());
    //        Assertions.assertEquals("not found", thrown.getMessage());
    //    }
    @Test
    void testExperienceAndLevelApplierSuperPeopleNotFoundExceptionError()
            throws SuperPeopleNotFoundException {
        var heroTested = SuperPeopleBuilder.superPeopleSuccessBuilder();
        when(superPeopleRepository.findById(any())).thenReturn(Optional.empty());
        SuperPeopleNotFoundException thrown = Assertions.assertThrows(SuperPeopleNotFoundException.class,
                () ->{superPeopleService.experienceAndLevelApplier(heroTested.getId(), 50L, true);
        });
        Assertions.assertEquals("S04", thrown.getCode());
        Assertions.assertEquals("not found", thrown.getMessage());
    }


}

