package fr.afpa.cda.main.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.afpa.cda.exception.*;


class PathMainTest {
	String chemin;
	String commande;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		chemin = "./help";
		commande = "myCd ./help -v";
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCalculeCheminDirIfExists() throws CheminInvalideException, CheminRepertoirInvalideException {
		assertNotNull(PathMain.calculeCheminDirIfExists(chemin), "Le résultat est null");
		assertEquals(PathMain.calculeCheminIfExists(chemin),PathMain.calculeCheminDirIfExists(chemin));
		assertThrows(CheminRepertoirInvalideException.class, ()->{PathMain.calculeCheminDirIfExists("./help/myRm.txt");});
	}

	@Test
	void testCalculeCheminIfExists() throws CheminInvalideException {
		assertNotNull(PathMain.calculeCheminIfExists(chemin), "Le résultat est null");
		assertEquals(PathMain.calculeChemin(chemin),PathMain.calculeCheminIfExists(chemin));
		assertThrows(CheminInvalideException.class, ()->{PathMain.calculeCheminIfExists("./Test");});
	}

	@Test
	void testCalculeChemin() {
		assertNotNull(PathMain.calculeChemin(chemin), "Le résultat est null");
		assertEquals(Path.of(PathMain.pathMiniShell, chemin).normalize().toString(),PathMain.calculeChemin(chemin),"Le chemin attendu est faux");
	}

	@Test
	void testDecouperCommande() throws CommandeInvalideException, CommandeIntrouvableException {
		assertNotNull(PathMain.decouperCommande(commande), "Le résultat est null");
		assertEquals("myCd",PathMain.decouperCommande(commande).getNom(),"La commande attendu est fausse");
		assertTrue(PathMain.decouperCommande(commande).getParams().contains("./help"),"Le parametre attendu est faux");
		assertTrue(PathMain.decouperCommande(commande).getOptions().contains("v"),"L'option attendu est faux");
		assertThrows(CommandeIntrouvableException.class, ()->{PathMain.decouperCommande(null);});
		assertThrows(CommandeInvalideException.class, ()->{PathMain.decouperCommande("./help -v");});
	
	}

	@Test
	void testIsCommande() throws CommandeInvalideException, CommandeIntrouvableException {
		assertEquals(true, PathMain.isCommande(PathMain.decouperCommande(commande).getNom()));
	}

	@Test
	void testOptionIsValid() throws OptionInvalidException, CommandeInvalideException, CommandeIntrouvableException {
		String[] allOptions = {"v"};
		List<String> ALL_OPTIONS = new ArrayList<>(Arrays.asList(allOptions));
		assertEquals(true, PathMain.isOption(PathMain.decouperCommande(commande).getOptions(), ALL_OPTIONS));
		assertThrows(OptionInvalidException.class, ()->{PathMain.isOption(PathMain.decouperCommande("myCd ./help -d").getOptions(), ALL_OPTIONS);});
	}

}
