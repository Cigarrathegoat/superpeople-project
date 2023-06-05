package br.com.mrmaia.superpeope.storage.apis;

import br.com.mrmaia.superpeope.storage.adapters.SuperPeopleAdapter;
import br.com.mrmaia.superpeope.storage.adapters.SuperPeopleDTOAdapter;
import br.com.mrmaia.superpeope.storage.apis.dto.requests.SuperPeopleDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.errors.ErrorSpecificationDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.DeleteResponseDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.SuperPeopleListResponseDTO;
import br.com.mrmaia.superpeope.storage.apis.dto.responses.responses.SuperPeopleResponseDTO;
import br.com.mrmaia.superpeope.storage.exceptions.BattleAttributeWithValueZeroException;
import br.com.mrmaia.superpeope.storage.exceptions.ExcessiveTotalBattleAttributesException;
import br.com.mrmaia.superpeope.storage.exceptions.InvalidNameException;
import br.com.mrmaia.superpeope.storage.exceptions.SuperPeopleNotFoundException;
import br.com.mrmaia.superpeope.storage.repositories.entities.SuperPeople;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


public interface ISuperPeopleAPI {

    @ApiOperation(value = "add SuperPeople object",
    response = SuperPeopleResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    SuperPeopleResponseDTO add(SuperPeopleDTO superPeopleDTO)
            throws InvalidNameException, BattleAttributeWithValueZeroException,
            ExcessiveTotalBattleAttributesException;

    @ApiOperation(value = "find SuperPeople object",
    response = SuperPeopleListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public SuperPeopleListResponseDTO find(String heroName)
            throws SuperPeopleNotFoundException;

    @ApiOperation(value = "list all SuperPeople objects",
    response = SuperPeopleListResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success")})
    public SuperPeopleListResponseDTO listAll();

    @ApiOperation(value = "update SuperPeople object",
    response = SuperPeopleResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
    @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found"),
    @ApiResponse(code = 400, response = ErrorSpecificationDTO.class, message = "invalid name")})
    public SuperPeopleResponseDTO update(SuperPeopleDTO superPeopleDTO)
            throws SuperPeopleNotFoundException, InvalidNameException;

    @ApiOperation(value = "delete SuperPeople object",
    response = DeleteResponseDTO.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success"),
        @ApiResponse(code = 404, response = ErrorSpecificationDTO.class, message = "not found")})
    public ResponseEntity<DeleteResponseDTO> delete(Long superPeopleId)
            throws SuperPeopleNotFoundException;

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
}
