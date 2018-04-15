package rotl.menu;

public class Credits implements MenuOption {

	private static final String menuSectionImageSrc = "..\\resources\\images\\BGcredits.jpg",
			nextImageSrc = "..\\resources\\images\\Next.png", logoSrc = "..\\resources\\images\\tb_logo.png";
	
	private static Credits single_instance = null;
	private static String content = "Team : - Bogdan Anghelache\n" + "              - Andrei Gorneanu\n"
			+ "              - Marian Lupascu\n" + "              - Stefan Stevoaca\n"
			+ "              - Stefan Nita\n";

	private Credits() {
	}

	public static Credits getCredits() {
		if (single_instance == null)
			single_instance = new Credits();

		return single_instance;
	}

	public void setCredits() {

	}
}