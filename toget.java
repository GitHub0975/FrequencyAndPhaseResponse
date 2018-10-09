import javax.swing.*;

import java.awt.*;
public class toget extends JFrame{
	Graph tried;
	static double x_coord,y_coord,xql,yql;
    static int j;
    static double xco[]=new double[50];
    static double x[]=new double[4601];
    static double y[]=new double[4601];
    static double frequency[]=new double[50];
    static double sample=0.1;
    static int mean;
    static String compose;

    
	public void initial(String sin,double[] a,double sampleTime){
		compose=sin;
		if(sampleTime>0)
			sample=sampleTime;
		     for(int n=0;n<x.length;++n){
		         x[n]=0.0;
		         y[n]=0.0;
			  }
		     int j=0;
		     while(sin.indexOf("cos")>0 || sin.indexOf("sin")>0){
		    	int index;
		    	String equation;
		    	 if(sin.indexOf("cos")<6 && sin.indexOf("cos")>0){
		    		 index=sin.indexOf("cos");
		    		 equation=sin.substring(0,index);
		    		 xco[j]=Double.parseDouble(equation);
		    		 xco[j+1]=1000.0;
		    		 index+=4;
		    		 equation=sin.substring(index,sin.indexOf("*n*Ts)"));
		    		 frequency[j]=Double.parseDouble(equation);
		    		 equation=sin.substring(sin.indexOf("*n*Ts)")+6);
		    		 sin=equation;
		    		 j+=2;
		    		 
		    		 continue;
		    		 
		    	 }
		    	 else if(sin.indexOf("sin")<6 && sin.indexOf("sin")>0){
		    		 index=sin.indexOf("sin");
		    		 equation=sin.substring(0,index);
		    		 //System.out.println(equation);
		    		 xco[j]=Double.parseDouble(sin.substring(0,index));
		    		 xco[j+1]=2000.0;
		    		 index+=4;
		    		 equation=sin.substring(index,sin.indexOf("*n*Ts)"));
		    		 frequency[j]=Double.parseDouble(equation);
		    		 equation=sin.substring(sin.indexOf("*n*Ts)")+6);
		    		 sin=equation;
		    		 j+=2;
		    		 continue;
		    	 }
		     }
		     for(int y=0;y<xco.length;++y){
		    	 System.out.println(xco[y]+"fre"+frequency[y]);
		     }
			
			for(int n=0;n<x.length;++n){
				for(int i=0;i<xco.length;i=i+2){
					if(xco[i+1]==1000)
				      x[n]+=xco[i]*Math.cos(frequency[i]*n*sampleTime);
					else if(xco[i+1]==2000)
						x[n]+=xco[i]*Math.sin(frequency[i]*n*sampleTime);
				}
		      }
			for(int n=10;n<x.length;++n){
			    for(int i=0;i<a.length;++i){
				    if((n-i)>=0)
				        y[n-10]+=a[i]*x[n-i];
				    else
		  	        	y[n]+=0;
			    }
			}
	  
		toget drawing=new toget();
		drawing.setVisible(true);
}
	
	public toget(){
		super(compose);
		setSize(1500,850);
        getContentPane().setBackground(Color.white);
	}
public void paint(Graphics g){
	   super.paint(g);
	   tried=new Graph();
       tried.graph(g, 40, -2, 10, -10);

        g.setColor(Color.blue);
        for(int k=-2;k<0;++k){
        	xql=tried.zerox+k*tried.xa;
            yql=0+tried.zeroy;
		    //g.fillOval((int)xql-3,(int)yql-3,6,6);
        }
        
             for(int n=0;n<x.length;++n){
                 xql=tried.zerox+n*sample*tried.xa;
                 yql=tried.zeroy-y[n]*tried.ya;
		         g.fillOval((int)xql-2,(int)yql-3,4,4);
		         g.drawLine((int)xql, (int)yql, (int)xql, (int)tried.zeroy);
             }
        
	}
}