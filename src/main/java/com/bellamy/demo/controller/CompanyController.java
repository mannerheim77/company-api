package com.bellamy.demo.controller;

import com.bellamy.demo.aop.interceptors.Timed;
import com.bellamy.demo.dto.CompanyDTO;
import com.bellamy.demo.dto.OfficeDTO;
import com.bellamy.demo.entity.Company;
import com.bellamy.demo.entity.Office;
import com.bellamy.demo.exception.ResourceNotFoundException;
import com.bellamy.demo.mappers.EntityMapper;
import com.bellamy.demo.repository.CompanyRepository;
import com.bellamy.demo.repository.OfficeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1")
public class CompanyController {
    private final CompanyRepository companyRepository;
    private final OfficeRepository officeRepository;
    private final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    @Autowired
    public CompanyController(CompanyRepository companyRepository, OfficeRepository officeRepository) {
        this.companyRepository = companyRepository;
        this.officeRepository = officeRepository;
    }

    @PostMapping("/companies")
    public ResponseEntity<CompanyDTO> createCompany(@Valid @RequestBody Company company) {
        Company newCompany = companyRepository.save(company);
        return ResponseEntity.status(CREATED).body(mapper.toDTO(newCompany));
    }

    @Timed
    @GetMapping("/companies")
    public ResponseEntity<List<CompanyDTO>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        List<CompanyDTO> responses = mapper.toCompanyDTOs(companies);
        return ResponseEntity.status(OK).body(responses);
    }

    @PostMapping("/companies/{id}/offices")
    public ResponseEntity<OfficeDTO> createOffice(@PathVariable(value="id") UUID companyId,
                                                  @Valid @RequestBody OfficeDTO officeDTO)
            throws ResourceNotFoundException {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company with id: " + companyId + " not found."));

        Office office = mapper.toEntity(officeDTO);
        office.setCompany(company);

        Office newOffice = officeRepository.save(office);
        OfficeDTO responseDTO = mapper.toDTO(newOffice);
        return ResponseEntity.status(CREATED).body(responseDTO);
    }

    @GetMapping("/companies/{id}/offices")
    public ResponseEntity<List<OfficeDTO>> getAllOffices(@PathVariable(value="id") UUID companyId) {
        List<Office> offices = companyRepository.findOfficesById(companyId);
        List<OfficeDTO> responses = mapper.toOfficeDTOs(offices);
        return ResponseEntity.status(OK).body(responses);
    }

    @GetMapping("/companies/{company-id}/offices/{office-id}")
    public OfficeDTO getOffice(@PathVariable(value="company-id") UUID companyId,
                               @PathVariable(value="office-id") UUID officeId)
            throws ResourceNotFoundException {
        return officeRepository.findById(officeId)
                .map(mapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Office with id: " + officeId + " not found."));
    }


}
