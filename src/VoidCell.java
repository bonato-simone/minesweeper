import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class VoidCell extends Cell{
    private int adjacentMines;

    public VoidCell(Grid grid, int row, int column, double cellSide) {
        super(grid, row, column, cellSide);
        
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(grid.peep == 1){
                    peep("void");
                    grid.peep = 0;
                    return;
                }
                
                VoidCell.this.changeBackground();
                Text text = new Text("" + adjacentMines);
                VoidCell.this.getChildren().add(text);
                grid.updateRemainingCells();
                removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        });
    }
    
    void setAdjacenteMines(int adjacentMines){
        this.adjacentMines = adjacentMines;
    }
}
