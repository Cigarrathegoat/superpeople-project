package br.com.mrmaia.superpeope.storage.services.impl;

import br.com.mrmaia.superpeope.storage.exceptions.BattleAttributeWithValueZeroException;
import br.com.mrmaia.superpeope.storage.exceptions.InvalidNameException;
import br.com.mrmaia.superpeope.storage.exceptions.SuperPeopleNotFoundException;
import br.com.mrmaia.superpeope.storage.exceptions.TotalBattleAttributesOverThirtyException;
import br.com.mrmaia.superpeope.storage.repositories.ISuperPeopleRepository;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;
import br.com.mrmaia.superpeope.storage.services.ISuperPeopleService;
import br.com.mrmaia.superpeope.storage.services.ISuperPowerService;
import br.com.mrmaia.superpeope.storage.services.util.SuperPeopleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SuperPeopleService implements ISuperPeopleService {

    public static final long TOTAL_BATTLE_ATTRIBUTES = 30;

    @Autowired
    private ISuperPeopleRepository superPeopleRepository;

    @Autowired
    private ISuperPowerService superPowerService;

    @Override
    public SuperPeople save(SuperPeople superPeople) throws InvalidNameException,
            TotalBattleAttributesOverThirtyException, BattleAttributeWithValueZeroException {
        log.info("initialized SuperPeopleService.save");
        newSuperPeopleValidator(superPeople);
        log.info("save successful");
        return superPeopleRepository.save(superPeople);
    }

    private void newSuperPeopleValidator(SuperPeople superPeople) throws InvalidNameException,
            TotalBattleAttributesOverThirtyException, BattleAttributeWithValueZeroException {
        SuperPeopleUtil.superPeopleNameNullVerifier(superPeople.getName());
        SuperPeopleUtil.superPeopleTotalAttributeValueVerifier(
                SuperPeopleUtil.attributeSum(
                        superPeople.getStrength(), superPeople.getConstitution(),
                        superPeople.getDexterity(), superPeople.getIntelligence(),
                        superPeople.getWisdom(), superPeople.getCharisma()
                ), TOTAL_BATTLE_ATTRIBUTES
        );
        SuperPeopleUtil.superPeopleNoZeroValuesVerifier(
                superPeople.getStrength(), superPeople.getConstitution(),
                superPeople.getDexterity(), superPeople.getIntelligence(),
                superPeople.getWisdom(), superPeople.getCharisma()
        );
    }

    public List<SuperPeople> findSuperPeopleByName(String name) throws SuperPeopleNotFoundException {
        log.info("initialized SuperPeopleService.findSuperPeopleByName");
        var heroFind = superPeopleRepository.findSuperPeopleByName(name);
        SuperPeopleUtil.superPeopleFoundVerifier(name);
        log.info("find successful");
        return heroFind;
    }

    @Override
    public List<SuperPeople> listAll() {
        log.info("initialized SuperPeopleService.listAll");
        log.info("listAll complete");
        return superPeopleRepository.findAll();
    }

    @Override
    public SuperPeople update(SuperPeople superPeople) throws SuperPeopleNotFoundException,
            InvalidNameException, BattleAttributeWithValueZeroException,
            TotalBattleAttributesOverThirtyException {
        log.info("initialized SuperPeopleService.update");
        var heroFind = superPeopleRepository.findById(superPeople.getId())
                .orElseThrow(() -> new SuperPeopleNotFoundException("S04", "not found")
                );
        log.info("processing update");
        heroFind.setStrength(superPeople.getStrength());
        heroFind.setConstitution(superPeople.getConstitution());
        heroFind.setCharisma(superPeople.getCharisma());
        heroFind.setDexterity(superPeople.getDexterity());
        heroFind.setIntelligence(superPeople.getIntelligence());
        heroFind.setWisdom(superPeople.getWisdom());

        newSuperPeopleValidator(heroFind);
        log.info("update complete");
        return superPeopleRepository.save(heroFind);
    }

    @Override
    public void delete(SuperPeople superPeople) throws SuperPeopleNotFoundException {
        log.info("initialized equipmentService.delete");
        var heroDelete = superPeopleRepository.findById(
                superPeople.getId()).orElseThrow(() -> new SuperPeopleNotFoundException(
                        "S04", "not found"));
                log.info("processing delete");
                superPeopleRepository.delete(heroDelete);
                log.info("delete completed");
    }
}