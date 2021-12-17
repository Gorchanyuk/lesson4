package ru.swagger.hibernate.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.swagger.hibernate.dto.CreateOrganizationDTO;
import ru.swagger.hibernate.dto.OrganizationDTO;
import ru.swagger.hibernate.entity.Organization;
import ru.swagger.hibernate.service.OrganizationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;
    private final ConversionService conversionService;

    @PostMapping("/organization")
    public Organization create(@RequestBody CreateOrganizationDTO request){
        return organizationService.saveOrganization(request.getName(), request.getEmployees());
    }

    @GetMapping("/organization")
    public List<OrganizationDTO> getOrganization(){
        return organizationService.getOrganizations().stream()
                .map(organization -> conversionService.convert(organization, OrganizationDTO.class))
                .collect(Collectors.toList());
    }

}
