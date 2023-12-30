import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

public class dbProject extends JFrame {
	ImageIcon imageicon = new ImageIcon("images/photo.jpg");  //이미지 생성
	JLabel label = new JLabel(imageicon); //이미지가 들어가는 JLabel 생성
	private JLabel state;
	
	String colName[] = { "CName", "COffice", "CPhone", "Id" }; //기본 행 이름
	DefaultTableModel model = new DefaultTableModel(colName, 0); //기본테이블 설정
	JTable table = new JTable(model);//기본 테이블 생성
	JScrollPane scrollPane = new JScrollPane(table); //테이블을 JScrollPane에 설정
	String row[] = new String[12];
	public String str_state = "COLLEGE";
	
	private Connection conn = null;
	private String path = "oracle.jdbc.driver.OracleDriver"; //DriverManager path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost 설정
	private String id = "sugang"; //id 설정
	private String pwd = "1234"; //pw 설정
	
	public dbProject() {
		setTitle("DBProject_이원영"); //창 이름 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창에서 닫기버튼 누를 시 종료 설정
		setLayout(new BorderLayout()); //BorderLayout으로 기본 설정
		JPanel Btn_panel = new JPanel(); //버튼패널 생성
		JPanel Btn2_panel = new JPanel();
		JPanel panel = new JPanel();  //패널 생성
		Btn_panel.setLayout(new FlowLayout()); //버튼패널 내 FlowLayout설정
		 panel.setLayout(new FlowLayout()); //패널 내 FlowLayout설정
		 panel.add(label); //패널에 label추가
		
		 state = new JLabel(); //state를 표시하는 JLabel 설정
		 state.setText("Oracle DB 연동테스트"); //JLabel 내용 설정
		 
		JButton btn_college = new JButton("COLLEGE"); //회원 테이블을 불러오는 버튼
		JButton btn_instructor = new JButton ("INSTRUCTOR"); //제조업체 테이블을 불러오는 버튼
		JButton btn_dept = new JButton ("DEPT"); //상품 테이블을 불러오는 버튼
		JButton btn_student = new JButton ("STUDENT");//게시글 테이블을 불러오는 버튼
		JButton btn_takes = new JButton ("TAKES");//게시글 테이블을 불러오는 버튼
		JButton btn_course = new JButton ("COURSE");//게시글 테이블을 불러오는 버튼
		JButton btn_section = new JButton ("SECTION");//게시글 테이블을 불러오는 버튼
		
		JButton btn_Insert = new JButton ("Insert");//게시글 테이블을 불러오는 버튼
		JButton btn_Delete = new JButton ("Delete");//게시글 테이블을 불러오는 버튼
		JButton btn_Update = new JButton ("Update");//게시글 테이블을 불러오는 버튼
		
		Btn_panel.add(btn_college); //회원 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_instructor); //제조업체 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_dept); //상품 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_student); //게시글 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_takes); //게시글 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_course); //게시글 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_section); //게시글 테이블 버튼을 버튼 패널에 추가

		Btn2_panel.add(btn_Insert);
		Btn2_panel.add(btn_Delete);
		Btn2_panel.add(btn_Update);
		
		 add(state, BorderLayout.NORTH); //상태표시라벨을 북쪽에 표시
		 add(panel, BorderLayout.WEST); //이미지 패널을 서쪽에 표시
		 add(Btn_panel, BorderLayout.SOUTH); //버튼패널을 남쪽에 표시
 		 add(scrollPane, BorderLayout.CENTER); //테이블을 가운데에 표시
 		 add(Btn2_panel, BorderLayout.NORTH);

			try {
				Class.forName(path); //DriverManager 클래스에등록
				conn = DriverManager.getConnection(url, id, pwd); //DB 접속, 계정과 pwd 확인 수정
				state.setText("성공적연결"); //연결 성공 시 성공적연결 표시
			} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			state.setText("DB 연결실패" + e.toString());
			} catch (SQLException e) { e.printStackTrace();
			state.setText("DB 연결실패" + e.toString());
			}
		 
			
			//회원 테이블 버튼
		 btn_college.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "COLLEGE";
				//필요한 열 추가
				model.addColumn("CName");
				model.addColumn("COffice");
				model.addColumn("CPhone");
				model.addColumn("Id");

