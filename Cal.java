import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
import java.io.FileWriter; 
import java.io.IOException; 

/* 
<applet code="Cal.class" CodeBase="" width=400 height=450>
</applet>
*/
 
public class Cal extends Applet
implements ActionListener
{	
	String url= "jdbc:Mysql@127.0.0.1:3306/history";		//database details
	String user="student";
	String password="12345";

	StringBuffer str1= new StringBuffer(100);
	StringBuffer str2=new StringBuffer(100);
	StringBuffer str3=new StringBuffer(1000);
	String msg=" ";
	double v1,v2,result;
	double fact1;
	int i=0;
	JTextField t1;
	JTextField t2;
	JTextArea t3; 
	Button b[]=new Button[10];
	Button add,dot,exp,sub,mul,div,clear,mod,EQ,sin,cos,tan,cot,sec,cosec,shift,fact,hist;
	char OP;
	double radians,sinresult;//,result;
	public void init()
	{
		Color k=new Color(0,255,255);
		Color j=new Color(255,75,75);
		setBackground(k);
		t1=new JTextField(50);
		t2=new JTextField(50);
		t3=new JTextArea();
		Font myFont = new Font("Ariel", Font.BOLD ,20);
		Font myFont1 = new Font("Ariel", Font.BOLD ,15);
		Font myFont2= new Font("Ariel", Font.BOLD ,13);
		t1.setBounds(10,100,100,20);
		t2.setBounds(10,130,100,20);
		t2.setHorizontalAlignment(JTextField.CENTER);
		t1.setHorizontalAlignment(JTextField.CENTER);
		t1.setBackground(k);
		t2.setBackground(k);
		t3.setBackground(k);
		
		add(t1);
		add(t2);
		t1.setFont(myFont);
		t2.setFont(myFont);
		t3.setFont(myFont2);
		t1.addActionListener(this);
		GridLayout gl=new GridLayout(5,5);
		setLayout(gl);
		for(int i=0;i<10;i++)
		{
			b[i]=new Button(""+i);
		}
		add=new Button("+");
		sub=new Button("-");
		mul=new Button("*");
		div=new Button("/");
		mod=new Button("%");
		exp=new Button("^");
		clear=new Button("Clear");
		EQ=new Button("=");
		sin=new Button("sin");
		cos=new Button("cos");
		tan=new Button("tan");
		cot=new Button("cot");
		sec=new Button("sec");
		cosec=new Button("cosec");
		dot=new Button(".");
		shift=new Button("Ans");
		hist=new Button("History");
		//fact=new Button("!");
		for(int i=0;i<10;i++)
		{
			add(b[i]);
			b[i].setBackground(j);
			b[i].setFont(myFont);
			//b[i].setForeground(Color.white);
		}
		add(add);	add.setBackground(Color.green);add.setFont(myFont);
		add(sub);	sub.setBackground(Color.green);sub.setFont(myFont);
		add(mul);	mul.setBackground(Color.green);mul.setFont(myFont);
		add(div);	div.setBackground(Color.green);div.setFont(myFont);
		add(mod);	mod.setBackground(Color.green);mod.setFont(myFont);
		add(exp);	exp.setBackground(Color.green);exp.setFont(myFont);
		add(sin);	sin.setBackground(Color.yellow);sin.setFont(myFont1);
		add(cos);	cos.setBackground(Color.yellow);cos.setFont(myFont1);
		add(tan);	tan.setBackground(Color.yellow);tan.setFont(myFont1);
		add(cot);	cot.setBackground(Color.yellow);cot.setFont(myFont1);
		add(sec);	sec.setBackground(Color.yellow);sec.setFont(myFont1);
		add(cosec);	cosec.setBackground(Color.yellow);cosec.setFont(myFont1);
		//add(fact);  fact.setBackground(Color.green);fact.setFont(myFont);
		add(dot); dot.setBackground(Color.blue);dot.setForeground(Color.white);dot.setFont(myFont);
		add(clear);	clear.setBackground(Color.blue);clear.setForeground(Color.white);clear.setFont(myFont1);
		add(shift); shift.setBackground(Color.blue);shift.setForeground(Color.white);shift.setFont(myFont1);
		add(EQ);	EQ.setBackground(Color.black);EQ.setForeground(Color.white);EQ.setFont(myFont);
		add(hist);	hist.setBackground(Color.black);hist.setForeground(Color.white);hist.setFont(myFont1);
		add(t3); 

		for(int i=0;i<10;i++)
		{
		b[i].addActionListener(this);
		}
		add.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		div.addActionListener(this);
		mod.addActionListener(this);
		exp.addActionListener(this);
		sin.addActionListener(this);
		cos.addActionListener(this);
		tan.addActionListener(this);
		cot.addActionListener(this);
		sec.addActionListener(this);
		cosec.addActionListener(this);
		clear.addActionListener(this);
		EQ.addActionListener(this);
		shift.addActionListener(this);
		dot.addActionListener(this);
		hist.addActionListener(this);
		//fact.addActionListener(this);

	}
 
	public void actionPerformed(ActionEvent ae) 
	{	
		
		try
		{
		Connection myConn=DriverManager.getConnection(url,user,password);  //putting values into database
		Statement myStat=myConn.createStatement();
		String sql="insert into calc_result"+"(result)"+"values('4+4=8')";
		myStat.executeUpdate(sql);
		System.out.println("done");
		}
		catch(Exception e)
		{}
		
		String str=ae.getActionCommand();
		str2.append(str);
		char ch=str.charAt(0);
		if(Character.isDigit(ch) || str.equals("."))
		{
			str1.append(str);
			ch=str1.charAt(i);
			t1.setText(str1.toString());
			i++;
		}
		else
		{
			if(str.equals("+"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='+';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("+");
				
			}
			else if(str.equals("-"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='-';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("-");
			}
			else if(str.equals("*"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='*';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("*");
			}
			else if(str.equals("/"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='/';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("/");
			}
			else if(str.equals("%"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='%';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("%");
			}
			else if(str.equals("^"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='^';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("^");
			}
			/*else if(str.equals("!"))
			{
				v1=Double.parseDouble(t1.getText());
				OP='^';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("!");
			}*/

			else if(str.equals("sin"))
			{					
				OP='s';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("sin");
			}
			else if(str.equals("cos"))
			{	
				OP='c';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("cos");
			}
			else if(str.equals("tan"))
			{	
				OP='t';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("tan");
			}
			else if(str.equals("cot"))
			{				
				OP='o';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("cot");
			}
			else if(str.equals("sec"))
			{				
				OP='e';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("sec");
			}
			else if(str.equals("cosec"))
			{				
				OP='m';
				str1.delete(0, str1.length());
				i=0;
				t1.setText("cosec");
			}
			
			else if(str.equals("Ans"))
			{	
				double in=Double.parseDouble(t2.getText());
				t1.setText(Double.toString(in));
			}
			
			else if(str.equals("History"))
			{
				str2.delete(0, str2.length());
				str1.delete(0, str1.length());
				i=0;
				t3.setText(str3.toString());
			}
		}

		if(str.equals("="))
		{	
			
			v2=Double.parseDouble(t1.getText());
			
			if(OP=='+')
				{
				result=v1+v2;
				t2.setText(""+result);
				}

			else if(OP=='-')
				{
				result=v1-v2;
				t2.setText(""+result);
				}

			else if(OP=='*')
				{
				result=v1*v2;
				t2.setText(""+result);
				}

			else if(OP=='/')
				{
				sinresult=v1/v2;
				t2.setText(""+sinresult);
				}

			else if(OP=='%')
				{
				result=v1%v2;
				t2.setText(""+result);
				}

			else if(OP=='^')
				{
				sinresult=Math.pow(v1,v2);
				t2.setText(""+sinresult);
				}

			/*
			else if(OP=='!')
				{	
					for(int i=1;i<=v1;i++){    
      					fact1=fact1*i;    
 				}    
				sinresult=fact1;
				t2.setText(""+sinresult);}
			*/
				
			else if(OP=='c')
				{
				radians=Math.toRadians(v2);
				sinresult=Math.cos(radians);
				t2.setText(""+sinresult);
				}

			else if(OP=='t')
				{
				radians=Math.toRadians(v2);
				sinresult=Math.tan(radians);
				t2.setText(""+sinresult);
				}

			else if(OP=='o')
				{
				radians=Math.toRadians(v2);
				sinresult=1/Math.tan(radians);
				t2.setText(""+sinresult);
				}

			else if(OP=='e')
				{
				radians=Math.toRadians(v2);
				sinresult=1/Math.cos(radians);
				t2.setText(""+sinresult);
				}

			else if(OP=='m')
				{
				radians=Math.toRadians(v2);
				sinresult=1/Math.sin(radians);
				t2.setText(""+sinresult);
				}

			else if(OP=='s')
				{
				radians=Math.toRadians(v2);
				sinresult=Math.sin(radians);
				t2.setText(""+sinresult);
				}

		t1.setText(str2.toString());
		str3.append(t1.getText()+""+t2.getText());
		str3.append("\r\n");
		//new Write(str1,str2,t1);	                  //writing to a file
		str1.delete(0, str1.length());
		str2.delete(0, str2.length());
		i=0;
	
		}	
		if(str.equals("Clear"))
		{
			//if(str1.toString()=="" && str2.toString()=="")
			{
				t3.setText("");
			}
			t1.setText("");
			t2.setText("");
			str1.delete(0, str1.length());
			str2.delete(0, str2.length());
			i=0;
		}
	}
}

//writing data to a file
/*
class Write
{
	Write(StringBuffer str1,StringBuffer str2,JTextField t1)
	{
		try
		{
			File file = new File("E:\\history.txt\\");
			FileWriter fw=new FileWriter(file,true);
			fw.write("hii");
		for (int i = 0; i < str1.length(); i++) 
            fw.write(str1.charAt(i)); 
		for (int i = 0; i < str2.length(); i++) 
            fw.write(str2.charAt(i)); 
		
		fw.close();
		}
		catch(Exception e){
			//t1.setText(""+e);
		};
	}
}

*/