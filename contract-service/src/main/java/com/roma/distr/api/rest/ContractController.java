package com.roma.distr.api.rest;

import com.roma.distr.entities.HotelClientContract;
import com.roma.distr.entities.dto.ContractsDTO;
import com.roma.distr.services.rest.ContractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/contracts")
    public ResponseEntity<ContractsDTO> getAllContracts() {
        List<HotelClientContract> contractList = this.contractService.getContracts();
        ContractsDTO contractsDTO = new ContractsDTO();
        contractsDTO.setContracts(contractList);

        return new ResponseEntity<>(contractsDTO, HttpStatus.OK);
    }

    @GetMapping("/contract")
    public ResponseEntity<HotelClientContract> getContractOfClient(
            @RequestParam(value = "clientId") String clientId) {
        System.out.println(clientId);
        HotelClientContract contract = this.contractService.getContractOfClient(UUID.fromString(clientId));

        if (contract != null)
            return new ResponseEntity<>(contract, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/contract")
    public ResponseEntity<Void> createContract(
            @RequestParam(value = "clientId") String clientId,
            @RequestParam(value = "roomId") String roomId) {
        this.contractService.concludeContract(UUID.fromString(clientId), UUID.fromString(roomId));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/contracts/{id}")
    public ResponseEntity<Void> removeContract(@PathVariable(value = "id") String contractId) {
        this.contractService.removeContract(UUID.fromString(contractId));
        return ResponseEntity.ok().build();
    }
}
