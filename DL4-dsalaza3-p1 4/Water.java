import java.awt.Color;
/**
 *   class of the element Sand with its behaviour when it falls down randomly, and when being pushed
 * @author Daniel Salazar
 */
public class Water extends Element {
    /**
     *  Returns the color of the element Water .
     *
     *   @return the current color of the element Water.
     */
    @Override
    public Color getColor() {
        return Color.BLUE;
    }

    /**
     *  Returns the weight of the element. Weights
     * 	  are used for determining which elements
     * 	  are heavier/lighter. Water is less heavier than sand and metal
     *
     * 	  @return the weight of the element
     */
    @Override
    public int getWeight() {
        return 60;
    }

    /**
     * same as Sand it will check what colums and rows the water is at to know where it will move
     * when the world expands or compresses.
     * @param grid the grid that needs to be updated if the element moved
     * @param row the current row the element is in
     * @param col the current column the element is in
     */
    @Override
        public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        if(row<(grid.size()-1) && col>0 && col<(grid.get(0).size()-1)) {
            double r = 8 * Math.random();
            if(r<=2){
                Element left = grid.get(row).get(col-1);
                if (left.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row).set(col-1, new Water());
                }
            }
            else if(r<=5){
                Element center  = grid.get(row+1).get(col);
                if (center.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row +1).set(col, new Water());
                }
            }
           else {
                Element right = grid.get(row).get(col+1);
                if (right.getWeight()<getWeight()){
                    grid.get(row).set(col, new Empty());
                    grid.get(row).set(col+1, new Water());
                }
            }
        }

        }

    /**
     * if the water can be pushed up it will return true, if the water can be pushed up left. Since it chooses directions
     * randomly when it falls from the top it behaves different as if it wehre to fall along another element
     * @param grid the grid that needs to be updated if the element moved
     * @param row the current row the element is in
     * @param col the current column the element is in
     * @return
     */
    @Override
    public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
        boolean canpushLeft = Math.random()<0.5;

        if(canpushLeft){
            if(pushLeft(grid, row ,col)){
                return true;
            }else if(pushUp(grid, row, col)){
                return true;
            }
        }else {
            if(pushUp(grid, row ,col)){
                return true;
            }else if(pushLeft(grid, row, col)){
                return true;
            }
        }
        return false;
    }
}
