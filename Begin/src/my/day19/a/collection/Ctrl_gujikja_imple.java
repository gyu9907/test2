package my.day19.a.collection;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ctrl_gujikja_imple implements Ctrl_gujikja {

	// == 구직자 회원가입 ==
	// 구직자(Gujikja) 신규 회원가입시 
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// List<Gujikja_imple> gu_list 와 Map<String, Gujikja_imple> gu_map 에 저장시켜주는 메소드 생성하기
	@Override
	public void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja_imple> gu_map) {
		Gujikja_imple gu = new Gujikja_imple();
		
		// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
		String id = null;
		outer:
		do {
			System.out.print("1.아이디 : ");
			id = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!!
			                               // "" 또는 "        " 공백만으로는 입력불가!!
			
			// == 가입된 회원들에 대해 중복아이디 검사하기 == //
			if(gu_map.get(id)!=null) {
				System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
				continue outer;
			}
			// === 중복 아이디 검사하기 끝 === //
			
			gu.setId(id);
			
		} while(!(gu.getId()!=null));
		
		
		// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("2.비밀번호 : "); 
			String passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
			
			gu.setPasswd(passwd);
			
		} while(!(gu.getPasswd()!=null));
		// end of do~while-----------------
		
		// 성명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
        // 성명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
		do {
			System.out.print("3.성명 : "); 
			String name = sc.nextLine(); // ""  "      "  "강 감 찬"  "강"  "김수한무거북이와두루미"  "강KamC"
                                           // "강감찬"
			
			gu.setName(name);
			
		} while(!(gu.getName() != null));
		// end of do~while-----------------
		
		// 주민번호는 필수 입력사항이면서 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("4.주민번호 : "); 
			String jubun = sc.nextLine(); // "9610021" "9610022"  정상
						                  // "0010023" "0010024"  정상
						                  // "9604311" "9604312"  "0004313"  "0004314" 비정상
						                  // "9610025" "0010026"  비정상
			gu.setJubun(jubun);
			
		} while(!(gu.getJubun() != null));
		// end of do~while-----------------
		
		//////////////////////////////////////////////
		
		gu_list.add(gu);
		gu_map.put(id, gu);
		
		System.out.println(">> 구직자 회원가입 성공 !! <<\n");
	}// end of public void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja_imple> gu_map)---------------------

	// == 구직자 로그인 메소드 생성하기 == //
	@Override
	public Gujikja_imple login(Scanner sc, Map<String, Gujikja_imple> gu_map) {
		System.out.print("ID : ");
		String id = sc.nextLine();

		System.out.print("비밀번호 : ");
		String passwd = sc.nextLine();
		
		if(gu_map.get(id)!=null && passwd.equals(gu_map.get(id).getPasswd())) {
			return gu_map.get(id);
		}
		
		return null;
	}// end of public Gujikja_imple login(Scanner sc, Map<String, Gujikja_imple> gu_map)--------------

	// 구직자 모두보기
	@Override
	public void view_all_info(List<Gujikja_imple> gu_list) {
		/*
			----------------------------------------------------------------------
			아이디	비밀번호		성명		생년월일		성별		현재만나이		가입일자
			----------------------------------------------------------------------
			eomjh	qWe****** 	엄정화	1986-10-20	여		37			2024-08-29 15:20:09
			leess	Abc****** 	이순신	1986-01-20	남		38			2024-08-29 15:20:09
			chaew	Wxy****** 	차은우	2001-06-20	남		23			2024-08-29 15:20:09
			
		 */
		
		if(gu_list.size() == 0) {
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else {
			title();
			StringBuilder sb = new StringBuilder();
			
			for(Gujikja_imple gu : gu_list) {
				sb.append(gu.getInfo()+"\n");
			}// end of for------------------------------
			
			System.out.println(sb.toString());
		}
		
	}// end of view_all_info(List<Gujikja_imple> gu_list)-----------------------------

	// 타이틀
	void title() {
		System.out.println("-".repeat(70)+"\n"
						 + "아이디\t비밀번호\t\t성명\t생년월일\t\t성별\t현재만나이\t가입일자\n"
						 + "-".repeat(70));
	}// end of title()-------------------------
	
	// 검색하기
	@Override
	public void search_menu(Scanner sc, List<Gujikja_imple> gu_list) {
		
		String str_menuno="";
		do {
			System.out.println("\n === 검색 메뉴 ===\n"
					 + "1.연령대 검색   2.성별 검색   3.연령대 및 성별 검색   4.메인 메뉴로 돌아가기\n");
			System.out.print("▷ 메뉴 번호 선택 : ");
			str_menuno = sc.nextLine();
			switch (str_menuno) {
			case "1": // 연령대 검색
				search_ageLine(sc, gu_list);
				break;
			case "2": // 성별 검색
				search_gender(sc, gu_list);
				break;
			case "3": // 연령대 및 성별 검색
				search_ageLine_gender(sc, gu_list);
				
				break;
			case "4": // 메인 메뉴로 돌아가기
				
				break;

			default:
				System.out.println("[경고] 메뉴에 없는 번호입니다.\n");
				break;
			}
		} while(!"4".equals(str_menuno));
		// end of do~while--------------------
	}// end of search_menu()-------------------------------

	// 연령대 검색
	void search_ageLine(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else { // 구직자가 있는 경우
			
			boolean isUse_ageLine = false;
			String str_ageLine = "";
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				str_ageLine = sc.nextLine(); // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
	                                                // "25" "강아지" "-20" --> 비정상
				switch (str_ageLine) {
				case "0":
				case "10":
				case "20":
				case "30":
				case "40":
				case "50":
				case "60":
				case "70":
				case "80":
					isUse_ageLine = true;
					break;

				default:
					System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
					break;
				}// end of switch (str_ageLine)--------------------------
			} while(!isUse_ageLine);
			// end of do~while---------------------------
			
			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			for(int i=0; i<gu_list.size(); i++) {
				int ageLine = gu_list.get(i).age()/10*10;
							// 나이/10*10 ==> 연령대
							// 37/10*10 ==> 30
							// 36/10*10 ==> 30
							// 23/10*10 ==> 20
				if(Integer.parseInt(str_ageLine) == ageLine) {
					isFind = true;
					sb.append(gu_list.get(i).getInfo()+"\n");
				}
				
			}// end of for---------------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 연령대가 "+str_ageLine+"대인 구직자는 없습니다]\n");
			}
			
		}// end of if~else---------------------------
	}// end of search_ageLine(Scanner sc, List<Gujikja_imple> gu_list)---------------------

	// 성별 검색
	void search_gender(Scanner sc, List<Gujikja_imple> gu_list) {
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else { // 구직자가 있는 경우
			boolean isUse_gender = false;
			String input_gender = "";
			do {
				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
				input_gender = sc.nextLine(); // "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
                							  // ""  "      " "강아지" --> 비정상
				switch (input_gender.trim()) {
				case "남":
				case "여":
					isUse_gender = true;
					break;

				default:
					System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
					break;
				}// end of switch (input_gender.trim())--------------------------
			} while(!isUse_gender);
			// end of do~while---------------------------
			
			// == 입력받은 성별에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			for(int i=0; i<gu_list.size(); i++) {
				if(input_gender.trim().equals(gu_list.get(i).gender())) {
					isFind = true;
					sb.append(gu_list.get(i).getInfo()+"\n");
				}
				
			}// end of for---------------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 성별이 "+input_gender+"자인 구직자는 없습니다]\n");
			}
		}// end of if~else---------------------------
	}// end of search_gender(Scanner sc, List<Gujikja_imple> gu_list)-----------------

	// 연령대 및 성별 검색
	void search_ageLine_gender(Scanner sc, List<Gujikja_imple> gu_list) {
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else { // 구직자가 있는 경우
			
			boolean isUse_ageLine = false;
			String str_ageLine = "";
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				str_ageLine = sc.nextLine(); // "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
	                                         // "25" "강아지" "-20" --> 비정상
				switch (str_ageLine) {
				case "0":
				case "10":
				case "20":
				case "30":
				case "40":
				case "50":
				case "60":
				case "70":
				case "80":
					isUse_ageLine = true;
					break;

				default:
					System.out.println("[경고] 올바른 연령대를 입력하세요!!\n");
					break;
				}// end of switch (str_ageLine)--------------------------
			} while(!isUse_ageLine);
			// end of do~while---------------------------
			
			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			boolean isFind_ageline = false;
			
			for(int i=0; i<gu_list.size(); i++) {
				int ageLine = gu_list.get(i).age()/10*10;
							// 나이/10*10 ==> 연령대
							// 37/10*10 ==> 30
							// 36/10*10 ==> 30
							// 23/10*10 ==> 20
				if(Integer.parseInt(str_ageLine) == ageLine) {
					isFind_ageline = true;
					break;
				}
				
			}// end of for---------------------
			
			if(!isFind_ageline) { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하지 않는 경우
				System.out.println("[검색결과 연령대가 "+str_ageLine+"대인 구직자는 없습니다]\n");
				return; // 해당 메소드(지금은 search_ageLine_gender()) 를 종료하는 것이다.
			}
			else { // 검색하고자 하는 연령대에 해당하는 구직자가 존재하는 경우
				
				// === !! 입력받은 연령대 및 성별에 해당하는 구직자 찾기 !! === //
				boolean isUse_gender = false;
				String input_gender;
				do {
					System.out.print("▷ 검색하고자 하는 성별[남/여] : ");
					input_gender = sc.nextLine();
					input_gender = input_gender.trim();
					switch (input_gender) {
					case "남":
					case "여":
						isUse_gender = true;
						break;
	
					default:
						System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
						break;
					}// end of switch (input_gender)-------------------
					
				} while(!isUse_gender);

				StringBuilder sb = new StringBuilder();
				boolean isFind_ageline_gender = false;
				
				for(int i=0; i<gu_list.size(); i++) {
					if(Integer.parseInt(str_ageLine) == gu_list.get(i).age()/10*10
						&& input_gender.equals(gu_list.get(i).gender())) {
						
						isFind_ageline_gender = true;
						sb.append(gu_list.get(i).getInfo()+"\n");
					}
					
				}// end of for------------------
				if(isFind_ageline_gender) {
					title();
					System.out.println(sb.toString());
				}
				else {
					System.out.println("[검색결과 연령대가 "+str_ageLine+"대인 "+input_gender+"자 구직자는 없습니다]\n");
				}
			}
			
		}// end of if~else--------------------------
	}// end of search_ageLine_gender(Scanner sc, List<Gujikja_imple> gu_list)-------------------

	// === 구직자 전용메뉴 메소드 생성하기 === //
	@Override
	public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {
		
		String str_menuno;
		
		do {
			System.out.println("\n === 구직자 전용메뉴(" + login_gu.getName() + "님 로그인 중) ===\n"
							 + "1.내 정보 보기   2.내 정보 수정   3.모든 구인회사 조회   4.구인회사 검색하기\n"
							 + "5.모든 채용공고 조회   6.채용공고 상세보기   7.채용 응모하기   8.채용 응모한 것 조회\n"
							 + "9.채용 응모한 것 수정하기   10.로그아웃\n");
			System.out.print("▷ 메뉴번호 선택 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 내 정보 보기
					view_myInfo(login_gu);
					break;
	
				case "2": // 내 정보 수정
					update_myInfo(sc, login_gu);
					break;
					
				case "3": // 모든 구인회사 조회
					view_all_company_info(cp_list);
					break;
					
				case "4": // 구인회사 검색하기
					search_company(sc, cp_list);
					break;
					
				case "5": // 모든 채용공고 조회(현재 채용이 진행중인 것만 조회)
					view_all_recruit_info(rc_list);
					break;
					
				case "6": // 채용공고 상세보기
					view_detail_one_recruit_info(sc, rc_list);
					break;
					
				case "7": // 채용 응모하기
					input_recruitApply(sc, login_gu, rc_list, rcapply_list);
					break;
					
				case "8": // 채용 응모한 것 조회
					view_my_recruitApply(login_gu, rcapply_list);
					break;
					
				case "9": // 채용 응모한 것 수정하기
					update_my_recruitApply(sc, login_gu, rcapply_list);
					break;
					
				case "10": // 로그아웃
					
					break;
					
				default:
					System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n");
					break;
			}// end of switch(str_menuno)-----------------
		} while(!"10".equals(str_menuno));
		// end of do~while------------------------
		
		System.out.println(">> 로그아웃 되었습니다. <<\n");
		
	}// end of public void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)-----------

	// 내 정보 보기
	private void view_myInfo(Gujikja_imple login_gu) {
	/*
		
		>>> 엄정화님의 정보 <<<
		--------------------------------------------------------------
		  아이디    비밀번호      성명    주민번호    성별    현재나이    가입일자
		--------------------------------------------------------------
		  eomjh    qWer1234$   엄정화  8610202   여      37        2024-09-02 10:07:10
	 */
		System.out.println(">>> "+ login_gu.getName() +"님의 정보 <<<");
		System.out.println("-".repeat(75));
		System.out.println("아이디\t비밀번호\t\t성명\t주민번호\t성별\t현재나이\t가입일자");
		System.out.println("-".repeat(75));
		System.out.println(login_gu.view_info());
		
	}// end of private void view_myInfo(Gujikja login_gu)--------------------

	// 내 정보 수정
	private void update_myInfo(Scanner sc, Gujikja_imple login_gu) {
		
		view_myInfo(login_gu);
		
		System.out.println("\n>> [주의사항] 변경하지 않고 예전의 데이터값을 그대로 사용하시려면 그냥 엔터하세요!!\n");
		
		boolean exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("1.비밀번호 : ");
			String new_passwd = sc.nextLine();	// 기존비밀번호인 qWer1234$ 을 qWer0070$ 으로 변경하려고 한다.
	        									// 기존비밀번호인 qWer1234$ 을 qWer1234$ 으로 변경하려고 하려면 기존암호와 동일하므로 변경이 불가합니다. 
	        									// 기존비밀번호인 qWer1234$ 을 변경하기 싫다. 
												// 기존비밀번호인 qWer1234$ 을 abcd 로 변경하고자 할때는 비밀번호 정책에 맞지 않으므로 안된다.!! 
			
			if(!new_passwd.equals("")) { // 입력한 비밀번호가 엔터가 아닐 경우
				
				if(new_passwd.equals(login_gu.getPasswd())) { // 입력한 비밀번호가 기존 비밀번호와 같을 경우
					System.out.println(">> 기존 암호와 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 비밀번호가 기존 비밀번호와 다를 경우
					login_gu.setPasswd(new_passwd);
					
					if(login_gu.getPasswd().equals(new_passwd)) { // 비밀번호 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 비밀번호가 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("2.성명 : ");
			String new_name = sc.nextLine();	// 기존성명인 엄정화 를 엄화정 으로 변경하려고 한다.
									            // 기존성명인 엄정화 를 엄정화로 변경하려고 하려면 기존성명과 동일하므로 변경이 불가합니다. 
									            // 기존성명인 엄정화 을 변경하기 싫다. 
									            // 기존성명인 엄정화를 엄JungHwa 로 변경하고자 할때는 성명정책에 맞지 않으므로 안된다.!!
			
			if(!new_name.equals("")) { // 입력한 성명이 엔터가 아닐 경우
				
				if(new_name.equals(login_gu.getName())) { // 입력한 성명이 기존 성명과 같을 경우
					System.out.println(">> 기존 성명과 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 성명이 기존 성명과 다를 경우
					login_gu.setName(new_name);
					
					if(login_gu.getName().equals(new_name)) { // 성명 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 성명이 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("3.주민번호 : ");
			String new_jubun = sc.nextLine();
			
			if(!new_jubun.equals("")) { // 입력한 주민번호가 엔터가 아닐 경우
				
				if(new_jubun.equals(login_gu.getJubun())) { // 입력한 주민번호가 기존 주민번호와 같을 경우
					System.out.println(">> 기존 주민번호와 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 주민번호가 기존 성명과 다를 경우
					login_gu.setJubun(new_jubun);
					
					if(login_gu.getJubun().equals(new_jubun)) { // 주민번호 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 주민번호가 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
	}// end of private void update_myInfo(Scanner sc, Gujikja login_gu)-------------
	
	// 모든 구인회사 조회
	private void view_all_company_info(List<Company_imple> cp_list) {
		
		if(!(cp_list.size() > 0)) {
			System.out.println(">> 구인회사로 등록된 회사가 한 개도 없습니다.");
		}
		else {
			title_company();

			StringBuilder sb = new StringBuilder();
			
			for(Company_imple cp : cp_list) {
				sb.append(cp.getInfo() + "\n");
			}// end of for--------------------
			
			System.out.println(sb.toString());
		}
		
	}// end of private void view_all_company_info(List<Company_imple> cp_list-------------

	private void title_company() {
		System.out.println("=".repeat(50));
		System.out.println("회사명	업종	사업자등록번호	자본금");
		System.out.println("=".repeat(50));
		
	}// end of private void title_company()---------------
	
	// 구인회사 검색하기
	private void search_company(Scanner sc, List<Company_imple> cp_list) {
		
		String str_menuno;
		do {
			//////////////////////////////////////////////////////
			System.out.println(">>> 구인회사 검색메뉴 <<<\n"
	                + "1.업종검색    2.자본금검색    3.구직자메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			str_menuno = sc.nextLine();
			switch (str_menuno) {
				case "1": // 업종검색
					search_jobtype_company(sc, cp_list);
					break;
	
				case "2": // 자본금검색
					search_seedmoney_company(sc, cp_list);
					break;
	
				case "3": // 구직자메뉴로 돌아가기
					
					break;
	
				default:
					System.out.println(">> [경고] 구인회사 검색메뉴에 없는 번호입니다. <<\n");
					break;
			}// end of switch (str_menuno)------------------
			//////////////////////////////////////////////////////
		} while(!"3".equals(str_menuno));
		// end of do~while------------------------
		
	}// end of private void search_company(Scanner sc, List<Company_imple> cp_list)---------------

	// 업종검색
	private void search_jobtype_company(Scanner sc, List<Company_imple> cp_list) {
		
		System.out.print("▷ 업종명 : ");
		String job_type_name = sc.nextLine().toLowerCase();
		// "it"
		// "   it"
		// "it   "
		// "  it  "
		// " i  t  "
		
		StringBuilder sb = new StringBuilder();
		
		boolean is_existence = false;
		
		for(int i=0; i<cp_list.size(); i++) {
			if(cp_list.get(i).getJob_type().toLowerCase().contains(String.join("", job_type_name.split("\\ ")))) { // "it"
				is_existence = true;
				sb.append(cp_list.get(i).getInfo()+"\n");
			}
		}// end of for--------------------
		
		if(is_existence) {
			title_company();
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> 검색하시려는 "+job_type_name+"업종에 해당하는 회사는 없습니다.!!\n");
		}
		
	}// end of private void search_jobtype_company(Scanner sc, List<Company_imple> cp_list)---------
	
	// 자본금검색
	private void search_seedmoney_company(Scanner sc, List<Company_imple> cp_list) {
		
		System.out.print("▷ 자본금 : ");
		String str_search_seed_money = sc.nextLine(); 	// "5000000000"			==> OK
														// "5,000,000,000"		==> OK
														// "   5,000,000,000  "	==> OK
														// "50억"	==> NO
														// ""		==> NO
														// "     "	==> NO
		
	//	System.out.println("확인용 : "+String.join("", str_search_seed_money.split("[, ]")));
		
		try {
			long search_seed_money = Long.parseLong(String.join("", str_search_seed_money.split("[, ]")));

			StringBuilder sb = new StringBuilder();
			boolean is_existence = false;
			
			for(int i=0; i<cp_list.size(); i++) {
				if(cp_list.get(i).getSeed_money()>=search_seed_money) {
					is_existence = true;
					sb.append(cp_list.get(i).getInfo()+"\n");
				}
			}// end of for--------------------
			
			if(is_existence) {
				title_company();
				System.out.println(sb.toString());
			}
			else {
				DecimalFormat fmt = new DecimalFormat("#,###");
				System.out.println(">> 검색하시려는 자본금이 "+fmt.format(search_seed_money)+"원 이상인 회사는 없습니다.!!\n");
			}
			
		} catch(NumberFormatException e) {
			System.out.println(">> [경고] 자본금은 정수로만 입력하세요!! <<\n");
		}
		
	}// end of private void search_seedmoney_company(Scanner sc, List<Company_imple> cp_list)--------

	// == 모든 채용공고 조회(현재 채용이 진행중인 것만 조회, 채용마감일자가 오늘보다 이전인 것은 보여주면 안된다.) ==
	private void view_all_recruit_info(List<Recruit_imple> rc_list) {
		
		if(rc_list.size() == 0) {
			System.out.println(">> 채용공고가 1개도 없습니다. <<\n");
		}
		else {

			StringBuilder sb = new StringBuilder();
			
			Date now = new Date(); // 현재시각
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			boolean is_existence = false;
			
			for(int i=0; i<rc_list.size(); i++) {
					
				try {
					Date finish_day = sdf.parse(rc_list.get(i).getFinish_day());
					if(finish_day.after(now)) {
						is_existence = true;
						sb.append(
								rc_list.get(i).getRecruit_no() + "\t" + 
								rc_list.get(i).getCp().getName() + "\t" +
								rc_list.get(i).getCp().getJob_type() + "\t" +
								new DecimalFormat("#,###").format(rc_list.get(i).getCp().getSeed_money()) + "원\t" +
								rc_list.get(i).getWork_type() + "\t" +
								rc_list.get(i).getCnt() + "\t" +
								rc_list.get(i).getRegister_day().substring(0,4)+"-"+rc_list.get(i).getRegister_day().substring(4,6)+"-"+rc_list.get(i).getRegister_day().substring(6) + "\t" +
								rc_list.get(i).getFinish_day().substring(0,4)+"-"+rc_list.get(i).getFinish_day().substring(4,6)+"-"+rc_list.get(i).getFinish_day().substring(6) + "\n"
								);
					}
				} catch (ParseException e) { }
					
			}// end of for---------------------
			if(is_existence) {

				System.out.println("-".repeat(90));
				System.out.println("채용공고순번      회사명   회사직종타입  자본금   채용분야(근무형태)  채용인원  등록일자  채용마감일자");
				System.out.println("-".repeat(90));
				System.out.println(sb.toString());
				System.out.println("[채용공고가 "+rc_list.size()+"건이 있습니다]\n");
			}
			else {
				System.out.println(">> 채용공고가 1개도 없습니다. <<\n");
			}
		}
		
	}// end of private void view_all_recruit_info(List<Recruit_imple> rc_list)-----------

	// 채용공고 상세보기
	private void view_detail_one_recruit_info(Scanner sc, List<Recruit_imple> rc_list) {

		if(rc_list.size() == 0) {
			System.out.println(">> 채용공고가 1개도 없습니다. <<\n");
			return;
		}
		else {
			//view_all_recruit_info(rc_list);
			
			System.out.print("▷ 채용공고번호 : ");
			String str_recruit_no = sc.nextLine();
			
			Date now = new Date(); // 현재시각
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
			Recruit_imple rc = null;
			
			for(int i=0; i<rc_list.size(); i++) {
				try {
					Date finish_day = sdf.parse(rc_list.get(i).getFinish_day());
					if(finish_day.after(now)
							&& str_recruit_no.equals(String.valueOf(rc_list.get(i).getRecruit_no()))
							) {
						rc = rc_list.get(i);
						break;
					}
				} catch(ParseException e) {}
			}// end of for---------------------
			if(rc != null) {
				System.out.println(rc.recruit_info());
			}
			else {
				System.out.println(">> 조회하신 채용번호 "+str_recruit_no+"은 존재하지 않습니다. <<\n");
			}
		}
	}// end of private void view_detail_one_recruit_info(Scanner sc, List<Recruit_imple> rc_list)---------------------

	// 채용 응모하기
	private void input_recruitApply(Scanner sc, Gujikja_imple login_gu, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {

		// == 채용공고번호는 채용공고로 올라온 번호만 입력해야 한다. == //
		System.out.print("▷ 채용공고번호 : ");
		String input_recruit_no = sc.nextLine(); // "1001" "1002" "1003"	==> 정상
												 // "923424"  "강아지"  		==> 비정상
		
		Recruit_imple rc = null;
		boolean is_existence = false;
		
		for(int i=0; i<rc_list.size(); i++) {
			if(input_recruit_no.equals(String.valueOf(rc_list.get(i).getRecruit_no()))) {
				is_existence = true;
				rc = rc_list.get(i);
				break;
			}
		}// end of for-----------------
		
		if(!is_existence) {
			System.out.println(">> 입력하신 "+input_recruit_no+"번은 채용공고에 존재하지 않습니다.!!\n");
			return; // 메소드 종료
		}
		
		// == 채용공고번호는 채용공고로 올라온 번호이지만 이미 응모한 채용공고번호이라면 입력하면 안된다.
		boolean is_already_apply = false;
		for(int i=0; i<rcapply_list.size(); i++) {
			if(input_recruit_no.equals(String.valueOf(rcapply_list.get(i).getRc().getRecruit_no()))) {
				if(login_gu.getId().equals(rcapply_list.get(i).getGu().getId())) {
					is_already_apply = true;
					break;
				}
			}
		}// end of for----------------------
		if(is_already_apply) {
			System.out.println(">> 입력하신 채용공고번호 "+ input_recruit_no +" 번은 이미 응모하신 번호입니다.");
			return; // 메소드 종료
		}
		
		RecruitApply rcapply = new RecruitApply();
		
		do {
			System.out.print("▷ 지원동기 : ");
			rcapply.setApply_motive(sc.nextLine());
		} while (!(rcapply.getApply_motive() != null));
		
		rcapply.setRc(rc);
		rcapply.setGu(login_gu);
		
		rcapply_list.add(rcapply);
		
	}// end of private void input_recruitApply(Scanner sc, Gujikja_imple login_gu, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)-------

	// 채용 응모한 것 조회
	private void view_my_recruitApply(Gujikja_imple login_gu, List<RecruitApply> rcapply_list) {

		boolean is_existence = false;
		
		for(int i=0; i<rcapply_list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				if(login_gu.getId().equals(rcapply_list.get(i).getGu().getId())
					&& sdf.parse(rcapply_list.get(i).getRc().getFinish_day()).after(new Date())
				) {
					is_existence = true;
					System.out.print(rcapply_list.get(i).getRc().recruit_info());
					System.out.println("▣ 지원동기 : " + rcapply_list.get(i).getApply_motive());
					
					String register_day = rcapply_list.get(i).getRegister_day();
					System.out.println("▣ 지원일자 : " + register_day.substring(0,4) + 
										"-" + register_day.substring(4,6) + 
										"-" + register_day.substring(6)+"\n");
				}
			} catch (ParseException e) { }
		}// end of for-----------------------
		
		if(!is_existence) {
			System.out.println(">> 현재 모집이 진행중인 채용공고에 응모한 내역이 없습니다. <<\n");
		}
		
	}// end of private void view_my_recruitApply(Gujikja_imple login_gu, List<RecruitApply> rcapply_list)-------

	// 채용 응모한 것 수정하기
	private void update_my_recruitApply(Scanner sc, Gujikja_imple login_gu, List<RecruitApply> rcapply_list) {
		
		view_my_recruitApply(login_gu, rcapply_list);
		
		System.out.print("▷ 수정해야 할 채용번호 : ");
		String str_recruit_no = sc.nextLine(); // 1232
		
		RecruitApply my_rc_apply = null;
		
		
		for(int i=0; i<rcapply_list.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			try {
				if(login_gu.getId().equals(rcapply_list.get(i).getGu().getId())
					&& sdf.parse(rcapply_list.get(i).getRc().getFinish_day()).after(new Date())
					&& str_recruit_no.equals(String.valueOf(rcapply_list.get(i).getRc().getRecruit_no()))
				) {
					my_rc_apply = rcapply_list.get(i);
					break;
				}
			} catch (ParseException e) { }
		}// end of for-----------------------
		
		if(my_rc_apply == null) {
			System.out.println(">> 입력하신 채용번호 "+str_recruit_no+" 은 응시내역에 존재하지 않습니다.\n");
			return;
		}
		
		System.out.print("▷ 지원동기 내용 : ");
		String apply_motive = sc.nextLine();
		
		if(apply_motive.isBlank()) {
	        System.out.println("[경고] 입사지원동기는 필수로 입력해야 합니다.!!\n");
	        return;
		}
		
		my_rc_apply.setApply_motive(apply_motive);
		
		System.out.println(">> 입사지원서 수정 완료!! <<\n");
		
	}// end of private void update_my_recruitApply(Scanner sc, Gujikja_imple login_gu, List<RecruitApply> rcapply_list)-------

	
}
