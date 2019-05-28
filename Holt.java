package holt;

import java.awt.image.BufferedImage;

public class Holt 
{    
    public BufferedImage apply(BufferedImage input) 
    {	
    	int height = input.getHeight();
		  int width = input.getWidth();
		
		  BufferedImage destination = new BufferedImage(width, height, input.getType());
                
      boolean change = true;
      boolean odd = false;
        
        while(change) 
        {	
            change = false;
            odd = !odd;
            
            for (int x = 1; x < width - 1; x++) 
            {
                for (int y = 1; y < height - 1; y++) 
                {
                    if (v(input.getRGB(x, y))) 
                    {
                        int value = pixelValue(getNeighborhood(x, y, input), odd);
                        
                        if (value != input.getRGB(x, y)) 
                            change = true;
                        
                        input.setRGB(x, y, value);
                    }
                }
            }
            
            destination.setRGB(x, y, value);
        }
        
        return destination;
    }
    
protected List<Integer> getNeighborhood(BufferedImage pixels)
	{
		List<Integer> neighborhood = new ArrayList<>();
		
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) 
			{
				int pixel = pixels.getRGB(j, i) / 255;
				neighborhood.forEach(neighbor->neighborhood.add(pixel));
			}
		}
		
		return neighborhood;
	}
	
	protected boolean isEdge(List<Integer> neighborhood)
	{		
		int np = 0;
		
		for (int neighbor : neighborhood) np += neighbor;
		
		return (np >= 2 && np <= 6) && isConnected(neighborhood);
	}
	
	protected boolean isConnected(List<Integer> neighborhood)
	{
		int np = 0;
		
		for (int i = 0; i < neighborhood.size(); i++)
		{
			int x = neighborhood.get(i).intValue();
			int j = neighborhood.get(i + 1).intValue();
			
			np += x < j ? 1 : 0;
		}
	
		return np == 1;
	}
	
	protected boolean v(int pixel)
	{
		return pixel == 255;
	} 
}
