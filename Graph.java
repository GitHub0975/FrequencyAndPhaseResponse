import java.awt.*;
public class Graph {
	double xbegin,xend,ybegin,yend;
	double x_length,y_length;
	double zerox,zeroy,currentx,currenty;
	double xa,ya;
	double xr,xl,yt,yb;
	public void graph(Graphics g,int xr,int xl,int yt,int yb){
		x_length=1280.0;
		y_length=690.0;
		xbegin=50.0;
		ybegin=55.0;
		xend=xbegin+x_length;
		yend=ybegin+y_length;
		
		xa=(xend-xbegin)/(xr-xl);
		ya=(yend-ybegin)/(yt-yb);
		zerox=xbegin-xa*xl;
		zeroy=yend+ya*yb;
		
		g.setColor(Color.lightGray);
    	g.drawLine((int)xbegin,(int)zeroy,(int)xend,(int)zeroy);
    	g.drawLine((int)zerox,(int)ybegin,(int)zerox,(int)yend);
    	
    	g.setColor(Color.black);
    	g.drawRect((int)xbegin, (int)ybegin, (int)x_length, (int)y_length);
    	
    	g.setColor(Color.red);
    	g.drawString("n", (int)xend+10, (int)zeroy+2);
		g.drawString("y[n]", (int)zerox-3, (int)ybegin-10);
		
		g.setColor(Color.black);
    	currenty=yt;
    	for(double i=ybegin;i<=yend;i+=ya){
    			g.drawString(""+(int)currenty, (int)xbegin-25, (int)(ybegin+(yt-currenty)*ya)+3);
  
    			if(currenty%5!=0){
    				g.drawLine((int)xbegin-2,(int)(ybegin+(yt-currenty)*ya),(int)xbegin,(int)(ybegin+(yt-currenty)*ya));
    			}
    			else{
    				g.drawLine((int)xbegin-6,(int)(ybegin+(yt-currenty)*ya),(int)xbegin,(int)(ybegin+(yt-currenty)*ya));
    			}
    			currenty--;
    	}

    	currentx=xl;
    	for(int j=(int)xbegin;j<=xend;j+=xa){
    			g.drawString(""+(int)currentx, (int)(xbegin+(currentx-xl)*xa)-6, (int)yend+15);
    			g.setColor(Color.red);
    			for(double k=ybegin;k<=yend;k+=ya){
    				g.drawLine((int)(xbegin+(currentx-xl)*xa),(int)k,(int)(xbegin+(currentx-xl)*xa),(int)k);
    			
    			}
    			g.setColor(Color.black);
    			if(currentx%5!=0){
    				g.drawLine((int)(xbegin+(currentx-xl)*xa),(int)yend+3,(int)(xbegin+(currentx-xl)*xa),(int)yend);
    			}
    			else{
    				g.drawLine((int)(xbegin+(currentx-xl)*xa),(int)yend+7,(int)(xbegin+(currentx-xl)*xa),(int)yend);
    			}
    			currentx++;

    	}
	}
}