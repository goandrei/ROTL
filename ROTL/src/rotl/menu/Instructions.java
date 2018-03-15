package rotl.menu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Instructions implements MenuOption {

    private static final String menuSectionImageSrc = "..\\resources\\images\\BGinstruction.jpg",
            nextImageSrc = "..\\resources\\images\\Next.png";
    private static final Integer contentFontSize = 30;
    private static Instructions single_instance = null;
    private static String content1 = "\tJocul se bazeaza pe ideea de wave;\r\n" +
            "- Initial, amandoi jucatorii pornesc doar cu turnurile din baza, toate la\r\n" +
            "nivelul 1, fara turnuri inafara bazei si fara nici o trupa de soldati;\r\n" +
            "- Initial, amandoi jucatorii primesc o cantitate de aur pe baza careia pot\r\n" +
            "opera asa cum se prezinta in capitolul Store si Player pentru a obtine o\r\n" +
            "armata mai puternica si o aparare mai buna, si pentru a obtine un scor\r\n" +
            "cat mai bun intr-un wave;\r\n" +
            "- Exista un time standard de pregatire inainte de fiecare lupta, in care\r\n" +
            "jucatorul isi poate pregati armata;\r\n" +
            "- Dupa expirarea timpului standard, lupta incepe:\r\n" +
            "\to Se iau de la fiecare jucator doar trupele pentru care a fost setat\r\n" +
            "explicit poarta de parasire a bazei si se trimit pe drumul care\r\n" +
            "pleaca din acea poarta;\r\n" +
            "\to Trupele se misca prin campul de lupta pe baza unor algoritmi de\r\n" +
            "decizie in vederea ajungerii la zidul bazei inamice;\r\n" +
            "\to Pe drum, se pot intalni cu trupe inamice, caz in care se simuleaza\r\n" +
            "batalia intre ele. Trupa care supravietuieste(daca exista) isi\r\n" +
            "continua drumul prin campul de lupta;\r\n" +
            "\to Pe drum se pot intalni cu turnuri inamice, caz in care se simuleaza\r\n" +
            "batalia intre ele. Trupa de soldati isi continua drumul prin campul\r\n" +
            "de lupta, doar daca a reusit sa distruga turnul (sa elibereze calea);";
    private static String content2 = "\to Ajunse la zidul inamic, trupele provoaca daune zidului pana la\r\n" +
            "caderea acestuia, apoi intra in baza inamica si avanseaza spre\r\n" +
            "castel; in interior pe fiecare drum va exista cate un turn, care\r\n" +
            "respecta aceeasi interactiune cu trupele cum este prezentat\r\n" +
            "anterior;\r\n" +
            "\to Trupa trecuta si de turnul interior, pe un drum, ajunge la castel,\r\n" +
            "unde provoaca daune, pana la distrugerea castelului;\r\n" +
            "\to Distrugerea castelului inamic aduce victoria jucatorului care a\r\n" +
            "realizat acest lucru;\r\n" +
            "- Pentru un wave exista un timp standard; la terminarea timpului pentru\r\n" +
            "un wave, toate trupele se intorc instant in baza de unde provin si toate\r\n" +
            "daunele provocate de-a lungul wave-ul terminat se pastreaza, fiind\r\n" +
            "datoria jucatorului sa isi refaca turnurile si armata; castelul si zidul nu pot\r\n" +
            "fi refacute !!!\r\n" +
            "- Dupa terminarea wave-ului (bataliei), se reinstaureaza starea de\r\n" +
            "pregatire, descrisa anterior;\r\n" +
            "- Scopul jocului presupune distrugerea castelului inamicului;\r\n" +
            "- Nu exista limite pentru numarul de wave-uri, nivelul armatei sau al\r\n" +
            "turnurilor, totul poate deveni cat de INSANE este posibil !!";
    private static Integer step = 1;

    private Instructions() {
    }

    public static Instructions getInstructions() {
        if (single_instance == null)
            single_instance = new Instructions();

        return single_instance;
    }

    public void setInstructions(Pane root) {

        step = 1;
        try (InputStream is = Files.newInputStream(Paths.get(menuSectionImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(root.getMaxWidth() + 75);
            img.setFitHeight(root.getMaxHeight());
            root.getChildren().add(img);

            Text back = createBackButton(root);

            Text title = createTitle(root);

            Vector<Object> content = createContent(root);

            ImageView next = createNextButton(root);

            back.setOnMouseReleased(event -> {
                root.getChildren().removeAll(img, back, title, (Text) content.get(0),
                        (Text) content.get(1), next);
            });

            next.setOnMouseReleased(event -> {
                if (step == 2) {
                    Text txt1 = (Text) content.get(0);
                    txt1.setVisible(false);
                    Text txt2 = (Text) content.get(1);
                    txt2.setVisible(true);
                }
            });

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
        }
    }

    public ImageView createNextButton(Pane root) {

        try (InputStream is = Files.newInputStream(Paths.get(nextImageSrc))) {
            ImageView img = new ImageView(new Image(is));
            img.setFitWidth(75);
            img.setFitHeight(75);
            img.setTranslateX(975);
            img.setTranslateY(525);
            img.setOpacity(0.9);
            root.getChildren().add(img);

            img.setOnMouseEntered(event -> {
                img.setOpacity(1);
                img.setFitWidth(85);
                img.setFitHeight(85);
                img.setTranslateX(970);
                img.setTranslateY(520);
            });

            img.setOnMouseExited(event -> {
                img.setFitWidth(75);
                img.setFitHeight(75);
                img.setTranslateX(975);
                img.setTranslateY(525);
                img.setOpacity(0.9);
            });

            return img;

        } catch (IOException e) {
            System.out.println("Couldn't load image...");
            return null;
        }
    }

    @Override
    public Text createBackButton(Pane root) {

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0,
                true, CycleMethod.NO_CYCLE, new Stop[]{
                new Stop(0, Color.DEEPSKYBLUE),
                new Stop(0.5, Color.BLACK),
                new Stop(0.5, Color.BLACK),
                new Stop(1, Color.DEEPSKYBLUE)

        });


        Text text = new Text("BACK");
        text.setFill(Color.DARKGREY);
        text.setFont(Font.font("Neuropol", FontWeight.SEMI_BOLD, backButtonFontSize));
        text.setTranslateX(100);
        text.setTranslateY(50);

        root.getChildren().add(text);
        text.setOnMouseEntered(event -> {
            text.setFill(gradient);
            text.setFill(Color.WHITE);
        });

        text.setOnMouseExited(event -> {
            text.setFill(Color.BLACK);
            text.setFill(Color.DARKGREY);
        });
        text.setOnMousePressed(event -> {
            text.setFont(Font.font("Neuropol", FontWeight.BOLD, backButtonFontSize * 1.1));
        });

        return text;
    }

    @Override
    public Text createTitle(Pane root) {

        Text text = new Text("I N S T R U C T I O N S ");
        text.setFill(Color.DEEPSKYBLUE);
        text.setFont(Font.font("Neuropol", FontWeight.EXTRA_BOLD, titleFontSize));
        text.setTranslateX(350);
        text.setTranslateY(50);
        root.getChildren().add(text);

        return text;
    }

    @Override
    public Vector<Object> createContent(Pane root) {

        Text text1 = new Text(content1);
        Text text2 = new Text(content2);
        text1.setFill(Color.LIGHTGOLDENRODYELLOW);
        text1.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize));
        text1.setTranslateX(100);
        text1.setTranslateY(100);
        text1.setVisible(true);
        root.getChildren().add(text1);

        text2.setFill(Color.LIGHTGOLDENRODYELLOW);
        text2.setFont(Font.font("Neuropol X", FontWeight.NORMAL, contentFontSize));
        text2.setTranslateX(100);
        text2.setTranslateY(100);
        text2.setVisible(false);
        root.getChildren().add(text2);
        step++;

        Vector<Object> objVector = new Vector<Object>(2);
        objVector.add(text1);
        objVector.add(text2);

        return objVector;
    }
}