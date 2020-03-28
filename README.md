# Spring DTO Mapper
In this example I have used mapstruct Mapper.
````maven
	<dependency>
	    <groupId>org.mapstruct</groupId>
	    <artifactId>mapstruct</artifactId>
	    <version>1.3.1.Final</version>
	</dependency>
````
````java
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
````		