import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class freqResponse extends JFrame{
	
	private JFrame frame;
	private JTextField textField;
    private JTextField textField_1;
    private JTextField textField2;
    private JTextField textField1;
    JTextArea textArea;
	static double[] coco=new double[100];
	static double[] fre=new double[100];
	static double sampleTime;
	static double frequency;
	static String text,cosin,b;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel contentPane;
	static double[] coefyy=new double[6];
	static String record;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					freqResponse frame = new freqResponse();
					frame.setVisible(true);
					frame.getContentPane().setBackground(new Color(240, 230, 140));
					frame.getContentPane().setForeground(Color.ORANGE);
					frame.setBounds(100, 100, 573, 400);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void notifyEvent(Object o) {
	         textArea.setText(o.toString());;
	    }
	public freqResponse() {
		
		super("linear time-invariant system & frequency response");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 307, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(10, 257, 537, 46);
		contentPane.add(textPane);
		
		JLabel lblDelay = new JLabel("System X[n] Delay:");
		lblDelay.setForeground(new Color(128, 0, 0));
		lblDelay.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		lblDelay.setBounds(55, 20, 147, 18);
		contentPane.add(lblDelay);
		
		
		JLabel lblts = new JLabel("Sampling time(Ts):");
		lblts.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		lblts.setBounds(57, 184, 145, 37);
		contentPane.add(lblts);
		lblts.setForeground(new Color(128, 0, 0));
		
		JLabel lblts1 = new JLabel("Angular frequency:");
		lblts1.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		lblts1.setBounds(57, 149, 145, 37);
		contentPane.add(lblts1);
		lblts1.setForeground(new Color(128, 0, 0));
		
		textField = new JTextField();
		textField.setBounds(212, 19, 96, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(212, 192, 96, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField2 = new JTextField();
		textField2.setBounds(212, 157, 96, 21);
		contentPane.add(textField2);
		textField2.setColumns(10);
		
		
		JButton btnXndelay = new JButton("X[n]Delay\u4FC2\u6578");
		btnXndelay.setForeground(new Color(0, 0, 0));
		btnXndelay.setBackground(new Color(255, 140, 0));
		btnXndelay.setFont(new Font("標楷體", Font.PLAIN, 14));
		btnXndelay.setBounds(350, 20, 135, 23);
		contentPane.add(btnXndelay);
		btnXndelay.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String coef=textField.getText();
				int coeff=Integer.parseInt(coef)+1;
				Mydialog ID = new Mydialog(coeff);       //創建自己定義的Dialog
			    ID.setModal(true);   //這個無敵重要 一定要有  意義為  彈出Dialog程序會  暫停在此 各位可以試試有此行與無此行所取得的 值有何變化
			       ID.setBounds(10,0,550,800);
			       testclass tc = ID.getValue();   //呼叫自己創建的Dialog中的一個自訂義function
			      
			       for(int k=0;k<tc.getS().length;++k)
			    	   coco=tc.getS();
			       textPane.setText("");
		    	   for(int i=0;i<coco.length;++i)
		    		   if(coco[i]!=0){
		    			   if(coco[i]<0){
		    				   text=textPane.getText()+coco[i]+"x[n-"+i+"]";
		    			   }
	   			   else
		    			       text=textPane.getText()+"+"+coco[i]+"x[n-"+i+"]";
		    	            textPane.setText(text);
		    		   }
		    	   text=textPane.getText().substring(1,text.length());
		    	   if(coco[0]<0)
		    		   text="-"+text;
		    	   textPane.setText("y[n]="+text);
			}});
		
		JLabel lblYnDelay = new JLabel("System y[n] Delay:");
		lblYnDelay.setForeground(new Color(128, 0, 0));
		lblYnDelay.setFont(new Font("Sitka Small", Font.PLAIN, 14));
		lblYnDelay.setBounds(55, 55, 147, 18);
		contentPane.add(lblYnDelay);
		
		textField1 = new JTextField();
		textField1.setColumns(10);
		textField1.setBounds(212, 54, 96, 21);
		contentPane.add(textField1);
		
		JButton btnYndelay = new JButton("y[n]Delay\u4FC2\u6578");
		btnYndelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String coefy=textField1.getText();
				int coeffy=Integer.parseInt(coefy)+1;
				Mydia I = new Mydia(coeffy);       //創建自己定義的Dialog
			    I.setModal(true);   //這個無敵重要 一定要有  意義為  彈出Dialog程序會  暫停在此 各位可以試試有此行與無此行所取得的 值有何變化
			    I.setBounds(10,0,550,800);
			    testc tc = I.getVa();   //呼叫自己創建的Dialog中的一個自訂義function
			    for(int k=0;k<tc.getSy().length;++k)
			    	   coefyy=tc.getSy();
			    for(int i=1;i<coefyy.length;++i)
			    	System.out.println(coefyy[i]);
			    
		    	   for(int i=1;i<coefyy.length;++i)
		    		   if(coefyy[i]!=0){
		    			   if(coefyy[i]<0){
		    				   text=textPane.getText()+coefyy[i]+"y[n-"+i+"]";
		    			   }
		    			   else
		    			       text=textPane.getText()+"+"+coefyy[i]+"y[n-"+i+"]";
		    	            textPane.setText(text);
		    		   }

		   
		    	   textPane.setText(text);
			}
		});
		btnYndelay.setForeground(Color.BLACK);
		btnYndelay.setFont(new Font("標楷體", Font.PLAIN, 14));
		btnYndelay.setBackground(new Color(255, 140, 0));
		btnYndelay.setBounds(350, 55, 135, 23);
		contentPane.add(btnYndelay);
		
		JButton btnFrequency = new JButton("frequency \u8F38\u5165");
		btnFrequency.setForeground(new Color(0, 0, 0));
		btnFrequency.setBackground(new Color(255, 140, 0));
		btnFrequency.setFont(new Font("標楷體", Font.PLAIN, 14));
		btnFrequency.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String frequency=textField2.getText();
				int fre_num=Integer.parseInt(frequency)+1;
				youdialog freq = new youdialog(fre_num);       //創建自己定義的Dialog
				freq.setModal(true);   //這個無敵重要 一定要有  意義為  彈出Dialog程序會  暫停在此 各位可以試試有此行與無此行所取得的 值有何變化
				freq.setBounds(10,0,550,800);
			       testclassy tc = freq.getValuey();   //呼叫自己創建的Dialog中的一個自訂義function
			    for(int k=0;k<tc.getSy().length;++k)
			    	   fre=tc.getSy();
			}
		});
		btnFrequency.setBounds(350, 154, 135, 23);
		contentPane.add(btnFrequency);
		
		
		
		JButton button_1 = new JButton("\u8A2D\u5B9A\u5408\u6210\u6CE2");
		button_1.setEnabled(false);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_1.getText().equals(""))
				      sampleTime=0.1;
				else
					sampleTime=Double.parseDouble(textField_1.getText());
				cosin Test = new cosin(); 
				Test.setModal(true);
				Test.setBounds(100, 120, 380, 469);
			       testclas tc = Test.getValu();   //呼叫自己創建的Dialog中的一個自訂義function
			    	   textArea.setText(tc.getSyz());
                       b=textArea.getText();
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("標楷體", Font.PLAIN, 14));
		button_1.setBackground(new Color(255, 140, 0));
		button_1.setBounds(350, 104, 135, 23);
		contentPane.add(button_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(87, 90, 247, 49);
		contentPane.add(textArea);
		pack();
		setVisible(true);
		
		JRadioButton rdbtnUnitstep = new JRadioButton("amplitude");
		rdbtnUnitstep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnUnitstep.isSelected()){
					button_1.setEnabled(false);
					btnFrequency.setEnabled(true);
					btnYndelay.setEnabled(true);
					textArea.setText("");
				}
			}
		});
		rdbtnUnitstep.setForeground(new Color(128, 0, 0));
		rdbtnUnitstep.setBackground(new Color(240, 230, 140));
		rdbtnUnitstep.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		buttonGroup.add(rdbtnUnitstep);
		rdbtnUnitstep.setBounds(120, 227, 107, 23);
		contentPane.add(rdbtnUnitstep);
		rdbtnUnitstep.setSelected(true);
		
		JRadioButton rdbtnUnitsample = new JRadioButton("phase");
		rdbtnUnitsample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnUnitsample.isSelected()){
					button_1.setEnabled(false);
					btnFrequency.setEnabled(true);
					btnYndelay.setEnabled(true);
					textArea.setText("");
				}
			}
		});
		rdbtnUnitsample.setForeground(new Color(128, 0, 0));
		rdbtnUnitsample.setBackground(new Color(240, 230, 140));
		rdbtnUnitsample.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		buttonGroup.add(rdbtnUnitsample);
		rdbtnUnitsample.setBounds(270, 227, 107, 23);
		contentPane.add(rdbtnUnitsample);
		
		
		
		JRadioButton rdbtnXn = new JRadioButton("X[n]=");
		rdbtnXn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnXn.isSelected()){
					btnYndelay.setEnabled(false);
					button_1.setEnabled(true);
					btnFrequency.setEnabled(false);
				}
			}
		});
		buttonGroup.add(rdbtnXn);
		rdbtnXn.setSelected(false);
		rdbtnXn.setForeground(new Color(128, 0, 0));
		rdbtnXn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 14));
		rdbtnXn.setBackground(new Color(240, 230, 140));
		rdbtnXn.setBounds(20, 104, 59, 23);
		contentPane.add(rdbtnXn);
		
		JButton button = new JButton("\u7E6A\u5716");
		button.setBackground(new Color(255, 140, 0));
		button.setFont(new Font("標楷體", Font.PLAIN, 14));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int select;
				if(rdbtnUnitstep.isSelected())
					select=1;
				else if(rdbtnUnitsample.isSelected())
					select=0;
				else
					select=2;
				if(textField_1.getText().equals(""))
				      sampleTime=0.1;
				else
					sampleTime=Double.parseDouble(textField_1.getText());
				if(textField2.getText().equals(""))
				      frequency=2;
				else
					frequency=Double.parseDouble(textField2.getText());
				if(select==2){
					toget bb=new toget();
					bb.initial(b,coco,sampleTime);
				}
				else{
				Point a=new Point();
				a.init(coco,sampleTime, fre,select,coefyy);}
		}});
		button.setBounds(216, 323, 87, 23);
		contentPane.add(button);
		
	}
	
	class Mydia extends JDialog{
		testc tcy;

	    public Mydia(int coefyy){
	    	
	       JPanel JPy=new JPanel();
	       add(JPy);
	       JPy.setLayout(null);
	       JPy.setBackground(new Color(240, 230, 140));
	       JLabel[] labelsy = new JLabel[coefyy];
	       for (int i =  1; i < labelsy.length; i++) {
	    	   labelsy[i] = new JLabel("y[n-"+i+"]的係數:");
	    	   if(i>10)
	               labelsy[i].setBounds(15+i/i*205, 35+(i-11)*60 , 100, 20);
	    	   else
	               labelsy[i].setBounds(15, 35+i*60 , 100, 20);
	           JPy.add(labelsy[i]);
	     }
			
	       JTextField[] allFieldy = new JTextField [coefyy];
	      
	       for(int i = 1;i<coefyy;i++)
	       {
	    	   allFieldy[i] = new JTextField(String.valueOf(0));
	    	   if(i>10){
	    		   allFieldy[i].setBounds(105+i/i*205, 35+(i-11)*60 , 50, 20);
	    	   }
	    	   else
	    	    allFieldy[i].setBounds(105, 35+i*60 , 50, 20);
	    	  JPy.add(allFieldy[i]);
	       }
	       
	       JButton button_1 = new JButton("提交");
			button_1.setBounds(172, 660, 87, 23);
			button_1.setBackground(new Color(255, 140, 0));
			button_1.setFont(new Font("標楷體", Font.PLAIN, 14));
			JPy.add(button_1);
	       button_1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					double coefficienty[]=new double[coefyy];
					for(int k=1;k<coefyy;++k)
						coefficienty[k]=Double.parseDouble(allFieldy[k].getText());
					 setVisible(false);                                //點了這個按鈕之後就讓Dialog隱藏
					   tcy=new testc(coefficienty);
					   dispose(); 
				}});
	       
	    }
	    public testc getVa(){
	        setVisible(true);                     //呼叫此Function之後 讓Dialog顯示
	        return tcy;
	    }
	}
	class testc{                              //一個隨便定義的Class  表示 Dialog也可以回傳給Frame 自訂義Class
	    double[] sy;
	    public testc(double[] insy){
	       sy=insy;
	    }
	    public double[] getSy(){
	       return sy;
	    }
	}
	
	class Mydialog extends JDialog{
		testclass tc;

	    public Mydialog(int coef){
	    	
	       JPanel JP=new JPanel();
	       add(JP);
	       JP.setLayout(null);
	       JP.setBackground(new Color(240, 230, 140));
	       JLabel[] labels = new JLabel[coef];
	       for (int i =  0; i < labels.length; i++) {
	    	   labels[i] = new JLabel("X[n-"+i+"]的係數:");
	    	   if(i>10)
	               labels[i].setBounds(15+i/i*205, 35+(i-11)*60 , 100, 20);
	    	   else
	               labels[i].setBounds(15, 35+i*60 , 100, 20);
	           JP.add(labels[i]);
	     }
			
	       JTextField[] allField = new JTextField [coef];
	      
	       for(int i = 0;i<coef;i++)
	       {
	    	   allField[i] = new JTextField(String.valueOf(0));
	    	   if(i>10){
	    		   allField[i].setBounds(105+i/i*205, 35+(i-11)*60 , 50, 20);
	    	   }
	    	   else
	    	    allField[i].setBounds(105, 35+i*60 , 50, 20);
	    	  JP.add(allField[i]);
	       }
	       
	       JButton button_1 = new JButton("提交");
			button_1.setBounds(172, 660, 87, 23);
			button_1.setBackground(new Color(255, 140, 0));
			button_1.setFont(new Font("標楷體", Font.PLAIN, 14));
			JP.add(button_1);
	       button_1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					double coefficient[]=new double[coef];
					for(int k=0;k<coef;++k)
						coefficient[k]=Double.parseDouble(allField[k].getText());
					 setVisible(false);                                //點了這個按鈕之後就讓Dialog隱藏
					   tc=new testclass(coefficient);
					   dispose(); 
				}});
	       
	    }
	    public testclass getValue(){
	        setVisible(true);                     //呼叫此Function之後 讓Dialog顯示
	        return tc;
	    }
	}

	class testclass{                              //一個隨便定義的Class  表示 Dialog也可以回傳給Frame 自訂義Class
	    double[] s;
	    public testclass(double[] ins){
	       s=ins;
	    }
	    public double[] getS(){
	       return s;
	    }
	}


	class youdialog extends JDialog{
		testclassy tcy;

	    public youdialog(int coefyy){
	    	
	       JPanel JPy=new JPanel();
	       add(JPy);
	       JPy.setLayout(null);
	       JPy.setBackground(new Color(240, 230, 140));
	       JLabel[] labelsy = new JLabel[coefyy-1];
	       for (int i =  0; i < labelsy.length; i++) {
	    	   labelsy[i] = new JLabel("頻率(W"+i+")");
	    	   if(i>10)
	               labelsy[i].setBounds(15+i/i*205, 35+(i-11)*60 , 100, 20);
	    	   else
	               labelsy[i].setBounds(15, 35+i*60 , 100, 20);
	           JPy.add(labelsy[i]);
	     }
			
	       JTextField[] allFieldy = new JTextField [coefyy-1];
	      
	       for(int i = 0;i<coefyy-1;i++)
	       {
	    	   allFieldy[i] = new JTextField(String.valueOf(3*i+4));
	    	   if(i>10){
	    		   allFieldy[i].setBounds(105+i/i*205, 35+(i-11)*60 , 50, 20);
	    	   }
	    	   else
	    	    allFieldy[i].setBounds(105, 35+i*60 , 50, 20);
	    	  JPy.add(allFieldy[i]);
	       }
	       
	       JButton button_1 = new JButton("提交");
			button_1.setBounds(172, 660, 87, 23);
			button_1.setBackground(new Color(255, 140, 0));
			button_1.setFont(new Font("標楷體", Font.PLAIN, 14));
			JPy.add(button_1);
	       button_1.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					
					double coefficienty[]=new double[coefyy-1];
					for(int k=0;k<coefyy-1;++k)
						coefficienty[k]=Double.parseDouble(allFieldy[k].getText());
					 setVisible(false);                                //點了這個按鈕之後就讓Dialog隱藏
					   tcy=new testclassy(coefficienty);
					   dispose(); 
				}});
	       
	    }
	    public testclassy getValuey(){
	        setVisible(true);                     //呼叫此Function之後 讓Dialog顯示
	        return tcy;
	    }
	}
	class testclassy{                              //一個隨便定義的Class  表示 Dialog也可以回傳給Frame 自訂義Class
	    double[] sy;
	    public testclassy(double[] insy){
	       sy=insy;
	    }
	    public double[] getSy(){
	       return sy;
	    }
	}

	public class cosin extends JDialog {

		private JPanel contentPane;
	    String enter;
	    private JTextField textPane;
	    testclas tcy;
		
	    
		public cosin() {
			getContentPane().setLayout(null);
			//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 380, 469);
			contentPane = new JPanel();
			contentPane.setBackground(new Color(204, 255, 153));
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JButton btnOne = new JButton("1");
			btnOne.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"1";
					textPane.setText(enter);
				}
			});
			btnOne.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnOne.setBackground(new Color(204, 204, 0));
			btnOne.setBounds(24, 205, 69, 58);
			contentPane.add(btnOne);
			
			JButton btnTwo = new JButton("2");
			btnTwo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"2";
					textPane.setText(enter);
				}
			});
			btnTwo.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnTwo.setBackground(new Color(204, 204, 0));
			btnTwo.setBounds(100, 205, 69, 58);
			contentPane.add(btnTwo);
			
			JButton btnThree = new JButton("3");
			btnThree.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"3";
					textPane.setText(enter);
				}
			});
			btnThree.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnThree.setBackground(new Color(204, 204, 0));
			btnThree.setBounds(178, 205, 69, 58);
			contentPane.add(btnThree);
			
			JButton btnFour = new JButton("4");
			btnFour.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"4";
					textPane.setText(enter);
				}
			});
			btnFour.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnFour.setBackground(new Color(204, 204, 0));
			btnFour.setBounds(24, 143, 69, 58);
			contentPane.add(btnFour);
			
			JButton btnFive = new JButton("5");
			btnFive.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"5";
					textPane.setText(enter);
				}
			});
			btnFive.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnFive.setBackground(new Color(204, 204, 0));
			btnFive.setBounds(100, 143, 69, 58);
			contentPane.add(btnFive);
			
			JButton btnSix = new JButton("6");
			btnSix.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"6";
					textPane.setText(enter);
				}
			});
			btnSix.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnSix.setBackground(new Color(204, 204, 0));
			btnSix.setBounds(178, 143, 69, 58);
			contentPane.add(btnSix);
			
			JButton btnNine = new JButton("9");
			btnNine.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"9";
					textPane.setText(enter);
				}
			});
			btnNine.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnNine.setBackground(new Color(204, 204, 0));
			btnNine.setBounds(178, 78, 69, 58);
			contentPane.add(btnNine);
			
			JButton btnEight = new JButton("8");
			btnEight.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"8";
					textPane.setText(enter);
				}
			});
			btnEight.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnEight.setBackground(new Color(204, 204, 0));
			btnEight.setBounds(100, 78, 69, 58);
			contentPane.add(btnEight);
			
			JButton btnSeven_1 = new JButton("7");
			btnSeven_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"7";
					textPane.setText(enter);
				}
			});
			btnSeven_1.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnSeven_1.setBackground(new Color(204, 204, 0));
			btnSeven_1.setBounds(24, 78, 69, 58);
			contentPane.add(btnSeven_1);
			
			JButton btnCos = new JButton("cos(");
			btnCos.setFont(new Font("標楷體", Font.PLAIN, 14));
			btnCos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"cos(";
					textPane.setText(enter);
				}
			});
			btnCos.setBounds(24, 265, 69, 58);
			contentPane.add(btnCos);
			
			JButton btnZero = new JButton("sin(");
			btnZero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"sin(";
					textPane.setText(enter);
				}
			});
			btnZero.setFont(new Font("標楷體", Font.PLAIN, 14));
			btnZero.setBounds(178, 265, 69, 58);
			contentPane.add(btnZero);
			
			JButton button_8 = new JButton("-");
			button_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"-";
					textPane.setText(enter);
				}
			});
			button_8.setFont(new Font("標楷體", Font.PLAIN, 23));
			button_8.setBounds(257, 265, 69, 58);
			contentPane.add(button_8);
			
			JButton button_11 = new JButton(".");
			button_11.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+".";
					textPane.setText(enter);
				}
			});
			button_11.setFont(new Font("標楷體", Font.PLAIN, 23));
			button_11.setBounds(24, 333, 69, 51);
			contentPane.add(button_11);
			
			JButton button_12 = new JButton("0");
			button_12.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"0";
					textPane.setText(enter);
				}
			});
			button_12.setFont(new Font("標楷體", Font.PLAIN, 23));
			button_12.setBackground(new Color(204, 204, 0));
			button_12.setBounds(100, 265, 70, 59);
			contentPane.add(button_12);
			
			JButton button_9 = new JButton("+");
			button_9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					enter=textPane.getText()+"+";
					textPane.setText(enter);
				}
			});
			button_9.setFont(new Font("標楷體", Font.PLAIN, 23));
			button_9.setBounds(257, 205, 69, 58);
			contentPane.add(button_9);
			
			JButton btnSeven = new JButton("C");
			btnSeven.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textPane.getText().length()>0){
						StringBuilder a=new StringBuilder(textPane.getText());
						a.deleteCharAt(textPane.getText().length()-1);
						enter=a.toString();
						textPane.setText(enter);
					}
				}
			});
			btnSeven.setFont(new Font("標楷體", Font.PLAIN, 23));
			btnSeven.setBounds(257, 143, 69, 58);
			contentPane.add(btnSeven);
			
			JButton btnReset = new JButton("reset");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textPane.setText("");
				}
			});
			btnReset.setFont(new Font("標楷體", Font.PLAIN, 14));
			btnReset.setBounds(257, 78, 69, 58);
			contentPane.add(btnReset);
			
			JButton btnSet = new JButton("set");
			btnSet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false); 
					tcy=new testclas(enter);
					dispose();
					
				}
			});
			btnSet.setBounds(132, 345, 87, 32);
			contentPane.add(btnSet);
			
			JButton btnnts = new JButton("*n*Ts)");
			btnnts.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					record=enter;
					while(record.indexOf(")")>0)
						record=record.substring(record.indexOf(")")+1);
					enter=textPane.getText()+"*n*Ts)";
					textPane.setText(enter);
					for(int i=0;i<coco.length;++i)
						System.out.println(coco[i]);
					System.out.println(sampleTime);
					System.out.println(record);
					x t=new x();
					x.xinitial(record,coco,sampleTime);
				}
			});
			btnnts.setFont(new Font("標楷體", Font.PLAIN, 11));
			btnnts.setBounds(251, 333, 75, 58);
			contentPane.add(btnnts);
			
			textPane = new JTextField();
			textPane.setFont(new Font("標楷體", Font.PLAIN, 20));
			textPane.setHorizontalAlignment(SwingConstants.RIGHT);
			textPane.setBounds(24, 17, 302, 51);
			contentPane.add(textPane);
			textPane.setColumns(10);
			
			
		}
		public testclas getValu(){
	        setVisible(true);                     //呼叫此Function之後 讓Dialog顯示
	        return tcy;
	    }
		 
	}
	class testclas{                              //一個隨便定義的Class  表示 Dialog也可以回傳給Frame 自訂義Class
			    String sy;
			    public testclas(String insy){
			       sy=insy;
			    }
			    public String getSyz(){
			       return sy;
			    }
	}

}