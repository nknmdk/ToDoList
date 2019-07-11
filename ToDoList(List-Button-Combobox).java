package gui;

//package com.techscore.ui.chapter9.exercise1;
//http://java-code-complete.blogspot.com/2011/10/retrieve-data-from-database-and-display_18.html

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
 
public class ToDoList extends JFrame implements ActionListener{
	
	//�R���|�[�l���g������
	private DefaultListModel model;
	private JComboBox combox;
	private JButton buttons[];
	private JList dbList;
	private JPanel p1,p2,p3;
	private String bLabel[] = {"Add","Done list","Delete List","No Goal List","Logout"};
		
	//DB�p
	Connection con;
	Statement st;
	ResultSet rs;
	String db;
	
	
	public ToDoList() {
		
		super("ToDoList");
		setSize(400,260);
		
		//�E�B���h�E�I���v���p�e�B
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		model = new DefaultListModel();
		buttons = new JButton[5];
		dbList = new JList(model);
		combox=new JComboBox();
		combox.setPreferredSize(new Dimension(140, 20));
		
		
		//���X�g�\�����@
		dbList.setVisibleRowCount(5);
		//���X�g���ڃZ���̃T�C�Y
	    dbList.setFixedCellHeight(27);
	    dbList.setFixedCellWidth(130);
	    //�s�I�����
	    dbList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    
	    //���X�g�p�l���̍\�z
	    p1 = new JPanel();
	    p1.setBorder(BorderFactory.createTitledBorder("ToDoList"));
	    // p1.setLayout(new GridLayout(2,1));
	    
	    p1.add(new JScrollPane(dbList)); //���X�g�p�l����JList�ǉ�
	    
	    getContentPane().add(p1, BorderLayout.CENTER);
	    
	    JButton Goal = new JButton("Goal");
		Goal.addActionListener(this);
		Goal.setActionCommand("Goal");
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(this);
		Delete.setActionCommand("Delete");
		
	    combox.addItem("�w�Z");
	    combox.addItem("�d��");
		
		p1.add(Goal);
		p1.add(Delete);
		p1.add(combox);
	 
	    
	    //�{�^���p�l���̍\�z
	    p2 = new JPanel();
	    p2.setLayout(new GridLayout(5,1));
	    p2.setBorder(BorderFactory.createTitledBorder(" "));
	    
	    /*
	    p3=new JPanel();
	    p3.setLayout(new GridLayout(3,3));
	    p3.setBorder(BorderFactory.createTitledBorder(" "));
	    combox.addItem("�w�Z");
	    p3.add(combox);	    
	     */

	    
	    //���[�v��JBottons�쐬�A�{�^���p�l���ɒǉ�
	    for(int count=0; count<buttons.length; count++) {
	    	buttons[count] = new JButton(bLabel[count]);
	    	p2.add(buttons[count]);
	    }
	   
	    //�R���|�[�l���g�ǉ����邽�߂̃R���e�i
	    Container pane = getContentPane();
	    setContentPane(pane);
	    
	    //�R���e�i���C�A�E�g
	    GridLayout grid = new GridLayout(1,2); //����(�s�A��A�����Ԋu�A�����Ԋu)
	    BorderLayout bd=new BorderLayout();
	    pane.setLayout(grid);
	    // GridBagLayout gb=new GridBagLayout();
	    
	    //DB�ւ̐ڑ�
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        con = DriverManager.getConnection("jdbc:mysql://localhost/TDL?serverTimezone=JST","root","root");
	        st = con.createStatement();  
	        
	        String sql = "SELECT Task FROM Tasks WHERE InputDay=current_date";
	        ResultSet rs = st.executeQuery (sql);
	        
	        while(rs.next()) {
	        	model.addElement(rs.getString("Task"));
	        }
	        
	       } catch (Exception e) {
	    	   e.printStackTrace();
	        JOptionPane.showMessageDialog(null,"�f�[�^�x�[�X�ɐڑ��ł��܂���","�ڑ��G���[",JOptionPane.WARNING_MESSAGE);
	        System.exit(0);
	    	  // System.out.println(e);
	       }
	    
	    //�uAdd�v�ɃC�x���g���X�i�[������
	    buttons[0].addActionListener(
	    		new ActionListener() {
	    		   
	    			//�N���b�N�ŃC�x���g����
	    		
	    			public void actionPerformed(ActionEvent event) {
	    		    /*
	    			try {
	    		     model.clear();
	    		     rs=st.executeQuery("select * from person");
	    		     while (rs.next()) {
	    		         model.addElement(rs.getString("id"));
	    		     }
	    		     
	    		    } catch (Exception e) {
	    		     System.out.println("Retrieving Data Fail");
	    		    }
	    		    */
	    		   }
	    		  }
	    		  );
	
	//�uDelete�v�ɃC�x���g���X�i�[������
	buttons[2].addActionListener(
			new ActionListener() {
			   
			  //Handle JButton event if it is clicked
			  public void actionPerformed(ActionEvent event) {
			    /*
				try {
			    model.clear();
			    rs=st.executeQuery("select * from person");
			    while (rs.next()) {
			         model.addElement(rs.getString("middleName"));
			     }
			     
			    } catch (Exception e) {
			     System.out.println("Retrieving Data Fail");
			    }
			    */
			   }
			  }
			  );
	//�uNo Goal List�v�ɃC�x���g���X�i�[����
	buttons[3].addActionListener(
			  new ActionListener() {
			   
			   //Handle JButton event if it is clicked
			   public void actionPerformed(ActionEvent event) {
			     /*
			     	try{
			     model.clear();
			     rs=st.executeQuery("select * from person");
			     while (rs.next()) {
			         model.addElement(rs.getString("familyName"));
			     }
			     
			    } catch (Exception e) {
			     System.out.println("Retrieving Data Fail");
			    }
			    */
			   }
			  }
			  );
	
	//�uLogout�v�ɃC�x���g���X�i�[����
	 buttons[4].addActionListener(
			  new ActionListener() {
			   
			   //Handle JButton event if it is clicked
			   public void actionPerformed(ActionEvent event) {
			    /*
				 try {
			     model.clear();
			     rs=st.executeQuery("select * from person");
			     while (rs.next()) {
			         model.addElement(rs.getString("age"));
			     }
			     
			    } catch (Exception e) {
			     System.out.println("Retrieving Data Fail");
			    }
			     */
			   }
			  }
			  );
	 
	 //�R���e�i�ɃR���|�[�l���g��ǉ�
	 pane.add(p1);
	 pane.add(p2);
	 
	 setVisible(true);
     setResizable(false);
    }
	
	public static void main (String[] args) {
	     ToDoList rdjl = new ToDoList();
	 }

}
//http://java-code-complete.blogspot.com/2011/10/retrieve-data-from-database-and-display_18.html
//https://okwave.jp/qa/q7542809.html