				//수정된 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //회원테이블 함수 실행
				College();
			}
		 });
		 
		 //제조업체 테이블 버튼
		 btn_instructor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset(); //행, 열 초기화 
				str_state = "INSTRUCTOR";
				//필요한 열 추가
				model.addColumn("Id");
				model.addColumn("Ranke");
				model.addColumn("IName");
				model.addColumn("IOffice");
				model.addColumn("IPhone");
				model.addColumn("DName");
				model.addColumn("DCode");
				
				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //제조업체테이블 함수 실행
				Instructor();
			}
		 });
		 
		 //상품 테이블 버튼
		 btn_dept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "DEPT";
				//필요한 열 추가
				model.addColumn("DName");
				model.addColumn("DCode");
				model.addColumn("DOffice");
				model.addColumn("DPhone");
				model.addColumn("CName");
				model.addColumn("Id");
				model.addColumn("CStartDate");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //상품테이블 함수 실행
				Dept();
			}
		 });
		 
		 btn_student.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "STUDENT";
				//필요한 열 추가
				model.addColumn("Sid");
				model.addColumn("DOB");
				model.addColumn("FName");
				model.addColumn("MName");
				model.addColumn("LName");
				model.addColumn("Addr");
				model.addColumn("Phone");
				model.addColumn("Major");
				model.addColumn("DName");
				model.addColumn("DCode");


				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //게시글테이블 함수 실행
				Student();
			}
		 });
		 
		 btn_takes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "TAKES";
				//필요한 열 추가
				model.addColumn("Sid");
				model.addColumn("SecId");
				model.addColumn("Grade");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //게시글테이블 함수 실행
				Takes();
			}
		 });
		 
		 btn_course.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "COURSE";
				//필요한 열 추가
				model.addColumn("CCode");
				model.addColumn("CoName");
				model.addColumn("Credits");
				model.addColumn("Level");
				model.addColumn("CDesc");
				model.addColumn("DName");
				model.addColumn("DCode");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //게시글테이블 함수 실행
				Course();
			}
		 });
		 
		 btn_section.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				str_state = "SECTION";
				//필요한 열 추가
				model.addColumn("SecId");
				model.addColumn("SecNo");
				model.addColumn("Sem");
				model.addColumn("Year");
				model.addColumn("RoomNo");
				model.addColumn("Bldg");
				model.addColumn("DaysTime");
				model.addColumn("Id");
				model.addColumn("CCode");
				model.addColumn("CoName");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //게시글테이블 함수 실행
				Section();
			}
		 });
		 
	        btn_Insert.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                // When btn_Insert is pressed, open the InsertWindow
	                new InsertWindow(str_state);
	            }
	        });
	        
	        btn_Delete.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new DeleteWindow(str_state);
	        	}
	        });
	        btn_Update.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new UpdateWindow(str_state);
	        	}
	        });
		 
		 setSize(1300, 700); //창 크기 1300 X 700 지정 
		 setVisible(true); //창 보이기 설정
	}
	
	//행, 열 초기화 함수
	public void reset() {
		while (model.getRowCount() > 0) {
			model.removeRow(0); //행 초기화
        }
		while (model.getColumnCount() > 0)
			model.setColumnCount(0); //열 초기화
	}
	
	//회원 테이블 함수
	public void College() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from COLLEGE"); //회원테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 5; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}

	}
	
	//제조업체 테이블 함수
	public void Instructor() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from INSTRUCTOR"); //제조업체테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 8; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}
	}
	
	//상품 테이블 함수
	public void Dept() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from DEPT"); //상품테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 8; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}

	}
	
	
	//게시글 테이블 함수
	public void Student() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from STUDENT"); //게시글테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 11; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}
	}
	
	public void Takes() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from TAKES"); //게시글테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 4; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}
	}
	
	public void Course() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from COURSE"); //게시글테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 8; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}
	}
	
	public void Section() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from SECTION"); //게시글테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 11; i++) { 
					row[i - 1] = rset.getString(i);
				}
				System.out.println();
				// JTable에출력 
				model.addRow(row);
		}  }catch (SQLException e1) {
			e1.printStackTrace();
			state.setText("An Error Occured While Reading Database.");
				}
	}
	public static void main(String[] args) {
		new dbProject();

	}

}

