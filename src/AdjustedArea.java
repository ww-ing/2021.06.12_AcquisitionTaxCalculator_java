
import java.awt.*;
import javax.swing.*;

public class AdjustedArea extends JFrame {

	JPanel p = new JPanel(new BorderLayout());//중앙 패널
	JPanel pW = new JPanel(new GridLayout(0,1,3,3));//버튼 패널
	JPanel pC = new JPanel();//텍스트 패널
	
	JToggleButton[] button = new JToggleButton[15];//토글버튼 생성
	ButtonGroup buttonGroup = new ButtonGroup();//버튼그룹 생성
	
	JTextArea ta = new JTextArea("전지역(’16.11.3)",28,35);//지역별 조정대상 구역
	
	//조정대상 지역
	String[] button_text = { "서울", "경기", "인천", "부산", "대구", "광주", "대전", "울산", "세종", 
			"충북", "충남", "전북", "전남", "경북", "경남"};
	
	//조정대상 구역
	String[] text = {
			"전지역(’16.11.3)",
			"과천, 성남, 하남, 동탄2(’16.11.3), \n"
					+ "\n"
					+ "광명(’17.6.19)\n"
					+ "\n"
					+ "구리, 안양동안, 광교지구(’18.8.28),\n"
					+ "\n"
					+ "수원팔달, 용인수지‧기흥(’18.12.31),\n"
					+ "\n"
					+ "수원영통·권선·장안, 안양만안, 의왕(‘20.2.21)\n"
					+ "\n"
					+ "고양, 남양주, 화성, 군포, 부천, 안산, 시흥, 용인처인, 오산, 안성, 평택, 광주,"
					+ "\n"
					+ "양주, 의정부(’20.6.19)\n"
					+ "\n"
					+ "김포(‘20.11.20)\n"
					+ "\n"
					+ "파주(‘20.12.18)",
					"중, 동, 미추홀, 연수, 남동, 부평, 계양, 서(’20.6.19)",
			"해운대, 수영, 동래, 남, 연제(’20.11.20)\n"
					+ "\n"
					+ "서구, 동구, 영도구, 부산진구, 금정구, 북구, 강서구, 사상구, 사하구(‘20.12.18)",
			"수성(’20.11.20)\n"
					+ "\n"
					+ "중구, 동구, 서구, 남구, 북구, 달서구, 달성군(‘20.12.18)",
			"동구, 서구, 남구, 북구, 광산구(‘20.12.18)",
			"동, 중, 서, 유성, 대덕(’20.6.19)",
			"중구, 남구(‘20.12.18)",
			"세종(’16.11.3)",
			"청주(’20.6.19)",
			"천안동남·서북, 논산, 공주(‘20.12.18)",
			"전주완산·덕진(‘20.12.18)",
			"여수, 순천, 광양(‘20.12.18)",
			"포항남, 경산(‘20.12.18)",
			"창원성산(‘20.12.18)"
			
	};
	
	public AdjustedArea() {
		super(":::조정대상 지역:::");
		Container cp = this.getContentPane();
		cp.add(p, "Center");
		p.add(pW, "West");
		pW.setBackground(Color.gray);
		p.add(pC, "Center");
		pC.setBackground(Color.white);
		
		pC.add(ta);
		
		for(int i=0; i<button.length; i++) {
			
			button[i] = new JToggleButton(button_text[i]);//토글버튼 생성
			button[0].setSelected(true);//서울버튼을 기본값으로
			buttonGroup.add(button[i]);//버튼그룹 넣기
			pW.add(button[i]);//버튼 넣기
			
			//--핸들러
			button[i].addActionListener(e->{
				Object obj = e.getSource();
				
				//지역 버튼 클릭시 해당 조정대상 구역 보여주기
				for(int k=0; k<button.length; k++) {
					if(obj == button[k]) {
						ta.setText(text[k]);
						break;
					}
				}
			});
		}

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//페이지 기본값 설정

	}//--생성자

}
