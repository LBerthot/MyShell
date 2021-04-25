package fr.afpa.cda.main.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CommandLineTest {
	CommandeLine cl;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		cl = new CommandeLine();
	}

	@AfterEach
	void tearDown() throws Exception {
		cl = null;
	}

	@Test
	void testAddOption() {
		cl.addOption("p");
		assertEquals(1, cl.getOptions().size(), "Erreur à l'addition d'option");
	}

	@Test
	void testAddParam() {
		cl.addParam("./help");
		assertEquals(1, cl.getParams().size(), "Erreur à l'addition de parametre");
	}

}
