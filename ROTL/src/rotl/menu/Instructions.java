package rotl.menu;

public class Instructions implements MenuOption {

	private static final String menuSectionImageSrc = "..\\resources\\images\\BGinstruction.jpg",
			nextImageSrc = "..\\resources\\images\\Next.png";
	private static final Integer contentFontSize = 30;
	private static Instructions single_instance = null;
	private static String content1 = "\tJocul se bazeaza pe ideea de wave;\r\n"
			+ "- Initial, amandoi jucatorii pornesc doar cu turnurile din baza, toate la\r\n"
			+ "nivelul 1, fara turnuri inafara bazei si fara nici o trupa de soldati;\r\n"
			+ "- Initial, amandoi jucatorii primesc o cantitate de aur pe baza careia pot\r\n"
			+ "opera asa cum se prezinta in capitolul Store si Player pentru a obtine o\r\n"
			+ "armata mai puternica si o aparare mai buna, si pentru a obtine un scor\r\n"
			+ "cat mai bun intr-un wave;\r\n"
			+ "- Exista un time standard de pregatire inainte de fiecare lupta, in care\r\n"
			+ "jucatorul isi poate pregati armata;\r\n" + "- Dupa expirarea timpului standard, lupta incepe:\r\n"
			+ "\to Se iau de la fiecare jucator doar trupele pentru care a fost setat\r\n"
			+ "explicit poarta de parasire a bazei si se trimit pe drumul care\r\n" + "pleaca din acea poarta;\r\n"
			+ "\to Trupele se misca prin campul de lupta pe baza unor algoritmi de\r\n"
			+ "decizie in vederea ajungerii la zidul bazei inamice;\r\n"
			+ "\to Pe drum, se pot intalni cu trupe inamice, caz in care se simuleaza\r\n"
			+ "batalia intre ele. Trupa care supravietuieste(daca exista) isi\r\n"
			+ "continua drumul prin campul de lupta;\r\n"
			+ "\to Pe drum se pot intalni cu turnuri inamice, caz in care se simuleaza\r\n"
			+ "batalia intre ele. Trupa de soldati isi continua drumul prin campul\r\n"
			+ "de lupta, doar daca a reusit sa distruga turnul (sa elibereze calea);";
	private static String content2 = "\to Ajunse la zidul inamic, trupele provoaca daune zidului pana la\r\n"
			+ "caderea acestuia, apoi intra in baza inamica si avanseaza spre\r\n"
			+ "castel; in interior pe fiecare drum va exista cate un turn, care\r\n"
			+ "respecta aceeasi interactiune cu trupele cum este prezentat\r\n" + "anterior;\r\n"
			+ "\to Trupa trecuta si de turnul interior, pe un drum, ajunge la castel,\r\n"
			+ "unde provoaca daune, pana la distrugerea castelului;\r\n"
			+ "\to Distrugerea castelului inamic aduce victoria jucatorului care a\r\n" + "realizat acest lucru;\r\n"
			+ "- Pentru un wave exista un timp standard; la terminarea timpului pentru\r\n"
			+ "un wave, toate trupele se intorc instant in baza de unde provin si toate\r\n"
			+ "daunele provocate de-a lungul wave-ul terminat se pastreaza, fiind\r\n"
			+ "datoria jucatorului sa isi refaca turnurile si armata; castelul si zidul nu pot\r\n"
			+ "fi refacute !!!\r\n" + "- Dupa terminarea wave-ului (bataliei), se reinstaureaza starea de\r\n"
			+ "pregatire, descrisa anterior;\r\n" + "- Scopul jocului presupune distrugerea castelului inamicului;\r\n"
			+ "- Nu exista limite pentru numarul de wave-uri, nivelul armatei sau al\r\n"
			+ "turnurilor, totul poate deveni cat de INSANE este posibil !!";
	private static Integer step = 1;

	private Instructions() {
	}

	public static Instructions getInstructions() {
		if (single_instance == null)
			single_instance = new Instructions();

		return single_instance;
	}

	public void setInstructions() {

	}
}