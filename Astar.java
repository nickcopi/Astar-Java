import java.util.ArrayList;

class Astar{
	public static final int col = 10;
	public static final int row = 10;
	public static final int EMPTY = 0;
	public static final int CLOSED = 1;
	public static final int OPEN = 2;
	public static final int PATH = 3;
	public static final int WALL = 4;
	public static ArrayList<Node> openSet = new ArrayList<Node>();
	public static ArrayList<Node> closedSet = new ArrayList<Node>();
	public static ArrayList<Node> walls = new ArrayList<Node>();
	public static void main(String[] args){
		Node endSpot = doAstar();
		drawPath(endSpot);
	}
	public static void drawPath(Node endSpot){
		int[][] grid = new int[col][row];
		for(int x = 0; x < col; x++){
			for(int y = 0; y < row; y++){
				grid[x][y] = EMPTY;
			}
		}
		for(Node open: openSet){
			grid[open.x][open.y] = OPEN;
		}
		for(Node closed: openSet){
			grid[closed.x][closed.y] = CLOSED;
		}
		for(Node wall: walls){
			grid[wall.x][wall.y] = WALL;
		}
		if(endSpot != null){
			while(endSpot.parent != null){
				grid[endSpot.parent.x][endSpot.parent.y] = PATH;
				endSpot.parent = endSpot.parent.parent;

			}
		} else {
			System.out.println("No path!");
		}
		for(int x = 0; x < col; x++){
			for(int y = 0; y < row; y++){
				switch(grid[x][y]){
					case EMPTY:
						System.out.print('.');
					break;
					case WALL:
						System.out.print(' ');
					break;
					case CLOSED:
						System.out.print('X');
					break;
					case OPEN:
						System.out.print('O');
					break;
					case PATH:
						System.out.print('P');
					break;
				}
			}
			System.out.println();
		}

	}
	public static Node doAstar(){
		Node[][] grid = new Node[col][row];
		for(int x = 0; x < col; x++){
			for(int y = 0; y < row; y++){
				grid[x][y] = new Node(x,y);
			}
		}
		for(int x = 0; x < col; x++){
			for(int y = 0; y < row; y++){
				grid[x][y].findNeighbors(grid);
				if(grid[x][y].wall) walls.add(grid[x][y]);
			}
		}
		Node start = grid[0][0];
		Node end = grid[col-1][row-1];
		start.wall = false;
		end.wall = false;
		openSet.add(start);
		while(openSet.size() > 0){
			int winner = 0;
			for(int i = 0; i < openSet.size(); i++){
				if(openSet.get(winner).f > openSet.get(i).f){
					winner = i;
				}
			}
			Node current = openSet.get(winner);
			if(current == end)
			       return current;	
			openSet.remove(current);
			closedSet.add(current);
			for(Node neighbor : current.neighbors){
				if(closedSet.contains(neighbor)|| neighbor.wall)
					continue;
				double tempG = current.g + 1;
				if(openSet.contains(neighbor)){
					if(tempG >= neighbor.g)
						continue;
				} else {
					openSet.add(neighbor);
				}
				neighbor.g = tempG;
				neighbor.f = tempG + dist(neighbor.x,neighbor.y,end.x,end.y);
				neighbor.parent = current;

			}



		}
		return null;
	}
	public static double dist(int x1,int y1,int x2,int y2){
		return Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2));
	}



}
