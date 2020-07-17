import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Pos;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Grid extends TilePane{
    final private int N;
    final private Layout PARENT;
    final private double CELL_SIDE = 45;
    private Cell[][] cellsMatrix;
    int peep = 0;

    public Grid(Layout parent, int side) {
        PARENT = parent;
        N = side;
        
        setAlignment(Pos.CENTER);
        setPrefColumns(N);
        setPrefRows(N);
        setPrefTileWidth(CELL_SIDE);
        setPrefTileHeight(CELL_SIDE);
        setVgap(5);
        setHgap(5);
        
        cellsMatrix = new Cell[N][N];
        
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++){
                cellsMatrix[i][j] = null;
                this.getChildren().add(new Rectangle(CELL_SIDE, CELL_SIDE, Color.BLUE));
            }
    }
    
    void random(){
        class Position{
            int i, j;
            Position(int i, int j){
                this.i = i;
                this.j = j;
            }
        }
        
        ArrayList<Position> positionsList = new ArrayList<>();
        
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                positionsList.add(new Position(i, j));
        
        Collections.shuffle(positionsList);
        
        int k;
        for(k = 0; k < N + 1; k++){
            int i = positionsList.get(k).i, j = positionsList.get(k).j;
            cellsMatrix[i][j] = new MineCell(this, i, j, CELL_SIDE);
        }

        for(; k < N * N; k++){
            int i = positionsList.get(k).i, j = positionsList.get(k).j;
            cellsMatrix[i][j] = new VoidCell(this, i, j, CELL_SIDE);
        }
        
        getChildren().clear();
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                getChildren().add(cellsMatrix[i][j]);

        loadVoidCells();
    }
    
    void test(){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++){
                if(i == j || (i == 0 && j == N - 1))
                    cellsMatrix[i][j] = new MineCell(this, i, j, CELL_SIDE);
                else
                    cellsMatrix[i][j] = new VoidCell(this, i, j, CELL_SIDE);
            }
        
                getChildren().clear();
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                getChildren().add(cellsMatrix[i][j]);

        loadVoidCells();
    }

    void loadVoidCells(){
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++){
                if(cellsMatrix[i][j].getClass() == VoidCell.class){
                    int sum = 0;
                    
                    for(int y = -1; y < 2; y++)
                        for(int k = -1; k < 2; k++)
                            sum += isMineCell(i + y, j + k);
                    
                    ((VoidCell) cellsMatrix[i][j]).setAdjacenteMines(sum);
                }
            }
    }
    
    int isMineCell(int row, int column){
        if(row >= 0 && row < N && column >= 0 && column < N)
            if(cellsMatrix[row][column].getClass() == MineCell.class)
                return 1;
        
        return 0;
    }
    
    void playAgain(){
        PARENT.playAgain();
    }

    void updateRemainingCells(){
        PARENT.updateRemainingCells();
    }
    
}
