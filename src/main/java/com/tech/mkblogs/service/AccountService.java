package com.tech.mkblogs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.mapper.AccountMapper;
import com.tech.mkblogs.model.Account;
import com.tech.mkblogs.repository.JdbcAccountRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AccountService {
	
	@Autowired
	private JdbcAccountRepository repository;

	public AccountDTO saveAccount(AccountDTO accountDTO) throws Exception {
		log.info("Starting the saveAccount() method ");
		try {
			Account account = AccountMapper.INSTANCE.AccountDTOtoAccount(accountDTO);
			Integer generatedKey = repository.saveAccount(account);
			log.info("Generated Primary Key : " + generatedKey);
			accountDTO.setAccountId(generatedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the saveAccount() method ");
		return accountDTO;
	}
	
	public AccountDTO saveAccountWithNamedJDBCTemplate(AccountDTO accountDTO) throws Exception {
		log.info("Starting the saveAccountWithNamedJDBCTemplate() method ");
		try {
			Account account = AccountMapper.INSTANCE.AccountDTOtoAccount(accountDTO);
			Integer generatedKey = repository.saveAccountWithNamedJDBCTemplate(account);
			log.info("Generated Primary Key : " + generatedKey);
			accountDTO.setAccountId(generatedKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the saveAccountWithNamedJDBCTemplate() method ");
		return accountDTO;
	}
	

	public AccountDTO updateAccount(AccountDTO accountDTO) throws Exception {
		log.info("Starting the updateAccount() method ");
		try {
			Optional<Account> dbObjectExists = repository.findById(accountDTO.getAccountId());
			if(dbObjectExists.isPresent()) {
				Integer version = dbObjectExists.get().getVersion();
				version = version + 1;
				accountDTO.setVersion(version);
				Account account = AccountMapper.INSTANCE.AccountDTOtoAccount(accountDTO);
				repository.updateAccount(account);
			}else {
				throw new RuntimeException("Entity Not Found " + accountDTO.getAccountId());
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		log.info("Ended the updateAccount() method ");
		return accountDTO;
	}

	public AccountDTO getAccount(Integer id) throws Exception {
		AccountDTO accountDTO = null;
		log.info("Starting the getAccount() method ");
		try {
			Optional<Account> dbObjectExists = repository.findById(id);
			if(dbObjectExists.isPresent()) {
				Account account = dbObjectExists.get();
				accountDTO = AccountMapper.INSTANCE.AccounttoAccountDTO(account);
			}else {
				throw new RuntimeException("Entity Not Found " + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the getAccount() method ");
		return accountDTO;
	}

	public List<AccountDTO> getAllData() throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the getAllData() method ");
		try {
			List<Account> dbList = repository.findAll();
			for(Account account : dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.AccounttoAccountDTO(account);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the getAllData() method ");
		return list;
	}
	
	public List<AccountDTO> searchWithJdbcTemplate(FilterDTO dto) throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the search() method ");
		try {
			List<Account> dbList  = repository.searchWithJdbcTemplate(dto);
			for(Account account : dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.AccounttoAccountDTO(account);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the search() method ");
		return list;
	}
	
	public List<AccountDTO> findByAmount(BigDecimal amount) throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the findByAmount() method ");
		try {
			List<Account> dbList  = repository.findByAmount(amount);
			for(Account account : dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.AccounttoAccountDTO(account);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the findByAmount() method ");
		return list;
	}
	
	public List<AccountDTO> findByNameAndAmount(String name,BigDecimal amount) throws Exception {
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		log.info("Starting the findByNameAndAmount() method ");
		try {
			List<Account> dbList  = repository.findByNameAndAmount(name, amount);
			for(Account account : dbList) {
				AccountDTO accountDTO = AccountMapper.INSTANCE.AccounttoAccountDTO(account);
				list.add(accountDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("Ended the findByNameAndAmount() method ");
		return list;
	}
}
