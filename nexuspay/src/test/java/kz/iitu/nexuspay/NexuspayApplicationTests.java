package kz.iitu.nexuspay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class NexuspayApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	void contextLoadsAndFlywayV1MigrationApplied() {
		List<String> tables = jdbcTemplate.queryForList(
				"SELECT tablename FROM pg_tables WHERE schemaname = 'public' ORDER BY tablename",
				String.class
		);
		System.out.println("Applied public tables in PostgreSQL: " + tables);
		assertTrue(tables.containsAll(List.of("accounts", "transfers", "ledger_entries", "flyway_schema_history")));
	}

}
