package hu.webuni.hr.rita.web;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.OptionalLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.reactive.server.WebTestClient;

import hu.webuni.hr.rita.dto.EmployeeDto;
import hu.webuni.hr.rita.model.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIT {
	
	@Autowired
	WebTestClient webTestClient;
	private static final String BASE_URI = "/api/employees";
	
	@Test
	public void testCreateWorkInTheFuture() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime tomorrow = today.plusDays(1);
		createBadEmployee("Ötöske", "tesztelő", 250000, tomorrow);
	}
	
	@Test
	public void testCreateEmptyName() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		createBadEmployee("", "tesztelő", 250000, yesterday);
	}
	
	@Test
	public void testCreateEmptyPosition() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		createBadEmployee("Ötöske", "", 250000, yesterday);
	}
	
	@Test
	public void testCreateNegativeSalary() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		createBadEmployee("Ötöske", "tesztelő", -250000, yesterday);
	}
	
	@Test
	public void testCreateGood() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		createGoodEmployee("Ötöske", "tesztelő", 250000, yesterday);
	}
	
	@Test
	public void testModifyWorkInTheFuture() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime tomorrow = today.plusDays(1);
		modifyBadEmployee("Ötöske", "tesztelő", 250000, tomorrow);
	}
	
	@Test
	public void testModifyEmptyName() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		modifyBadEmployee("", "tesztelő", 250000, yesterday);
	}
	
	@Test
	public void testmodifyEmptyPosition() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		modifyBadEmployee("Ötöske", "", 250000, yesterday);
	}
	
	@Test
	public void testModifyNegativeSalary() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		modifyBadEmployee("Ötöske", "tesztelő", -250000, yesterday);
	}
	
	@Test
	public void testModifyGood() throws Exception {
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime yesterday = today.minusDays(1);
		modifyGoodEmployee("Ötöske", "tesztelő", 250000, yesterday);
	}
	
	//---------------------
	
	public void createBadEmployee(String name, String position, int salary, LocalDateTime employedSince) throws Exception {
		createEmployee(name, position, salary, employedSince, HttpStatus.BAD_REQUEST);
	}
	
	public void createGoodEmployee(String name, String position, int salary, LocalDateTime employedSince) throws Exception {
		createEmployee(name, position, salary, employedSince, HttpStatus.OK);
	}
	
	public void createEmployee(String name, String position, int salary, LocalDateTime employedSince, HttpStatus status) throws Exception {
		long nextId = getMaxId()+1L;
		Employee newEmployee = new Employee(nextId, name, position, salary, employedSince);
		StatusAssertions expectStatus = webTestClient.post().uri(BASE_URI).bodyValue(newEmployee).exchange().expectStatus();
		expectStatus.isEqualTo(status);
	}
	
	public void modifyBadEmployee(String name, String position, int salary, LocalDateTime employedSince) throws Exception {
		modifyEmployee(name, position, salary, employedSince, HttpStatus.BAD_REQUEST);
	}
	
	public void modifyGoodEmployee(String name, String position, int salary, LocalDateTime employedSince) throws Exception {
		modifyEmployee(name, position, salary, employedSince, HttpStatus.OK);
	}
	
	public void modifyEmployee(String name, String position, int salary, LocalDateTime employedSince, HttpStatus status) throws Exception {
		long maxId = getMaxId();
		Employee newEmployee = new Employee(maxId, name, position, salary, employedSince);
		StatusAssertions expectStatus = webTestClient.put()
				.uri(BASE_URI+"/{id}", maxId)
				.bodyValue(newEmployee)
				.exchange()
				.expectStatus();
		expectStatus.isEqualTo(status);
	}
	
	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> responseList = webTestClient
		.get()
		.uri(BASE_URI)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(EmployeeDto.class)
		.returnResult().getResponseBody();
		
		// Collections.sort(responseList, (a1, a2)->Long.compare(a1.getId(), a2.getId()));
		return responseList;
	}
	
	public long getMaxId() {
		List<EmployeeDto> emp_list = getAllEmployees();
		OptionalLong max_id = emp_list.stream().mapToLong(e -> e.getId()).max();
		return max_id.orElse(0);
	}
	
}
