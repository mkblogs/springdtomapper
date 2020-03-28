package com.tech.mkblogs.controller;

import java.time.LocalTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.mkblogs.dto.AccountDTO;
import com.tech.mkblogs.filter.FilterDTO;
import com.tech.mkblogs.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value = "/api")
@Validated
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/account")
	public ResponseEntity<AccountDTO> saveAccount(@RequestBody @Valid AccountDTO accountDTO) throws Exception {
		log.info("| Request Time - Start - saveAccount() " + LocalTime.now());
		accountDTO = accountService.saveAccount(accountDTO);		
		log.info("| Request Time - End - saveAccount() " + LocalTime.now());
		return ResponseEntity.ok().body(accountDTO);
	}
	
	@PutMapping("/account")
	public ResponseEntity<AccountDTO> updateAccount(@RequestBody @Valid AccountDTO accountDTO) throws Exception {
		log.info("| Request Time - Start - updateAccount() " + LocalTime.now());
		accountDTO = accountService.updateAccount(accountDTO);		
		log.info("| Request Time - End - updateAccount() " + LocalTime.now());
		return ResponseEntity.ok().body(accountDTO);
	}
	
	@GetMapping("/account/{accountId}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountId") 
					@Min(value = 1,message = "{account.id.min}" ) @Valid Integer accountId) throws Exception{
		log.info("| Request Time - Start - getAccount("+accountId+") " + LocalTime.now());
		AccountDTO accountDTO = accountService.getAccount(accountId);		
		log.info("| Request Time - End - getAccount("+accountId+") " + LocalTime.now());
		return ResponseEntity.ok().body(accountDTO);
	}
	
	@GetMapping("/account")
	public ResponseEntity<List<AccountDTO>> getAllAccount() throws Exception{
		log.info("| Request Time - Start - getAllAccount() " + LocalTime.now());
		List<AccountDTO> listAccount = accountService.getAllData();		
		log.info("| Request Time - End - getAllAccount() " + LocalTime.now());
		return ResponseEntity.ok().body(listAccount);
	}
	
	@GetMapping("/account/search")
	public ResponseEntity<List<AccountDTO>> search(@RequestParam("amount") String amount,
			@RequestParam("accountName") String accountName,
			@RequestParam("accountType") String accountType) throws Exception{
		log.info("| Request Time - Start - search() " + LocalTime.now());
		FilterDTO dto = new FilterDTO();
		dto.setAccountName(accountName);
		dto.setAccountType(accountType);
		dto.setAmount(amount);
		List<AccountDTO> listAccount = accountService.searchWithJdbcTemplate(dto);
		log.info("| Request Time - End - search() " + LocalTime.now());
		return ResponseEntity.ok().body(listAccount);
	}
}
