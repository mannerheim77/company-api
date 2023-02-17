package com.bellamy.demo.mappers;

import com.bellamy.demo.dto.CompanyDTO;
import com.bellamy.demo.dto.EmployeeDTO;
import com.bellamy.demo.dto.OfficeDTO;
import com.bellamy.demo.entity.Company;
import com.bellamy.demo.entity.Employee;
import com.bellamy.demo.entity.Office;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EntityMapper {
    OfficeDTO toDTO(Office office);

    CompanyDTO toDTO(Company company);

    EmployeeDTO toDTO(Employee employee);

    Office toEntity(OfficeDTO office);
    Company toEntity(CompanyDTO company);
    Employee toEntity(EmployeeDTO employee);

    List<OfficeDTO> toOfficeDTOs(Iterable<Office> offices);
    List<CompanyDTO> toCompanyDTOs(Iterable<Company> companies);
    List<EmployeeDTO> toEmployeeDTOs(Iterable<Employee> employees);
}
