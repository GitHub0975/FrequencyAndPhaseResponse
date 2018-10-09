import javax.swing.*;
import java.util.Random;

import java.awt.*;
public class Point extends JFrame{
	Graph tried;
	static double x_coord,y_coord,xql,yql;
    static int j;
    static double x[][]=new double[100][1601];
    static double result[][]=new double[100][1601];
    static double y[][]=new double[100][1601];
    static double frequency[]=new double[100];
    static double phase[]=new double[50];
    static double sample=0.1;
    static double phaseMean;
    static int counter=0;
    static double ylar[]=new double[100];
    static double ylar1[]=new double[100];
    static double xlar[]=new double[100];
    static double xphase[]=new double[1601];
    static double yphase[]=new double[1601];
    static int mean;

    
	public void init(double[] a,double sampleTime,double[] fre,int select,double[] f){
		 Random ran = new Random();
		if(sampleTime>0)
			sample=sampleTime;
		frequency=fre;
		for(int w=0;w<fre.length;++w)
		     for(int n=0;n<x[w].length;++n){
		         x[w][n]=0.0;
		         y[w][n]=0.0;
			  }
		for(int w=0;w<fre.length;++w){
			counter=w;
			
			for(int n=0;n<1601;++n){
				      x[w][n]=Math.cos(fre[w]*n*sample);
		      }
			for(int n=0;n<1601;++n){
			    for(int i=0;i<a.length;++i){
				    if((n-i)>=0)
				        result[w][n]+=a[i]*x[w][n-i];
				    else
		  	        	result[w][n]+=0;
			    }
			}
			
			y[w][0]=result[w][0];
			for(int n=1;n<1601;++n){
				y[w][n]=result[w][n];
				for(int x=1;x<f.length;++x){
					if((n-x)>=0){
						y[w][n]+=f[x]*y[w][n-x];
					}
					else{
						y[w][n]+=0;
					}
				}
			}
			/*for(int i=0;i<1601;++i){
				System.out.println("result"+result[0][i]);
				System.out.println("y"+y[0][i]);
			}*/
			
		}
	    if(select==1){
	    	for(int n=0;n<ylar.length;++n)
		         ylar[n]=0.0;
	        ylar=largest(y);
	        /*for(int i=0;i<ylar.length;++i)
				System.out.println("result"+ylar[i]);*/
	    }
	    else{
	    	for(int n=0;n<ylar.length;++n)
		         ylar[n]=0.0;
	    	ylar1=phaselar(y);
	    	xlar=phaselar(x);
	    	/*for(int w=0;w<xlar.length;++w){
	    		for(int i=10;i<x[w].length-10;++i)
	    			if(x[w][i]>x[w][i-1] && x[w][i]>x[w][i+1]){
	    				xlar[w]=i;
	    			System.out.println("resultx"+xlar[w]);
	    			break;}
	    	}
	    	for(int w=0;w<ylar1.length;++w){或快
	    		for(int i=10;i<y[w].length-10;++i)
	    			if(y[w][i]>y[w][i-1] && y[w][i]>y[w][i+1]){
	    				ylar1[w]=i;
	    			System.out.println("resulty"+ylar1[w]);
	    			break;}
	    	}*/
	    	/*for(int w=0;w<frequency.length;++w){
	    		j=0;
	    		for(int i=1;i<x[w].length-1;++i){
	    			if(x[w][i]>x[w][i-1] && x[w][i]>x[w][i+1]){    //large程綼┏翴
	    					xphase[j]=i;
	    					System.out.println("xphase"+j+":"+xphase[j]);
	    					++j;
	    			}
	    		}
	    		j=0;
	    		for(int i=1;i<y[w].length-1;++i){
	    		    if(y[w][i]>y[w][i-1] && y[w][i]>y[w][i+1]){    //large程綼┏翴
    					   yphase[j]=i;
    					   System.out.println("yphase"+j+":"+yphase[j]);
    					   ++j;
    			    }
	    		}
	    		phaseMean=0.0;
	    		for(int i=0;i<50;++i){
	    			
	    		    mean=ran.nextInt(j);
	    		    System.out.println("mean:"+mean);
	    		    System.out.println(xphase[mean]);
	    		    System.out.println(yphase[mean]);
	    		    phaseMean+=(xphase[mean]-yphase[mean]);

	    	    }
	    		phase[w]=phaseMean/50;
	    		System.out.println(phase[w]);
	    	}*/
	    	for(int n=0;n<ylar.length;++n)
		         ylar[n]=0.0;
	    	
	    	for(int i=0;i<frequency.length;++i){
	    		ylar[i]=frequency[i]*(xlar[i]-ylar1[i])*sample*sample;
	    		System.out.println(frequency[i]+"  "+sample+"  "+" "+xlar[i]+" "+ylar1[i]+" "+(xlar[i]-ylar1[i])+" "+(xlar[i]-ylar1[i])*sample+" "+ylar[i]);
	    		if(ylar[i]>1.57){
	    		    while(ylar[i]>1.57)
	    			    ylar[i]-=3.14;}
	    		if(ylar[i]<-1.57){
	    		    while(ylar[i]<-1.57)
	    			    ylar[i]+=3.14;}
	    	}
	    	for(int i=0;i<ylar.length;++i)
		        System.out.println("aaa  "+ylar[i]);
	    }
		Point drawing=new Point();
		drawing.setVisible(true);
	}
	
	public Point(){
		super("response");
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
		     g.fillOval((int)xql-2,(int)yql-3,4,4);
        }
        
             for(int n=0;n<frequency.length;++n){
                 xql=tried.zerox+frequency[n]*sample*tried.xa;
                 yql=tried.zeroy-ylar[n]*tried.ya;
		         g.fillOval((int)xql-2,(int)yql-2,4,4);
		         g.drawLine((int)xql, (int)yql, (int)xql, (int)tried.zeroy);
             }
        
	}
public double[] largest(double in[][]){
	    double big[]=new double[100];
	   
	    for(int i=0;i<=counter;++i)
	    	big[i]=in[i][50];
	    
	    for(int i=0;i<=counter;++i){
	    	for(int j=50;j<in[i].length;++j){
	    		if(in[i][j]>big[i])
	    			big[i]=in[i][j];
	    	}
	    }
	  
	    return(big);
    }
public double[] phaselar(double in[][]){
    double big[]=new double[100];
    double lar[]=new double[100];
   
    for(int i=0;i<=counter;++i){
    	big[i]=in[i][50];
    	lar[i]=50;
    }
    
    for(int i=0;i<=counter;++i){
    	for(int j=50;j<in[i].length;++j){
    		if(in[i][j]>big[i]){
    			big[i]=in[i][j];
    			lar[i]=j;
    		}
    	}
    }
  
    return(lar);
}
}