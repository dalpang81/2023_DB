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
	
	String colName[] = { "회원아이디", "비밀번호", "이름", "나이", "직업", "등급", "적립금", "우편번호", "기본주소", "상세주소", "감시아이디" }; //기본 행 이름
	DefaultTableModel model = new DefaultTableModel(colName, 0); //기본테이블 설정
	JTable table = new JTable(model);//기본 테이블 생성
	JScrollPane scrollPane = new JScrollPane(table); //테이블을 JScrollPane에 설정
	String row[] = new String[12];
	
	private Connection conn = null;
	private String path = "oracle.jdbc.driver.OracleDriver"; //DriverManager path 설정
	private String url = "jdbc:oracle:thin:@localhost:1521:xe"; //localhost 설정
	private String id = "htetmyet"; //id 설정
	private String pwd = "1234"; //pw 설정
	
	public dbProject() {
		setTitle("DBProject_이원영"); //창 이름 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //창에서 닫기버튼 누를 시 종료 설정
		setLayout(new BorderLayout()); //BorderLayout으로 기본 설정
		JPanel Btn_panel = new JPanel(); //버튼패널 생성
		JPanel panel = new JPanel();  //패널 생성
		Btn_panel.setLayout(new FlowLayout()); //버튼패널 내 FlowLayout설정
		 panel.setLayout(new FlowLayout()); //패널 내 FlowLayout설정
		 panel.add(label); //패널에 label추가
		
		 state = new JLabel(); //state를 표시하는 JLabel 설정
		 state.setText("Oracle DB 연동테스트"); //JLabel 내용 설정
		 
		JButton btn_customer = new JButton("회원 DB"); //회원 테이블을 불러오는 버튼
		JButton btn_company = new JButton ("제조업체 DB"); //제조업체 테이블을 불러오는 버튼
		JButton btn_goods = new JButton ("상품 DB"); //상품 테이블을 불러오는 버튼
		JButton btn_write = new JButton ("게시글 DB");//게시글 테이블을 불러오는 버튼

		Btn_panel.add(btn_customer); //회원 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_company); //제조업체 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_goods); //상품 테이블 버튼을 버튼 패널에 추가
		Btn_panel.add(btn_write); //게시글 테이블 버튼을 버튼 패널에 추가
		
		 add(state, BorderLayout.NORTH); //상태표시라벨을 북쪽에 표시
		 add(panel, BorderLayout.WEST); //이미지 패널을 서쪽에 표시
		 add(Btn_panel, BorderLayout.SOUTH); //버튼패널을 남쪽에 표시
 		 add(scrollPane, BorderLayout.CENTER); //테이블을 가운데에 표시

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
		 btn_customer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				//필요한 열 추가
				model.addColumn("회원아이디");
				model.addColumn("비밀번호");
				model.addColumn("이름");
				model.addColumn("나이");
				model.addColumn("직업");
				model.addColumn("등급");
				model.addColumn("적립금");
				model.addColumn("우편번호");
				model.addColumn("기본주소");
				model.addColumn("상세주소");
				model.addColumn("감시아이디");

				//수정된 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //회원테이블 함수 실행
				Customer();
			}
		 });
		 
		 //제조업체 테이블 버튼
		 btn_company.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset(); //행, 열 초기화 
				//필요한 열 추가
				model.addColumn("제조업체명");
				model.addColumn("전화번호");
				model.addColumn("위치");
				model.addColumn("담당자");
				
				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //제조업체테이블 함수 실행
				Company();
			}
		 });
		 
		 //상품 테이블 버튼
		 btn_goods.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				//필요한 열 추가
				model.addColumn("상품번호");
				model.addColumn("상품명");
				model.addColumn("재고량");
				model.addColumn("단가");
				model.addColumn("제조업체명");
				model.addColumn("공급일자");
				model.addColumn("공급량");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //상품테이블 함수 실행
				Goods();
			}
		 });
		 
		 btn_write.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				reset(); //행, 열 초기화
				//필요한 열 추가
				model.addColumn("글번호");
				model.addColumn("글제목");
				model.addColumn("글내용");
				model.addColumn("작성일자");
				model.addColumn("회원아이디");

				//수정한 열 반영하여 테이블 생성
				table = new JTable(model);
				//수정된 테이블 반영
				scrollPane.setViewportView(table);
				//수정된 테이블 배치
		 		 add(scrollPane, BorderLayout.CENTER);
		 		 //게시글테이블 함수 실행
				Write();
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
	public void Customer() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from 회원"); //회원테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 12; i++) { 
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
	public void Company() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from 제조업체"); //제조업체테이블을 불러오는 쿼리문 실행
			
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
	
	//상품 테이블 함수
	public void Goods() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from 상품"); //상품테이블을 불러오는 쿼리문 실행
			
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
	public void Write() {
		try {			
			Statement stmt = conn.createStatement(); //Statement 생성
			ResultSet rset = stmt.executeQuery("select * from 게시글"); //게시글테이블을 불러오는 쿼리문 실행
			
			while (rset.next()) {
				// row 값읽기
				for (int i = 1; i < 6; i++) { 
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
