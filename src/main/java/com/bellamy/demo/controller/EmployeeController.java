package com.bellamy.demo.controller;

import com.bellamy.demo.dto.EmployeeDTO;
import com.bellamy.demo.entity.Employee;
import com.bellamy.demo.entity.Office;
import com.bellamy.demo.exception.ResourceNotFoundException;
import com.bellamy.demo.mappers.EntityMapper;
import com.bellamy.demo.repository.EmployeeRepository;
import com.bellamy.demo.repository.OfficeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
    private final OfficeRepository officeRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityMapper mapper = Mappers.getMapper(EntityMapper.class);

    public EmployeeController(OfficeRepository officeRepository,
                              EmployeeRepository employeeRepository) {
        this.officeRepository = officeRepository;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/offices/{office-id}/employees")
    public ResponseEntity<EmployeeDTO> createOfficeEmployee(
            @PathVariable(value="office-id") UUID officeId,
            @Valid @RequestBody EmployeeDTO employeeDTO) throws ResourceNotFoundException {

        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new ResourceNotFoundException("Office with id: " + officeId + " not found."));

        Employee employee = mapper.toEntity(employeeDTO);
        employee.setOffice(office);
        Employee newEmployee = employeeRepository.save(employee);
        return ResponseEntity.status(CREATED).body(mapper.toDTO(newEmployee));
    }
}
