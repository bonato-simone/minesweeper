import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MineCell extends Cell{

    public MineCell(Grid grid, int row, int column, double cellSide) {
        super(grid, row, column, cellSide);
        
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {                
                if(grid.peep == 1){
                    peep("a mine");
                    grid.peep = 0;
                    return;
                }
                
                MineCell.this.changeBackground();
                Circle circle = new Circle(cellSide / 2, Color.RED);
                MineCell.this.getChildren().add(circle);
                
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("BOMBA");
                alert.setHeaderText("Hai trovato una bomba! HAI PERSO!");
                alert.showAndWait();
                
                grid.playAgain();
            }
        });
    }
    
}
