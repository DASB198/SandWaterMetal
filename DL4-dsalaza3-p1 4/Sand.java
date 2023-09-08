//TO DO: Complete one method and the JavaDocs.
//Don't forget to add your name to the author list!////
import java.awt.Color;
/**
 *   class of the element Sand with its behaviour when it falls down or diagonally, and when being pushed
 * @author Daniel Salazar
 */
public class Sand extends Element{
    /**
     *  Returns the color of the element Sand .
     *
     *   @return the current color of the element Sand.
     */

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }

    /**
     *  Returns the weight of the element. Weights
     * 	  are used for determining which elements
     * 	  are heavier/lighter. Sand is heavier than water but not metal
     *
     * 	  @return the weight of the element
     */
    @Override
    public int getWeight() {
        return 100;
    }

    /**
     *
     * @param grid the grid that needs to be updated if the element moved
     * @param row the current row the element is in
     * @param col the current column the element is in
     */
    @Override
    public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        /**
         * here we check the movements and give the cases if it is falling straight down adding sand on
         * each row of the same colum and the previous row being empty unless is starting to
         * fill up with sand.
         * then we have the cases where it could fall diagonally.
         * it is checking the places where the sand is at to know where it could move
         *          when the world compresses or expands
         */

        int ncols = grid.get(0).size();
        if(row<(grid.size()-1)){
            Element below = grid.get(row+1).get(col);
            if(below.getWeight()<getWeight()){
                grid.get(row).set(col, new Empty());
                grid.get(row +1).set(col, new Sand());
            }
            else if(col==0){
                Element right = grid.get(row+1).get(col+1);
                if(right.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row +1).set(col+1, new Sand());
                }

            }

            else if(col==(ncols-1)){
                Element left = grid.get(row+1).get(col-1);
                if(left.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row +1).set(col-1, new Sand());
                }

            }

            else if(col>0 && col<(grid.get(0).size()-1)){
                Element left = grid.get(row+1).get(col-1);
                Element right = grid.get(row+1).get(col+1);
                if(left.getWeight()<getWeight() && right.getWeight()<getWeight()){
                    if(Math.random()<0.5){
                        grid.get(row).set(col, new Empty());
                        grid.get(row +1).set(col-1, new Sand());
                    }else{
                        grid.get(row).set(col, new Empty());
                        grid.get(row +1).set(col+1, new Sand());
                    }
                }
                else if(left.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row +1).set(col-1, new Sand());
                }else if(right.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row +1).set(col+1, new Sand());
                }
            }
        }

    }

    /**
     * if the sand can be pushed up it will return true, if the sand can be pushed up left when resizing the simulation
     * then it returns true, but if it can't it will compress returning false.
     * @param grid the grid that needs to be updated if the element moved
     * @param row the current row the element is in
     * @param col the current column the element is in
     * @return
     */
    @Override
    public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        if(pushUp(grid, row, col)){
            return true;
        }else if(pushLeft(grid, row, col)){
            return true;
        }
        return false;
    }
}
