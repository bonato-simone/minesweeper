import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Layout extends VBox {

    private final int SIDE;
    private Grid grid;
    private final Button random, test, leave, peep;
    int remainingCells;
    Text remainingCellsText;
    HBox hBox;

    public Layout(final int side) {
        SIDE = side;

        setFillWidth(false);
        setSpacing(5);

        random = new Button("Random");
        test = new Button("Test");
        leave = new Button("Leave");
        peep = new Button("Peep");

        hBox = new HBox(5);
        play();
        hBox.getChildren().addAll(random, test, leave, peep, remainingCellsText);

        random.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                grid.random();
                changeActiveButtons();
            }
        });

        test.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                grid.test();
                changeActiveButtons();
            }
        });

        peep.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                grid.peep = 1;
                peep.setDisable(true);
            }
        });

        leave.addEventHandler(ActionEvent.ANY, new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                playAgain();
            }
        });

        addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                final String s = event.getCharacter();

                if (s.equals("L") || s.equals("l")) {
                    if (leave.isDisable() == false)
                        leave.fireEvent(new ActionEvent());
                } else if (s.equals("R") || s.equals("r")) {
                    if (random.isDisable() == false)
                        random.fireEvent(new ActionEvent());
                } else if (s.equals("P") || s.equals("p")) {
                    if (peep.isDisable() == false)
                        peep.fireEvent(new ActionEvent());
                } else if (s.equals("T") || s.equals("t")) {
                    if (test.isDisable() == false)
                        test.fireEvent(new ActionEvent());
                }
            }
        });
    }

    void changeActiveButtons() {
        random.setDisable(true);
        test.setDisable(true);
        leave.setDisable(false);
        peep.setDisable(false);
    }

    void play() {
        grid = new Grid(this, SIDE);

        remainingCellsText = new Text();
        remainingCells = SIDE * SIDE + 1;
        updateRemainingCells();

        leave.setDisable(true);
        peep.setDisable(true);
        getChildren().clear();
        getChildren().addAll(grid, hBox);
    }

    void playAgain() {
        play();
        random.setDisable(false);
        test.setDisable(false);
    }

    void updateRemainingCells() {
        remainingCells--;

        if (remainingCells == 0) {
            final Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("WIN");
            alert.setHeaderText("You have found all the remaining cells. YOU WON!");
            alert.showAndWait();
            playAgain();
            return;
        }

        remainingCellsText.setText("Cells to find = " + remainingCells);
    }
}
