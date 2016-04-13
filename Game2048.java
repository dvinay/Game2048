import java.awt.*;//it import the required classes
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.util.*;

public class Game2048 extends JFrame implements ActionListener,KeyEventPostProcessor
{
	private static Game2048 openFrame;
	private static int a[]=new int[16];
	
	JTextField jTextFieldArray[] = new JTextField[16]; 
	String textFieldStrings[] = new String[16];
	
	JButton jButton1=new JButton("Start");
	public static void main(String args[])
	{
		new Game2048();
	}
	
	Game2048()
	{
		//if (openFrame != null)
		//{
		//	openFrame.dispose();
		//}
		//openFrame = this;
		jButton1.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(jButton1);
		jButton1.setBounds(30,260, 90, 30);

		for (int i = 0,x=0,y=0; i < jTextFieldArray.length; i++) 
		{	
			textFieldStrings[i] = "TextField" + i; 
			jTextFieldArray[i] = new JTextField(textFieldStrings[i]); 
			jTextFieldArray[i].setBounds(20+x, 20+y, 50, 50);
			jTextFieldArray[i].setEditable(false);
			jTextFieldArray[i].setText("  ");
			getContentPane().add(jTextFieldArray[i]); 
			if(i==3||i==7||i==11)
			{
				y+=60;
				x=0;
			}
			else x+=60;
		} 
					
		setTitle("Game2048");
        setSize(285,340);
        setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
		this.addWindowListener(new WindowAdapter()//for closing the tool when press close button
		{
            public void windowClosing(WindowEvent e)
            {
				System.exit(0);
            }
        });
	}
	
	Game2048(int a[])
	{
		jButton1.addActionListener(this);
		getContentPane().setLayout(null);
		getContentPane().add(jButton1);
		jButton1.setBounds(30,260, 90, 30);
		String st;
		for (int i = 0,x=0,y=0; i < jTextFieldArray.length; i++) 
		{
			textFieldStrings[i] = "TextField" + i; 
			jTextFieldArray[i] = new JTextField(textFieldStrings[i]); 
			jTextFieldArray[i].setBounds(20+x, 20+y, 50, 50);
			jTextFieldArray[i].setEditable(false);
			if(a[i]==0) st="  ";
			else	st=String.valueOf(a[i]);
			st="   "+st+"   ";
			jTextFieldArray[i].setText(st);
			switch(a[i])
			{
				case 2: jTextFieldArray[i].setBackground( Color.GREEN ); break;
				case 4: jTextFieldArray[i].setBackground( Color.RED ); break;
				case 8: jTextFieldArray[i].setBackground( Color.PINK ); break;
				case 16: jTextFieldArray[i].setBackground( Color.ORANGE ); break;
				case 32: jTextFieldArray[i].setBackground( Color.YELLOW ); break;
				case 64: jTextFieldArray[i].setBackground( Color.WHITE ); break;
				case 128: jTextFieldArray[i].setBackground( Color.MAGENTA ); break;
				case 256: jTextFieldArray[i].setBackground( Color.DARK_GRAY ); break;
				case 512: jTextFieldArray[i].setBackground( Color.YELLOW ); break;
				case 1024: jTextFieldArray[i].setBackground( Color.WHITE ); break;
				case 2048: jTextFieldArray[i].setBackground( Color.GREEN ); break;
			}
			getContentPane().add(jTextFieldArray[i]); 
			if(i==3||i==7||i==11)
			{
				y+=60;
				x=0;
			}
			else x+=60;
		} 
			
		setTitle("Game2048");
        setSize(285,340);
        setVisible(true);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventPostProcessor(this);
		this.addWindowListener(new WindowAdapter()//for closing the tool when press close button
		{
            public void windowClosing(WindowEvent e)
            {
				System.exit(0);
            }
        });
		if (openFrame != null)
		{
			openFrame.dispose();
		}
		openFrame = this;
		int t1=0,t2=0,t3=0,t4=0;
		int check=0,c1=1;
		int b[] = new int[16];
		for(int i=0;i<16;i++)
		{
			if(a[i]==0)	{c1=0; break;}
		}
		for(int i=0;i<16&&c1!=0;i++)
		{
			b[i]=0;
			t1=i-1;
			t2=i+1;
			t3=i-4;
			t4=i+4;
			if(t1>=0 && t1<=15)	{if(a[t1]==a[i]) b[i]=1;}
			if(t2>=0 && t2<=15)	{if(a[t2]==a[i]) b[i]=1;}
			if(t3>=0 && t3<=15)	{if(a[t3]==a[i]) b[i]=1;}
			if(t4>=0 && t4<=15)	{if(a[t4]==a[i]) b[i]=1;}
		}
		for(check=0;check<16;check++)
		{
			if(b[check]==1)	break;
		}
		if(check==16 && c1!=0)
		{
			JOptionPane.showMessageDialog(null,"Better luck next time");
			System.exit(0);
		}		
	}
	
