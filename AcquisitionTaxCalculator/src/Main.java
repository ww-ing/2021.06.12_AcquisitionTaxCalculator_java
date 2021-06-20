import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	//--패널생성
	JPanel p = new JPanel(new BorderLayout());//중앙 패널
	JPanel pN = new JPanel(new FlowLayout(0));//상단 페이지명 패널
	JPanel pC = new JPanel(new GridLayout(0,1,1,1));//계산기 패널
	JPanel[] pFlow = new JPanel[8];
	
	//--상단 라벨 생성
	Font font = new Font("궁서체",Font.BOLD,20);
	JLabel label_title = new JLabel("취득세 계산기");
	
	//--계산기 버튼 생성
	JToggleButton[] button = new JToggleButton[11];
	
	//--버튼 그룹 생성
	ButtonGroup bg_BuildingType = new ButtonGroup();
	ButtonGroup bg_BuildingSize = new ButtonGroup();
	ButtonGroup bg_BuildingCount = new ButtonGroup();
	
	//--버튼 이름 생성
	String[] button_text = { "주택", "오피스텔", "그 외 건물", "40m^2 이하", "60m^2 이하", 
			"85m^2 이하", "85m^2 초과", "1주택", "2주택", "3주택", "그 이상"};
	
	//--라디오 버튼 생성
	JRadioButton radio_adjusted_area = new JRadioButton("조정대상지역");
	JButton button_adfusted_area = new JButton("조정대상 지역 보기");
	JRadioButton radio_first_purchase = new JRadioButton("생애최초 구입");
	JRadioButton radio_metropolitan_area = new JRadioButton("수도권");
	JButton button_tax_calculation = new JButton("취득세 계산");
	
	//--금액 입력 및 출력 텍스트필드 생성
	JTextField text_price = new JTextField("금액 입력",20);
	JTextField text_calculation = new JTextField("계산 금액",20);
	
	
	Handler handler = new Handler();//핸들러 객체
	
	
	AdjustedArea adjustedAreaPage = new AdjustedArea();//조정대상지역 페이지 객체
	
	public Main() {
		super(":::취득세 계산기:::");
		Container cp = this.getContentPane();
		cp.add(p, "Center");
		p.setBackground(Color.white);
		
		//--패널 및 라벨 부착
		p.add(pN,"North");
		pN.add(label_title);
		label_title.setFont(font);
		p.add(pC,"Center");
		pC.setBackground(Color.gray);
		
		
		//--계산기 패널의 사이즈 및 보더 설정
		pC.setPreferredSize(new Dimension(ABORT,ABORT));
		pC.setBorder(new EmptyBorder(70, 0, 200, 0));
		
		
		//--페이지 기본값 설정
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//--버튼에 버튼이름 넣기
		for(int i=0; i<button.length; i++) {
			button[i] = new JToggleButton(button_text[i]);
		}
		
		//--pFlow 패널 생성
		for(int i=0; i<pFlow.length; i++) {
			pFlow[i] = new JPanel(new FlowLayout(0));
		}
		
		//--계산기 패널에 pFlow 패널 넣기
		for(int i=0; i<8; i++) {
			pC.add(pFlow[i]);
		}
		
		//--pFlow 패널에 버튼 넣기
		for(int i=0; i<=10; i++) {
			pFlow[0].add(button[i]);
			button[0].setSelected(true);
			
			if(i>=3 && i<7) {
				pFlow[1].add(button[i]);
				
			}else if(i>=7) {
				pFlow[2].add(button[i]);
			}
		}
		
		pFlow[3].add(radio_adjusted_area);
		pFlow[3].add(button_adfusted_area);
		pFlow[4].add(radio_first_purchase);
		
		pFlow[4].add(radio_metropolitan_area);
		radio_metropolitan_area.setVisible(false);
		
		pFlow[5].add(text_price);
		pFlow[6].add(button_tax_calculation);
		pFlow[7].add(text_calculation);
		text_calculation.setFont(font);
		text_calculation.setEnabled(false);
		pFlow[7].setVisible(false);
		
		
		//--버튼 그룹 넣기
		for(int i=0; i<button.length; i++) {
			
			bg_BuildingType.add(button[i]);
			
			if(i>=3 && i<7) {
				bg_BuildingSize.add(button[i]);
				
			}else if(i>=7) {
				bg_BuildingCount.add(button[i]);
			}
			
		}
		
		//--핸들러생성
		for(int i=0; i<button.length; i++) {
			button[i].addActionListener(handler);
		}
		
		button_adfusted_area.addActionListener(handler);
		button_tax_calculation.addActionListener(handler);
		radio_first_purchase.addActionListener(handler);
		
	}//--생성자
	
	class Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object obj=e.getSource();
			
			//주택 페이지
			if(obj==button[0]) {
				for(int i=0; i<8; i++) {
					pC.add(pFlow[i]);
				}
				pFlow[7].setVisible(false);
				pC.validate();
				pC.setBorder(new EmptyBorder(70, 0, 200, 0));
				
			//오피스텔 페이지
			}else if(obj==button[1]) {

				pC.add(pFlow[0]);
				pC.add(pFlow[1]);
				pC.remove(pFlow[2]);
				pC.add(pFlow[3]);
				pC.add(pFlow[4]);
				pC.add(pFlow[5]);
				pC.add(pFlow[6]);
				pC.add(pFlow[7]);
				pFlow[7].setVisible(false);
				pC.validate();
				pC.setBorder(new EmptyBorder(70, 0, 250, 0));
			
			//그 외 페이지	
			}else if(obj==button[2]) {

				pC.add(pFlow[0]);
				pC.remove(pFlow[1]);
				pC.remove(pFlow[2]);
				pC.remove(pFlow[3]);
				pC.remove(pFlow[4]);
				pC.add(pFlow[5]);
				pC.add(pFlow[6]);
				pC.add(pFlow[7]);
				pFlow[7].setVisible(false);
				pC.validate();
				pC.setBorder(new EmptyBorder(70, 0, 400, 0)); 
				
			}
			
			//조정대상지역 페이지 띄우기
			if(obj==button_adfusted_area) {

				adjustedAreaPage.setSize(500, 500);
				adjustedAreaPage.setVisible(true);
				adjustedAreaPage.setLocation(320, 300);
				
			}
			
			//--"생애최조 구입" 라디오 버튼 클릭시 "수도권" 버튼 보여주기 및 숨기기
			if(radio_first_purchase.isSelected()) {
				radio_metropolitan_area.setVisible(true);
			}else if(!radio_first_purchase.isSelected()) {
				radio_metropolitan_area.setVisible(false);
			}
			
			
			//---------------------------------------------------------------------계산
			
			//--주택 페이지 계산
			if(button[0].isSelected() && obj==button_tax_calculation) {//주택 페이지의 취득세 계산 클릭시
				int Amount = Integer.parseInt(text_price.getText());
				int value = 0;
			
				if(Amount<=600000000) {//6억 이하일 경우
					value=(int) (Amount*0.01);//취득세는 취득가액의 1%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>600000000 && Amount<=650000000) {//6억 초과 6억 5천 이하일 경우
					value=(int) (Amount*0.0133);//취득세는 취득가액의 1.33%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>650000000 && Amount<=700000000) {//6억 5천 초과 7억 이하일 경우
					value=(int) (Amount*0.0167);//취득세는 취득가액의 1.67%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>700000000 && Amount<=750000000) {//7억 초과 7억 5천 이하일 경우
					value=(int) (Amount*0.02);//취득세는 취득가액의 2%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>750000000 && Amount<=800000000) {//7억 5천 초과 8억 이하일 경우
					value=(int) (Amount*0.0233);//취득세는 취득가액의 2.33%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>800000000 && Amount<=850000000) {//8억 초과 8억 5천 이하일 경우
					value=(int) (Amount*0.0267);//취득세는 취득가액의 2.67%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}else if(Amount>850000000) {//8억 5천 초과일 경우
					value=(int) (Amount*0.03);//취득세는 취득가액의 3%
					value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
					
				}
			
				//비조정대상지역, 1억 초과, 3주택일 경우
				if(!radio_adjusted_area.isSelected() && button[9].isSelected()
						&& Amount > 100000000) {
					
					value=(int) (Amount*0.08);//취득세는 취득가액의 8% 
					
				//비조정대상지역, 1억 초과, 4주택 이상일 경우
				}else if(!radio_adjusted_area.isSelected() && button[10].isSelected()
						&& Amount > 100000000) {
					
					value=(int) (value*0.12);//취득세는 취득가액의 12%
				}
				
				
				//조정대상지역, 1억 초과, 2주택 이상일 경우
				if(radio_adjusted_area.isSelected() && button[8].isSelected()
						&& Amount > 100000000) {
					
					value=(int) (Amount*0.08);//취득세는 취득가액의 8%
			
				//조정대상지역, 1억 초과, 3주택 이상일 경우
				}else if(radio_adjusted_area.isSelected() && Amount > 100000000 && (button[9].isSelected() 
						|| button[10].isSelected())) {
					
					value=(int) (value*0.12);//취득세는 취득가액의 12%
					
				}
				
				//85^2 초과, 1억 이하일 경우
				if(button[6].isSelected() && Amount<=100000000) {
					value+=Amount*0.002;//취득세는 취득가액의 0.2% 가산 (농어촌특별세 )
					
				}
		
				//85^2 초과, 1억 초과, 비조정대상지역일 경우
				if(button[6].isSelected() && Amount>100000000 && !radio_adjusted_area.isSelected()) {
					
					//2주택 이하일 경우
					if(button[7].isSelected() || button[8].isSelected()) {
						value+=Amount*0.002;//취득세는 취득가액의 0.2% 가산 (농어촌특별세)
						
					//3주택일 경우
					}else if(button[9].isSelected()) {
						value+=Amount*0.006;//취득세는 취득가액의 0.6% 가산 (농어촌특별세)
	
					//4주택 이상일 경우
					}else if(button[10].isSelected()) {
						value+=Amount*0.01;//취득세는 취득가액의 1% 가산 (농어촌특별세)
						
					}
				//85^2 초과, 1억 초과, 조정대상지역일 경우
				}else if(button[6].isSelected() && Amount>100000000 && radio_adjusted_area.isSelected()) {
					
					//1주택일 경우
					if(button[7].isSelected()) {
						value+=Amount*0.002;//취득세는 취득가액의 0.2% 가산 (농어촌특별세)
					
					//2주택일 경우	
					}else if(button[8].isSelected()) {
						value+=Amount*0.006;//취득세는 취득가액의 0.6% 가산 (농어촌특별세)
					
					//3주택 이상일 경우	
					}else if(button[9].isSelected() || button[10].isSelected()) {
						value+=Amount*0.01;//취득세는 취득가액의 1% 가산 (농어촌특별세)
						
					}
					
				}
				
				//생애최초 구입, 1억 5천 이하, 1주택일 경우
				if(radio_first_purchase.isSelected() && Amount<=150000000 && button[7].isSelected()) {
					value=0;//취득세 면제
					
				//생애최초 구입, 1억 5천 초과 3억 이하, 1주택일 경우
				}else if(radio_first_purchase.isSelected() && Amount>150000000 && Amount<=300000000 && button[7].isSelected()) {
					value=value/2;//취득세 50% 면제
					
				}
				
				text_calculation.setText(Integer.toString((int)(value))+"원");//계산된 결과 출력
				pFlow[7].setVisible(true);//계산값 페이지 띄우기
				
			}//--주택 페이지 계산 끝
			
			//--오피스텔 페이지 계산
			if(obj==button_tax_calculation && button[1].isSelected()) {//오피스텔 페이지의 취득세 계산 클릭시
				int Amount = Integer.parseInt(text_price.getText());
				int value = 0;
				
				value=(int) (Amount*0.04);//취득세는 취득가액의 4%
				value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
				value+=Amount*0.002;//취득세는 취득가액의 0.2% 가산 (농어촌특별세)
			
				text_calculation.setText(Integer.toString((int)(value))+"원");//계산된 결과 출력
				pFlow[7].setVisible(true);//계산값 페이지 띄우기
					
			
			}//--오피스텔 페이지 계산 끝
			
			//--그 외 페이지 계산
			if(obj==button_tax_calculation && button[2].isSelected()) {//그 외 페이지의 취득세 계산 클릭시
				int Amount = Integer.parseInt(text_price.getText());
				int value = 0;
				
				value=(int) (Amount*0.04);//취득세는 취득가액의 4%
				value+=value*0.1;//취득세의 10% 가산 (유상취득 지방교육세)
				value+=Amount*0.002;//취득세는 취득가액의 0.2% 가산 (농어촌특별세)
			
				text_calculation.setText(Integer.toString((int)(value))+"원");//계산된 결과 출력
				pFlow[7].setVisible(true);//계산값 페이지 띄우기
				
			}//--그 외 페이지 계산 끝
			
		}
		
	}

	//계산기 페이지 띄우기
	public static void main(String[] args) {
		Main my = new Main();
		my.setSize(800, 700);
		my.setVisible(true);
	}

}