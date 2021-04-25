package fr.afpa.cda.main.helpers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.afpa.cda.main.dto.CommandeLine;

class ReadAllFileTest {
	CommandeLine cmd;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		cmd = PathMain.decouperCommande("myRm --help");
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testHelp() {
		fail("Not yet implemented");
	}

}