	public void actionPerformed(ActionEvent e)//when press enter buuton
	{
		if(e.getSource()== jButton1)
		{
			for(int i=0;i<16;i++)
			{
				a[i] = 0;
			}
			this.dispose();
			Random generator = new Random(); 
			int i = generator.nextInt(16);
			int j = generator.nextInt(16);
			if(i==j)	j++;
			a[i]=2;
			a[j]=2;
			new Game2048(a);
		}
	}
	
	@Override
    public boolean postProcessKeyEvent(KeyEvent ke) 
	{
		int keyCode;
		keyCode = ke.getKeyCode();
		if (ke.getID() == KeyEvent.KEY_PRESSED)
		{
			switch( keyCode ) 
			{ 
				case KeyEvent.VK_UP:
					for(int i=0,j;i<12;)//1st column
					{
						if(a[i]==0)
						{
							for(j=i;j<=12;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j+=4;
							}
						}
					i+=4;
					}
					for(int i=0,j;i<12;)
					{
						if(a[i]==a[i+4])
						{
							a[i]=a[i]+a[i+4];
							if(i==8) a[i+4]=0;
							else
							{
								for(j=i+4;j<12;)
								{
									a[j]=a[j+4];
									j+=4;
								}
								a[j]=0;
							}
						}
						i+=4;
					}
					for(int i=1,j;i<13;)//2nd column
					{
						if(a[i]==0)
						{
							for(j=i;j<=13;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j+=4;
							}
						}
					i+=4;
					}
					for(int i=1,j;i<13;)
					{
						if(a[i]==a[i+4])
						{
							a[i]=a[i]+a[i+4];
							if(i==9) a[i+4]=0;
							else
							{
								for(j=i+4;j<13;)
								{
									a[j]=a[j+4];
									j+=	4;
								}
								a[j]=0;
							}
						}
						i+=4;
					}
					for(int i=2,j;i<14;)//3rd column
					{
						if(a[i]==0)
						{
							for(j=i;j<=14;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j+=4;
							}
						}
					i+=4;
					}
					for(int i=2,j;i<14;)
					{
						if(a[i]==a[i+4])
						{
							a[i]=a[i]+a[i+4];
							if(i==10) a[i+4]=0;
							else
							{
								for(j=i+4;j<14;)
								{
									a[j]=a[j+4];
									j+=4;
								}
								a[j]=0;
							}
						}
						i+=4;
					}
					for(int i=3,j;i<15;)//4th column
					{
						if(a[i]==0)
						{
							for(j=i;j<=15;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j+=4;
							}
						}
					i+=4;
					}
					for(int i=3,j;i<15;)
					{
						if(a[i]==a[i+4])
						{
							a[i]=a[i]+a[i+4];
							
							if(i==11) a[i+4]=0;
							else
							{
								for(j=i+4;j<15;)
								{
									a[j]=a[j+4];
									j+=4;
								}
								a[j]=0;
							}
						}
						i+=4;
					}
					break;
				case KeyEvent.VK_DOWN:
					for(int i=12,j;i>0;)//1st column
					{
						if(a[i]==0)
						{
							for(j=i;j>=0;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j-=4;
							}
						}
					i-=4;
					}
					for(int i=12,j;i>0;)
					{
						if(a[i]==a[i-4])
						{
							a[i]=a[i]+a[i-4];
							
							if(i==4) a[i-4]=0;
							else
							{
								for(j=i-4;j>0;)
								{
									a[j]=a[j-4];
									j-=4;
								}
								a[j]=0;
							}
						}
						i-=4;
					}
					for(int i=13,j;i>1;)//2nd column
					{
						if(a[i]==0)
						{
							for(j=i;j>=1;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j-=4;
							}
						}
					i-=4;
					}
					for(int i=13,j;i>1;)
					{
						if(a[i]==a[i-4])
						{
							a[i]=a[i]+a[i-4];
							
							if(i==5) a[i-4]=0;
							else
							{
								for(j=i-4;j>1;)
								{
									a[j]=a[j-4];
									j-=4;
								}
								a[j]=0;
							}
						}
						i-=4;
					}
					for(int i=14,j;i>2;)//3rd column
					{
						if(a[i]==0)
						{
							for(j=i;j>=2;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j-=4;
							}
						}
					i-=4;
					}
					for(int i=14,j;i>2;)
					{
						if(a[i]==a[i-4])
						{
							a[i]=a[i]+a[i-4];
							
							if(i==6) a[i-4]=0;
							else
							{
								for(j=i-4;j>2;)
								{
									a[j]=a[j-4];
									j-=4;
								}
								a[j]=0;
							}
						}
						i-=4;
					}
					for(int i=15,j;i>3;)//4th column
					{
						if(a[i]==0)
						{
							for(j=i;j>=3;)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							j-=4;
							}
						}
					i-=4;
					}
					for(int i=15,j;i>3;)
					{
						if(a[i]==a[i-4])
						{
							a[i]=a[i]+a[i-4];
							
							if(i==7) a[i-4]=0;
							else
							{
								for(j=i-4;j>3;)
								{
									a[j]=a[j-4];
									j-=4;
								}
								a[j]=0;
							}
						}
						i-=4;
					}
					break;
				case KeyEvent.VK_LEFT:
					for(int i=0,j;i<3;i++)//1st row
					{
						if(a[i]==0)
						{
							for(j=i;j<=3;j++)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=0,j;i<3;i++)
					{
						if(a[i]==a[i+1])
						{
							a[i]=a[i]+a[i+1];
							
							if(i==2) a[i+1]=0;
							else
							{
								for(j=i+1;j<3;j++)
								{
									a[j]=a[j+1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=4,j;i<7;i++)//2nd row
					{
						if(a[i]==0)
						{
							for(j=i;j<=7;j++)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=4,j;i<7;i++)
					{
						if(a[i]==a[i+1])
						{
							a[i]=a[i]+a[i+1];
							
							if(i==6) a[i+1]=0;
							else
							{
								for(j=i+1;j<7;j++)
								{
									a[j]=a[j+1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=8,j;i<11;i++)//3rd row
					{
						if(a[i]==0)
						{
							for(j=i;j<=11;j++)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=8,j;i<11;i++)
					{
						if(a[i]==a[i+1])
						{
							a[i]=a[i]+a[i+1];
							
							if(i==10) a[i+1]=0;
							else
							{
								for(j=i+1;j<11;j++)
								{
									a[j]=a[j+1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=12,j;i<15;i++)//4th row
					{
						if(a[i]==0)
						{
							for(j=i;j<=15;j++)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=12,j;i<15;i++)
					{
						if(a[i]==a[i+1])
						{
							a[i]=a[i]+a[i+1];
							
							if(i==14) a[i+1]=0;
							else
							{
								for(j=i+1;j<15;j++)
								{
									a[j]=a[j+1];
								}
								a[j]=0;
							}
						}
					}
					break;
				case KeyEvent.VK_RIGHT :
					for(int i=3,j;i>0;i--)//1st row
					{
						if(a[i]==0)
						{
							for(j=i;j>=0;j--)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=3,j;i>0;i--)
					{
						if(a[i]==a[i-1])
						{
							a[i]=a[i]+a[i-1];
							
							if(i==1) a[i-1]=0;
							else
							{
								for(j=i-1;j>0;j--)
								{
									a[j]=a[j-1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=7,j;i>4;i--)//2nd row
					{
						if(a[i]==0)
						{
							for(j=i;j>=4;j--)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=7,j;i>4;i--)
					{
						if(a[i]==a[i-1])
						{
							a[i]=a[i]+a[i-1];
							
							if(i==5) a[i-1]=0;
							else
							{
								for(j=i-1;j>4;j--)
								{
									a[j]=a[j-1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=11,j;i>8;i--)//3rd row
					{
						if(a[i]==0)
						{
							for(j=i;j>=8;j--)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=11,j;i>8;i--)
					{
						if(a[i]==a[i-1])
						{
							a[i]=a[i]+a[i-1];
							
							if(i==9) a[i-1]=0;
							else
							{
								for(j=i-1;j>8;j--)
								{
									a[j]=a[j-1];
								}
								a[j]=0;
							}
						}
					}
					for(int i=15,j;i>12;i--)//4th row
					{
						if(a[i]==0)
						{
							for(j=i;j>=12;j--)
							{
								if(a[j]!=0)
								{
									a[i]=a[j];
									a[j]=0;
									break;
								}
							}
						}
					}
					for(int i=15,j;i>12;i--)
					{
						if(a[i]==a[i-1])
						{
							a[i]=a[i]+a[i-1];
							
							if(i==13) a[i-1]=0;
							else
							{
								for(j=i-1;j>12;j--)
								{
									a[j]=a[j-1];
								}
								a[j]=0;
							}
						}
					}
					break;
			}
			Random generator = new Random(); 
			int j;
			for(j=0;j<16;j++)
			{
				int i = generator.nextInt(16);
				if(a[i]==0)	
				{	a[i]=2; 
					break;
				}
			}
			if(j==16)
			{
				for(j=0;j<32;j++)
				{
					int i = generator.nextInt(16);
					if(a[i]==0)	
					{	a[i]=2; 
						break;
					}
				}
			}
			for(int i=0;i<16;i++)
			{
				if(a[i]==2048)	
				{	
					JOptionPane.showMessageDialog(null,"Congratulations");
					System.exit(0);
				}
			}
			new Game2048(a);
			return true;
		}
        // Let keyboard focus manager handle the event further.
        return false;
    }
}