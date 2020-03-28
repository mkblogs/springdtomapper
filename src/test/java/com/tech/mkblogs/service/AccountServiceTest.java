package com.tech.mkblogs.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
public class AccountServiceTest {

	@BeforeAll
	public static void beforeEachTest() throws Exception {
		log.info("==================================================================================");
		log.info("This is executed before each Test");
	}

	@AfterAll
	public static void afterEachTest() {		
		log.info("This is exceuted after each Test");
		log.info("==================================================================================");
	}
	
	@Test
	public void saveAccount(@Autowired AccountService service) throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName("test");
		accountDTO.setType("saving");
		accountDTO.setAmount(new BigDecimal(300));
		accountDTO.setCreatedBy(1);
		accountDTO.setCreatedName("Test");
		accountDTO = service.saveAccount(accountDTO);
		log.info(accountDTO.toString());
		
	}
	
	@Test
	public void saveAccountWithNamedJDBCTemplate(@Autowired AccountService service) throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName("namedjdbc");
		accountDTO.setType("saving");
		accountDTO.setAmount(new BigDecimal(300));
		accountDTO.setCreatedBy(1);
		accountDTO.setCreatedName("Test");
		accountDTO = service.saveAccountWithNamedJDBCTemplate(accountDTO);
		log.info(accountDTO.toString());
	}
	
	@Test
	public void updateAccount(@Autowired AccountService service) throws Exception {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setAccountId(2);
		accountDTO.setName("namedjdbcone");
		accountDTO.setType("saving");
		accountDTO.setAmount(new BigDecimal(300));
		accountDTO.setLastModifiedBy(1);
		accountDTO.setLastModifiedName("modified_name");
		try {
			accountDTO = service.updateAccount(accountDTO);
			log.info(accountDTO.toString());
		}catch(Exception e){
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void getAccountData(@Autowired AccountService service) throws Exception{
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setName("test");
		accountDTO.setType("saving");
		accountDTO.setAmount(new BigDecimal(300));
		accountDTO.setCreatedBy(1);
		accountDTO.setCreatedName("Test"); 
		accountDTO = service.saveAccount(accountDTO);
		accountDTO = service.getAccount(accountDTO.getAccountId());
		log.info(accountDTO.toString());
	}
	
	@Test
	public void getAllAccountData(@Autowired AccountService service) throws Exception{
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		list = service.getAllData();
		for(AccountDTO accountDTO:list)
			log.info(accountDTO.toString());
	}
	
	@Test
	public void findByAmountTest(@Autowired AccountService service) throws Exception {
		BigDecimal amount = new BigDecimal(100);
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		list = service.findByAmount(amount);
		for(AccountDTO accountDTO:list)
			log.info(accountDTO.toString());
	}
	
	@Test
	public void findByNameAndAmount(@Autowired AccountService service) throws Exception {
		BigDecimal amount = new BigDecimal(100);
		String name="test";
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		list = service.findByNameAndAmount(name, amount);
		for(AccountDTO accountDTO:list)
			log.info(accountDTO.toString());
	}
	
	@Test
	public void search(@Autowired AccountService service) throws Exception {
		String name="test";
		String type = "Saving";
		FilterDTO dto = new FilterDTO();
		dto.setAccountName(name);
		dto.setAccountType(type);
		dto.setAmount("300");
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		list = service.searchWithJdbcTemplate(dto);
		for(AccountDTO accountDTO:list)
			log.info(accountDTO.toString());
	}
}