class InsertWindow extends JFrame {
	
	private Connection conn = null;
	private String path = "oracle.jdbc.driver.OracleDriver"; //DriverManager path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost 설정
	private String id = "sugang"; //id 설정
	private String pwd = "1234"; //pw 설정
	
    // Code for your new window (insert window) goes here
    public InsertWindow(String state) {
    	
		try {
			Class.forName(path); //DriverManager 클래스에등록
			conn = DriverManager.getConnection(url, id, pwd); //DB 접속, 계정과 pwd 확인 수정
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	
        setTitle("Insert Window");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextField[] jTextField = new JTextField[10];
        
        if(state.equals("COLLEGE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("COffice: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("CPhone: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            
            add(collegePanel, BorderLayout.CENTER);
           
        }
        
        else if(state.equals("INSTRUCTOR")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Id: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("Rank: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("IName: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("IOffice: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("IPhone: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("DEPT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("DName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("DOffice: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("DPhone: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("CName: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("CStartDate: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("STUDENT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("DOB: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("FName: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("MName: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("LName: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Addr: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("Phone: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            collegePanel.add(new JLabel("Major: "));
            jTextField[7] = new JTextField(10);
            collegePanel.add(jTextField[7]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[8] = new JTextField(10);
            collegePanel.add(jTextField[8]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[9] = new JTextField(10);
            collegePanel.add(jTextField[9]);
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("TAKES")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("SecId: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Grade: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("COURSE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CCode: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("CoName: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Credits: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Level: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("CDesc: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("SECTION")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("SecId: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("SecNo: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Sem: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Year: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("RoomNo: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Bldg: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DaysTime: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[7] = new JTextField(10);
            collegePanel.add(jTextField[7]);
            collegePanel.add(new JLabel("CCode: "));
            jTextField[8] = new JTextField(10);
            collegePanel.add(jTextField[8]);
            collegePanel.add(new JLabel("CoName: "));
            jTextField[9] = new JTextField(10);
            collegePanel.add(jTextField[9]);
            
            add(collegePanel, BorderLayout.CENTER);
        }


        
        
        //==============================================================================
        JButton addButton = new JButton("추가");
        JButton cancelButton = new JButton("취소");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (state.equals("COLLEGE")) {
                    String query = "INSERT INTO COLLEGE (CName, COffice, CPhone, Id) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                
                else if (state.equals("INSTRUCTOR")) {
                    String query = "INSERT INTO INSTRUCTOR (Id, Rank, IName, IOffice, IPhone, DName, DCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());
                        pstmt.setString(5, jTextField[4].getText());
                        pstmt.setString(6, jTextField[5].getText());
                        pstmt.setString(7, jTextField[6].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("DEPT")) {
                    String query = "INSERT INTO DEPT (DName, DCode, DOffice, DPhone, CName, Id, CStartDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());
                        pstmt.setString(5, jTextField[4].getText());
                        pstmt.setString(6, jTextField[5].getText());
                        pstmt.setString(7, jTextField[6].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("STUDENT")) {
                    String query = "INSERT INTO STUDENT (Sid, DOB, FName, MName, LName, Addr, Phone, Major, DName, DCode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());
                        pstmt.setString(5, jTextField[4].getText());
                        pstmt.setString(6, jTextField[5].getText());
                        pstmt.setString(7, jTextField[6].getText());
                        pstmt.setString(8, jTextField[6].getText());
                        pstmt.setString(9, jTextField[6].getText());
                        pstmt.setString(10, jTextField[6].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("TAKES")) {
                    String query = "INSERT INTO STUDENT (Sid, SecId, Grade) VALUES (?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("COURSE")) {
                    String query = "INSERT INTO STUDENT (CCode, CoName, Credits, Level_s, Cdesc, DName, DCode) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());
                        pstmt.setString(5, jTextField[4].getText());
                        pstmt.setString(6, jTextField[5].getText());
                        pstmt.setString(7, jTextField[6].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("SECTION")) {
                    String query = "INSERT INTO STUDENT (SecId, SecNo, Sem, Year, RoomNo, Bldg, DaysTime, Id, CCode, CoName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());
                        pstmt.setString(2, jTextField[1].getText());
                        pstmt.setString(3, jTextField[2].getText());
                        pstmt.setString(4, jTextField[3].getText());
                        pstmt.setString(5, jTextField[4].getText());
                        pstmt.setString(6, jTextField[5].getText());
                        pstmt.setString(7, jTextField[6].getText());
                        pstmt.setString(8, jTextField[7].getText());
                        pstmt.setString(9, jTextField[8].getText());
                        pstmt.setString(10, jTextField[8].getText());
                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (conn != null) {
                        conn.close(); // 데이터베이스 연결 닫기
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose(); // "취소" 버튼을 눌렀을 때 창 닫기
            }
        });


        add(buttonPanel, BorderLayout.SOUTH);

        pack(); // 적절한 크기로 창 조정
        setVisible(true);
    }
}

class DeleteWindow extends JFrame {
	
	private Connection conn = null;
	private String path = "oracle.jdbc.driver.OracleDriver"; //DriverManager path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost 설정
	private String id = "sugang"; //id 설정
	private String pwd = "1234"; //pw 설정
	
    // Code for your new window (insert window) goes here
    public DeleteWindow(String state) {
    	
		try {
			Class.forName(path); //DriverManager 클래스에등록
			conn = DriverManager.getConnection(url, id, pwd); //DB 접속, 계정과 pwd 확인 수정
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	
        setTitle("Delete Window");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextField[] jTextField = new JTextField[1];
        
        if(state.equals("COLLEGE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("INSTRUCTOR")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Id: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("DEPT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("DName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("STUDENT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("TAKES")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("COURSE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CCode: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("SECTION")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("SecId: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);

            add(collegePanel, BorderLayout.CENTER);
        }
       

        
        
        //==============================================================================
        JButton addButton = new JButton("삭제");
        JButton cancelButton = new JButton("취소");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(state.equals("COLLEGE")) {
        			String query = "DELETE FROM COLLEGE WHERE CName = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("INSTRUCTOR")) {
        			String query = "DELETE FROM INSTRUCTOR WHERE Id = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("DEPT")) {
        			String query = "DELETE FROM DEPT WHERE DName = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("STUDENT")) {
        			String query = "DELETE FROM STUDENT WHERE Sid = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("TAKES")) {
        			String query = "DELETE FROM TAKES WHERE Sid = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("COURSE")) {
        			String query = "DELETE FROM INSTRUCTOR WHERE CCode = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
        		else if(state.equals("SECTION")) {
        			String query = "DELETE FROM INSTRUCTOR WHERE SecId = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[0].getText());
        				
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 삭제되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 삽입 중 오류 발생: " + ex.getMessage());
        			}
        		}
        	}
        });
                


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (conn != null) {
                        conn.close(); // 데이터베이스 연결 닫기
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose(); // "취소" 버튼을 눌렀을 때 창 닫기
            }
        });


        add(buttonPanel, BorderLayout.SOUTH);

        pack(); // 적절한 크기로 창 조정
        setVisible(true);
    }
}

class UpdateWindow extends JFrame {
	
	private Connection conn = null;
	private String path = "oracle.jdbc.driver.OracleDriver"; //DriverManager path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost 설정
	private String id = "sugang"; //id 설정
	private String pwd = "1234"; //pw 설정
	
    // Code for your new window (insert window) goes here
    public UpdateWindow(String state) {
    	
		try {
			Class.forName(path); //DriverManager 클래스에등록
			conn = DriverManager.getConnection(url, id, pwd); //DB 접속, 계정과 pwd 확인 수정
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
    	
        setTitle("Update Window");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JTextField[] jTextField = new JTextField[10];
        
        if(state.equals("COLLEGE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("COffice: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("CPhone: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM COLLEGE WHERE CName = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            add(collegePanel, BorderLayout.CENTER);
           
        }
        
        else if(state.equals("INSTRUCTOR")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Id: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("Rank: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("IName: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("IOffice: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("IPhone: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM INSTRUCTOR WHERE Id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("DEPT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("DName: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("DOffice: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("DPhone: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("CName: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("CStartDate: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM DEPT WHERE DName = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("STUDENT")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("DOB: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("FName: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("MName: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("LName: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Addr: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("Phone: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            collegePanel.add(new JLabel("Major: "));
            jTextField[7] = new JTextField(10);
            collegePanel.add(jTextField[7]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[8] = new JTextField(10);
            collegePanel.add(jTextField[8]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[9] = new JTextField(10);
            collegePanel.add(jTextField[9]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM STUDENT WHERE Sid = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("TAKES")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("Sid: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("SecId: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Grade: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM TAKES WHERE Sid = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("COURSE")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("CCode: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("CoName: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Credits: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Level: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("CDesc: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("DName: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DCode: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM COURSE WHERE CCode = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
        else if(state.equals("SECTION")) {
            JPanel collegePanel = new JPanel(new GridLayout(0, 2)); // 0은 행을 자동으로 조절하도록 합니다.
            collegePanel.add(new JLabel("SecId: "));
            jTextField[0] = new JTextField(10);
            collegePanel.add(jTextField[0]);
            collegePanel.add(new JLabel("SecNo: "));
            jTextField[1] = new JTextField(10);
            collegePanel.add(jTextField[1]);
            collegePanel.add(new JLabel("Sem: "));
            jTextField[2] = new JTextField(10);
            collegePanel.add(jTextField[2]);
            collegePanel.add(new JLabel("Year: "));
            jTextField[3] = new JTextField(10);
            collegePanel.add(jTextField[3]);
            collegePanel.add(new JLabel("RoomNo: "));
            jTextField[4] = new JTextField(10);
            collegePanel.add(jTextField[4]);
            collegePanel.add(new JLabel("Bldg: "));
            jTextField[5] = new JTextField(10);
            collegePanel.add(jTextField[5]);
            collegePanel.add(new JLabel("DaysTime: "));
            jTextField[6] = new JTextField(10);
            collegePanel.add(jTextField[6]);
            collegePanel.add(new JLabel("Id: "));
            jTextField[7] = new JTextField(10);
            collegePanel.add(jTextField[7]);
            collegePanel.add(new JLabel("CCode: "));
            jTextField[8] = new JTextField(10);
            collegePanel.add(jTextField[8]);
            collegePanel.add(new JLabel("CoName: "));
            jTextField[9] = new JTextField(10);
            collegePanel.add(jTextField[9]);
            
            JButton primaryKeyCheckButton = new JButton("기본키 확인");
            collegePanel.add(primaryKeyCheckButton);
            
            primaryKeyCheckButton.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String query = "SELECT * FROM SECTION WHERE SecId = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[0].getText());

                        if (pstmt.executeQuery().next()) {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재합니다!");
                        } else {
                            JOptionPane.showMessageDialog(null, "기본 키가 존재하지 않습니다.");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "기본 키 확인 중 오류 발생: " + ex.getMessage());
                    }            	}
            });
            
            add(collegePanel, BorderLayout.CENTER);
        }
        
      
        
        
        //==============================================================================
        JButton addButton = new JButton("수정");
        JButton cancelButton = new JButton("취소");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(state.equals("COLLEGE")) {
        			String query = "UPDATE COLLEGE SET COffice = ?, CPhone = ?, Id = ? WHERE CName = ?";
        			try (PreparedStatement pstmt = conn.prepareStatement(query)) {
        				pstmt.setString(1,  jTextField[1].getText());
        				pstmt.setString(2,  jTextField[2].getText());
        				pstmt.setString(3,  jTextField[3].getText());
        				pstmt.setString(4,  jTextField[0].getText());
        				pstmt.executeUpdate();
        				JOptionPane.showMessageDialog(null,  "데이터가 성공적으로 수정되었습니다!");
        				dispose();
        			} catch(SQLException ex) {
        				ex.printStackTrace();
        				JOptionPane.showMessageDialog(null,  "데이터 수정 중 오류 발생: " + ex.getMessage());
        			}
        		}
        		
                else if (state.equals("INSTRUCTOR")) {
                    String query = "UPDATE INSTRUCTOR SET Rank = ?, IName = ?, IOffice = ?, IPhone = ?, DName = ?, DCode = ? WHERE Id = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[3].getText());
                        pstmt.setString(4, jTextField[4].getText());
                        pstmt.setString(5, jTextField[5].getText());
                        pstmt.setString(6, jTextField[6].getText());
                        pstmt.setString(7, jTextField[0].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 수정되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 수정 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("DEPT")) {
                    String query = "UPDATE DEPT SET DCode = ?, DOffice = ?, DPhone = ?, CName = ?, Id = ?, CStartDate = ? WHERE DName = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[3].getText());
                        pstmt.setString(4, jTextField[4].getText());
                        pstmt.setString(5, jTextField[5].getText());
                        pstmt.setString(6, jTextField[6].getText());
                        pstmt.setString(7, jTextField[0].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("STUDENT")) {
                    String query = "UPDATE STUDENT SET DOB = ?, FName = ?, MName = ?, LName = ?, Addr = ?, Phone = ?, Major = ?, Dname = ?, DCode = ? WHERE Sid = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[3].getText());
                        pstmt.setString(4, jTextField[4].getText());
                        pstmt.setString(5, jTextField[5].getText());
                        pstmt.setString(6, jTextField[6].getText());
                        pstmt.setString(7, jTextField[7].getText());
                        pstmt.setString(8, jTextField[8].getText());
                        pstmt.setString(9, jTextField[9].getText());
                        pstmt.setString(10, jTextField[0].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("TAKES")) {
                    String query = "UPDATE INSTRUCTOR SET SecId = ?, Grade = ? WHERE Sid = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[0].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("COURSE")) {
                    String query = "UPDATE COURSE SET CoName = ?, Credits = ?, Level_s = ?, CDesc = ?, DName = ?, DCode = ? WHERE CCode = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[3].getText());
                        pstmt.setString(4, jTextField[4].getText());
                        pstmt.setString(5, jTextField[5].getText());
                        pstmt.setString(6, jTextField[6].getText());
                        pstmt.setString(7, jTextField[0].getText());

                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
                else if (state.equals("SECTION")) {
                    String query = "UPDATE INSTRUCTOR SET SecNo = ?, Sem = ?, Year = ?, RoomNo = ?, Bldg = ?, DaysTime = ?, Id = ?, CCode = ?, CoName = ? WHERE SecId = ?";
                    try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                        pstmt.setString(1, jTextField[1].getText());
                        pstmt.setString(2, jTextField[2].getText());
                        pstmt.setString(3, jTextField[3].getText());
                        pstmt.setString(4, jTextField[4].getText());
                        pstmt.setString(5, jTextField[5].getText());
                        pstmt.setString(6, jTextField[6].getText());
                        pstmt.setString(7, jTextField[7].getText());
                        pstmt.setString(8, jTextField[8].getText());
                        pstmt.setString(9, jTextField[9].getText());
                        pstmt.setString(10, jTextField[0].getText());
                        // 쿼리 실행
                        pstmt.executeUpdate();
                        JOptionPane.showMessageDialog(null, "데이터가 성공적으로 삽입되었습니다!");
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "데이터 삽입 중 오류 발생: " + ex.getMessage());
                    }
                }
        	}
        });
                


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (conn != null) {
                        conn.close(); // 데이터베이스 연결 닫기
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose(); // "취소" 버튼을 눌렀을 때 창 닫기
            }
        });


        add(buttonPanel, BorderLayout.SOUTH);

        pack(); // 적절한 크기로 창 조정
        setVisible(true);
    }
}

