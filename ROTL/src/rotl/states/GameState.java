package rotl.states;

import java.awt.Graphics;

import rotl.gfx.Assets;
import rotl.managers.TileManager;
import rotl.utilities.Handler;
import rotl.utilities.XMLLoader;

public class GameState extends State{
	
	private TileManager tileManager;
	
	private int width, height;
	
	private int[][][] layers;
	Assets a = new Assets();

	
	public GameState(int width, int height, Handler handler){
		super(handler);
		
		this.width = width;
		this.height = height;
		
		tileManager = new TileManager();
		XMLLoader load = new XMLLoader();
		layers = load.loadXMLMaps("/maps/final_map.xml");
		a.init();

	}
	
	
	@Override
	public void update(){
		
		
	}

	@Override
	public void render(Graphics g) {
		for(int k = 0;k < 1; ++k) {
			for(int i = 0;i < height / 16 + 1; ++i){
				for(int j = 0;j < width / 64 + 1; ++j) {
					//System.out.print(layers[i][j][k] + " ");
					//g.drawImage(Assets.outsideTiles[10], 0, 0, null);
					int offset;
					if(i % 2 == 0)
						offset = 0;
					else
						 offset = 32;
					tileManager.render(g, -32 + offset + j * 64, -16 + i * 16, Math.max(0, layers[i][j][k]));
				}System.out.println();
			}System.out.println();
		}	
		
	}
}
