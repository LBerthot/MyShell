package fr.afpa.cda.main.commandes;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.afpa.cda.exception.CommandeIntrouvableException;
import fr.afpa.cda.exception.CommandeInvalideException;
import fr.afpa.cda.main.dto.CommandeLine;
import fr.afpa.cda.main.helpers.PathMain;

class MyRmTest {
	CommandeLine cmd;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		cmd = new CommandeLine();
		cmd = PathMain.decouperCommande("myRm -r test.txt");
	}

	@AfterEach
	void tearDown() throws Exception {
		cmd = null;
	}

	@Test
	void testExec() {
		fail("Not yet implemented");
	}

	@Test
	void testMyRmOptionV() {
		assertTrue(MyRm.myRmOptionV(cmd), "Erreur dans l'option");
	}

	@Test
	void testMyRmOptionR() throws CommandeInvalideException, CommandeIntrouvableException {
		assertThrows(IOException.class, ()->{MyRm.myRmOptionR(cmd, false);});
		
	}

	@Test
	void testMyRmWithoutOption() {
		fail("Not yet implemented");
	}

}
