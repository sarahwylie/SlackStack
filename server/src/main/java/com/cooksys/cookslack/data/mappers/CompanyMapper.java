package com.cooksys.cookslack.data.mappers;

import com.cooksys.cookslack.data.dtos.CompanyPatchRequestDto;
import com.cooksys.cookslack.data.dtos.CompanyRequestDto;
import com.cooksys.cookslack.data.dtos.CompanyResponseDto;
import com.cooksys.cookslack.data.model.entities.Company;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    Company requestDtoToEntity(CompanyRequestDto companyRequestDto);

    Company patchRequestToEntity(CompanyPatchRequestDto companyPatchRequestDto);

    CompanyRequestDto entityToRequestDto(Company company);

    CompanyResponseDto entityToResponseDto(Company company);

    List<CompanyResponseDto> entitiesToResponseDtos(List<Company> companies);

}
