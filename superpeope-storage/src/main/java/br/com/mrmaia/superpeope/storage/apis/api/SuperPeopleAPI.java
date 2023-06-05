package br.com.mrmaia.superpeope.storage.apis.api;

import br.com.mrmaia.superpeope.storage.adapters.SuperPeopleAdapter;
import br.com.mrmaia.superpeope.storage.adapters.SuperPeopleDTOAdapter;
import br.com.mrmaia.superpeope.storage.apis.ISuperPeopleAPI;
import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.DeleteResponseDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.SuperPeopleListResponseDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.SuperPeopleResponseDTO;
import br.com.mrmaia.superpeope.storage.exceptions.BattleAttributeWithValueZeroException;
import br.com.mrmaia.superpeope.storage.exceptions.ExcessiveTotalBattleAttributesException;
import br.com.mrmaia.superpeope.storage.exceptions.InvalidNameException;
import br.com.mrmaia.superpeope.storage.exceptions.SuperPeopleNotFoundException;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;
import br.com.mrmaia.superpeope.storage.services.ISuperPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("V1/super-people")
public class SuperPeopleAPI implements ISuperPeopleAPI {

    @Autowired
    private ISuperPeopleService superPeopleService;

    @PostMapping("/new")
    public SuperPeopleResponseDTO add(@RequestBody SuperPeopleDTO superPeopleDTO)
            throws InvalidNameException, BattleAttributeWithValueZeroException,
            ExcessiveTotalBattleAttributesException {
        return SuperPeopleResponseDTO.builder()
                .data(
                        SuperPeopleDTOAdapter.convertTo(
                                superPeopleService.save(SuperPeopleAdapter.convertTo(superPeopleDTO))
                        )
                ).build();
    }

    @GetMapping("/find/{super-people}")
    public SuperPeopleListResponseDTO find(@PathVariable("super-people") String heroName)
        throws SuperPeopleNotFoundException {
        return SuperPeopleListResponseDTO.builder()
                .data(
                        SuperPeopleDTOAdapter.convertToList(
                                superPeopleService.findSuperPeopleByName(
                                        heroName)
                        )
                ).build();
    }

    @GetMapping("/list")
    public SuperPeopleListResponseDTO listAll() {
        return SuperPeopleListResponseDTO.builder()
                .data(
                        SuperPeopleDTOAdapter.convertToList(
                                superPeopleService.listAll()
                        )
                ).build();
    }

    @PutMapping("/change/super-people")
    public SuperPeopleResponseDTO update(@RequestBody SuperPeopleDTO superPeopleDTO)
        throws SuperPeopleNotFoundException, InvalidNameException {
        return SuperPeopleResponseDTO.builder()
                .data(
                        SuperPeopleDTOAdapter.convertTo(
                                superPeopleService.update(
                                        SuperPeopleAdapter.convertTo(superPeopleDTO)
                                )
                        )
                ).build();
    }
/*
    @PutMapping("/battle-result/super-people")
    public BattleResultResponseDTO battleExperienceAndLevelApplier(@RequestBody SuperPeopleDTO battleResultDTO)
        throws SuperPeopleNotFoundException {

        return BattleResultResponseDTO.builder()
                .data(
                        SuperPeopleDTOAdapter.convertTo(
                                superPeopleService.experienceAndLevelApplier(battleResultDTO)
                        )
                ).build();
    }

 */
    @DeleteMapping("/{superPeopleId}")
    public ResponseEntity<DeleteResponseDTO> delete(@PathVariable("superPeopleId") Long superPeopleId)
        throws SuperPeopleNotFoundException {
        superPeopleService.delete(SuperPeople.builder().id(superPeopleId).build());

        return ResponseEntity.ok(DeleteResponseDTO.builder()
                .deleteSuccessMessage("SuperPerson successfully deleted")
                .build());
    }
}
