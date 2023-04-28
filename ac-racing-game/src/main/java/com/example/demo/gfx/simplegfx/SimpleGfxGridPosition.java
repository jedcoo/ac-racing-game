package com.example.demo.gfx.simplegfx;

import com.example.demo.grid.GridColor;
import com.example.demo.grid.GridDirection;
import com.example.demo.grid.position.AbstractGridPosition;
import com.example.demo.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;


/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private Picture rectangle;
    private SimpleGfxGrid simpleGfxGrid;

    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid){
        super((int) (Math.random() * grid.getCols()), 0, grid);

        simpleGfxGrid = grid;

        int x = simpleGfxGrid.columnToX(getCol());
        int y = simpleGfxGrid.rowToY(getRow());

        this.rectangle = new Picture(x, y, "car1.png");

        show();
    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid){
        super(col, row, grid);

        simpleGfxGrid = grid;

        int x = simpleGfxGrid.columnToX(col);
        int y = simpleGfxGrid.rowToY(row);

        this.rectangle = new Picture(x, y, "ferrari.png");

        show();
    }

    /**
     * @see GridPosition#show()
     */
    @Override
    public void show() {
        this.rectangle.draw();
    }

    /**
     * @see GridPosition#hide()
     */
    @Override
    public void hide() {
        this.rectangle.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {

        int initialCol = getCol();
        int initialRow = getRow();

        super.moveInDirection(direction, distance);

        int dx = simpleGfxGrid.columnToX(getCol()) - simpleGfxGrid.columnToX(initialCol);
        int dy = simpleGfxGrid.rowToY(getRow()) - simpleGfxGrid.rowToY(initialRow);

        this.rectangle.translate(dx,dy);

    }
}
