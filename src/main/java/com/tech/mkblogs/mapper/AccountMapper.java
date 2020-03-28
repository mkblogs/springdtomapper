package com.tech.mkblogs.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.model.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {

	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
	
	@Mappings({
	      @Mapping(target="accountId", source="account.id"),
	      @Mapping(target="name", source="account.accountName"),
	      @Mapping(target="type", source="account.accountType")
	    })
	AccountDTO AccounttoAccountDTO(Account account);
	
	@Mappings({
	      @Mapping(target="id", source="accountDTO.accountId"),
	      @Mapping(target="accountName", source="accountDTO.name"),
	      @Mapping(target="accountType", source="accountDTO.type")
	    })
	Account AccountDTOtoAccount(AccountDTO accountDTO);
}
