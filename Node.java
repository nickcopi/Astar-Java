import java.util.ArrayList;

class Node{
	public double f;
	public double g;
	public double h;
	public int x;
	public int y;
	public ArrayList<Node> neighbors;
	public Node parent;
	public boolean wall;
	Node(int x,int y){
		this.x = x;
		this.y = y;
		this.f = 0;
		this.wall = false;
		if(Math.random() < 0.4) this.wall = true;
		this.neighbors = new ArrayList<Node>();
	}
	public void findNeighbors(Node[][] grid){
		int col = grid.length;
		int row = grid[0].length;
		if(x > 0) neighbors.add(grid[x-1][y]);
		if(y > 0) neighbors.add(grid[x][y-1]);
		if(x < col-1) neighbors.add(grid[x+1][y]);
		if(y < row-1) neighbors.add(grid[x][y+1]);
		if(x > 0 && y > 0) neighbors.add(grid[x-1][y-1]);
		if(x > 0 && y < row-1) neighbors.add(grid[x-1][y+1]);
		if(x < col-1 && y > 0) neighbors.add(grid[x+1][y-1]);
		if(x < col-1  && y < row-1) neighbors.add(grid[x+1][y+1]);
	}
	public String toString(){
		String toRet = "";
		toRet += "f: " + f + "\n";
		toRet += "g: " + g + "\n";
		toRet += "h: " + h + "\n";
		toRet += "x: " + x + "\n";
		toRet += "y: " + y + "\n";
		return toRet;
	}

}
