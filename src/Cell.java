import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Cell extends StackPane {
    private final int ROW, COLUMN;
    final Rectangle background;

    public Cell(Grid grid, int row, int column, double cellSide) {
        ROW = row;
        COLUMN = column;
        
        background = new Rectangle(cellSide, cellSide, Color.BLUE);
        
        getChildren().add(background);
    }

    void peep(String s) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PEEP");
        alert.setHeaderText("Cell at row " + (ROW + 1) + " and column " + (COLUMN + 1) + " is " + s);
        alert.showAndWait();
    }

    void changeBackground() {
        background.setFill(Color.YELLOW);
    }
}
