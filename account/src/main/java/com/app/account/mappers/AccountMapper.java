package com.app.account.mappers;

import com.app.account.model.Account;
import com.app.account.model.dto.AccountDto;
import com.app.account.model.dto.AccountUpdateDto;
import com.app.account.model.dto.CreateAccountDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account toEntity(AccountDto dto);

    Account toEntity(CreateAccountDto dto);

    AccountDto toDto(Account entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(AccountUpdateDto dto, @MappingTarget Account entity);
}
