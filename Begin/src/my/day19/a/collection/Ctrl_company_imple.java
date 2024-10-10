package my.day19.a.collection;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Ctrl_company_imple implements Ctrl_company {

	// == 구인회사 회원가입 ==
	// 구인회사(Company) 신규 회원가입시 
	// Company 클래스의 field 의 요구사항에 모두 맞으면
	// List<Company_imple> cp_list 와 Map<String, Company_imple> cp_map 에 저장시켜주는 메소드 생성하기
	@Override
	public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> cp_map) {
		Company_imple cp = new Company_imple();
		
		// 아이디는 필수 입력사항이면서 아이디 조건에 맞을때 까지 반복해야 한다.
		String id = null;
		outer:
		do {
			System.out.print("1.아이디 : ");
			id = sc.nextLine(); // "eomjh"  "leess"  "chaew" 현재 사용중인 아이디 이므로 입력불가!!
			                               // "" 또는 "        " 공백만으로는 입력불가!!
			
			// == 가입된 회원들에 대해 중복아이디 검사하기 == //
			if(cp_map.get(id)!=null) {
				System.out.println(">> 이미 사용중인 아이디 이므로 다른 아이디값을 입력하세요!!\n");
				continue outer;
			}
			// === 중복 아이디 검사하기 끝 === //
			
			cp.setId(id);
			
		} while(!(cp.getId()!=null));
		
		
		// 비밀번호는 필수 입력사항이면서 비밀번호 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("2.비밀번호 : "); 
			String passwd = sc.nextLine(); // "Qw12$"  "Qwer1234sdfsdfdsfsfsdf$"  "qwer1234$"  "qWer1234$"
			
			cp.setPasswd(passwd);
			
		} while(!(cp.getPasswd()!=null));
		// end of do~while-----------------
		
		// 회사명은 필수 입력사항이므로 그냥 엔터나 공백만으로 된 것이 아닐때 까지 반복해야 한다.
        // 회사명은 2글자 이상 6글자 이하의 한글로만 되어져야 한다.
		do {
			System.out.print("3.회사명 : "); 
			String name = sc.nextLine();
			
			cp.setName(name);
			
		} while(!(cp.getName() != null));
		// end of do~while-----------------
		
		// 사업자등록번호는 필수 입력사항이면서 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("4.사업자등록번호 : "); 
			String business_number = sc.nextLine();
			
			cp.setBusiness_number(business_number);
			
		} while(!(cp.getBusiness_number() != null));
		// end of do~while-----------------
		
		// 회사직종타입은 필수 입력사항이면서 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("5.회사직종타입 : "); 
			String job_type = sc.nextLine();
			
			cp.setJob_type(job_type);
			
		} while(!(cp.getJob_type() != null));
		// end of do~while-----------------
		
		// 자본금은 필수 입력사항이면서 조건에 맞을 때 까지 반복해야 한다.
		do {
			System.out.print("6.자본금 : ");
			long seed_money = 0;
			try {
				seed_money = Long.parseLong(sc.nextLine());
			} catch(NumberFormatException e) {
				System.out.println("[경고] 자본금은 숫자로만 입력하세요!!\n");
				continue;
			}
			cp.setSeed_money(seed_money);
		} while(!(cp.getSeed_money() > 0));
		// end of do~while-----------------

		cp_list.add(cp);
		cp_map.put(id, cp);
		
		System.out.println(">> 구인회사 회원가입 성공 !! <<\n");
	
	}// end of public void register(Scanner sc, List<Company_imple> cp_list, Map<String, Company_imple> cp_map)---------------------

	// == 구인회사 로그인 메소드 생성하기 == //
	@Override
	public Company_imple login(Scanner sc, Map<String ,Company_imple> cp_map) {
		System.out.print("ID : ");
		String id = sc.nextLine();

		System.out.print("비밀번호 : ");
		String passwd = sc.nextLine();
		
		if(cp_map.get(id)!=null && passwd.equals(cp_map.get(id).getPasswd())) {
			return cp_map.get(id);
		}
		
		return null;
	}// end of public Company_imple login(Scanner sc, Map<String ,Company_imple> cp_map)--------------

	// 구인회사 전용 메뉴
	@Override
	public void cp_menu(Scanner sc, Company_imple login_cp, List<Gujikja_imple> gu_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list) {
		
		String str_menuno;
		
		do {
			System.out.println("\n === 구인회사 전용메뉴(" + login_cp.getName() + "기업 로그인 중) ===\n"
							 + "1.우리회사 정보 보기   2.우리회사 정보 수정   3.모든 구직자 조회   4.구직자 검색하기\n"
							 + "5.채용공고 입력하기   6.우리회사 채용공고 조회   7.우리회사 채용공고 지원자 조회   8.로그아웃\n");
			System.out.print("▷ 메뉴번호 선택 : ");
			str_menuno = sc.nextLine();
			
			switch (str_menuno) {
				case "1": // 우리회사 정보 보기
					view_myInfo(login_cp);
					break;
	
				case "2": // 우리회사 정보 수정
					update_myInfo(sc, login_cp);
					break;
					
				case "3": // 모든 구직자 조회
					view_all_gujikja_info(gu_list);
					break;
					
				case "4": // 구직자 검색하기
					search_gujikja(sc, gu_list);
					break;
					
				case "5": // 채용공고 입력하기
					register_recruit(sc, login_cp, rc_list);
					break;
					
				case "6": // 우리회사 채용공고 조회
					view_recruit_mycompany(login_cp, rc_list);
					break;
					
				case "7": // 우리회사 채용공고 지원자 조회
					view_gujikja_my_recruitApply(login_cp, rcapply_list);
					break;
					
				case "8": // 로그아웃
					
					break;
					
				default:
					System.out.println(">> [경고] 선택하신 번호는 메뉴에 없습니다. <<\n");
					break;
			}// end of switch(str_menuno)-----------------
		} while(!"8".equals(str_menuno));
		// end of do~while------------------------
		
		System.out.println(">> 로그아웃 되었습니다. <<\n");
		
	}// end of public void cp_menu(Scanner sc, Company_imple login_cp, List<Gujikja_imple> gu_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list)------------

	private void view_myInfo(Company_imple login_cp) {
		/*
        
        >>> 삼성 기업의 정보 <<<
        ----------------------------------------------------------------------------------
          아이디     비밀번호      회사명   가입일자      사업자등록번호  직종타입   자본금
        ----------------------------------------------------------------------------------  
          samsung  Abcd1234$   삼성     2024-02-02  8123456789   제조업    8,000,000,000원
		*/
		
		System.out.println(">>> "+login_cp.getName()+" 기업의 정보 <<<\r\n"
				+ "-".repeat(80)+"\n"
				+ "아이디\t비밀번호\t\t회사명\t가입일자\t\t사업자등록번호\t직종타입\t자본금\n"
				+ "-".repeat(80));

		System.out.println(
				login_cp.getId()+"\t"+
				login_cp.getPasswd()+"\t"+
				login_cp.getName()+"\t"+
				login_cp.getRegister_day().substring(0, 10)+"\t"+
				login_cp.getBusiness_number() + "\t"+
				login_cp.getJob_type()+"\t"+
				new DecimalFormat("#,###").format(login_cp.getSeed_money())
				);
		
	}
	
	// 우리회사 정보 수정
	private void update_myInfo(Scanner sc, Company_imple login_cp) {
		
		view_myInfo(login_cp);
		
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
				
				if(new_passwd.equals(login_cp.getPasswd())) { // 입력한 비밀번호가 기존 비밀번호와 같을 경우
					System.out.println(">> 기존의 암호와 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 비밀번호가 기존 비밀번호와 다를 경우
					login_cp.setPasswd(new_passwd);
					
					if(login_cp.getPasswd().equals(new_passwd)) { // 비밀번호 정책에 맞는지 틀리는지
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
			System.out.print("2.회사명 : ");
			String new_name = sc.nextLine();	// 기존회사명인 삼성 을 삼성전자 로 변경하려고 한다.
									            // 기존회사명인 삼성 을 삼성 으로 변경하려고 하려면 기존회사명과 동일하므로 변경이 불가합니다. 
									            // 기존회사명인 삼성 을 변경하기 싫다. 
									            // 기존회사명인 삼성 을 삼sung 으로 변경하고자 할때는 회사명정책에 맞지 않으므로 안된다.!!
			
			if(!new_name.equals("")) { // 입력한 회사명이 엔터가 아닐 경우
				
				if(new_name.equals(login_cp.getName())) { // 입력한 회사명이 기존 회사명과 같을 경우
					System.out.println(">> 기존의 회사명과 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 회사명이 기존 회사명과 다를 경우
					login_cp.setName(new_name);
					
					if(login_cp.getName().equals(new_name)) { // 회사명 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 회사명이 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("3.사업자등록번호 : ");
			String new_business_number = sc.nextLine();
			
			if(!new_business_number.equals("")) { // 입력한 사업자등록번호가 엔터가 아닐 경우
				
				if(new_business_number.equals(login_cp.getBusiness_number())) { // 입력한 사업자등록번호가 기존 사업자등록번호와 같을 경우
					System.out.println(">> 기존의 사업자등록번호와 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 사업자등록번호가 기존 사업자등록번호와 다를 경우
					login_cp.setBusiness_number(new_business_number);
					
					if(login_cp.getBusiness_number().equals(new_business_number)) { // 사업자등록번호 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 사업자등록번호가 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("4.회사직종타입 : ");
			String new_job_type = sc.nextLine();
			
			if(!new_job_type.equals("")) { // 입력한 회사직종타입이 엔터가 아닐 경우
				
				if(new_job_type.equals(login_cp.getJob_type())) { // 입력한 회사직종타입이 기존 회사직종타입과 같을 경우
					System.out.println(">> 기존의 직종타입과 동일하므로 변경이 불가합니다.!!\n");
				}
				else { // 입력한 회사직종타입이 기존 회사직종타입과 다를 경우
					login_cp.setJob_type(new_job_type);
					
					if(login_cp.getJob_type().equals(new_job_type)) { // 회사직종타입 정책에 맞는지 틀리는지
						exit_ok = true;
					}
				}
			}
			else { // 입력한 회사직종타입이 엔터일 경우
				exit_ok = true;
			}
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		exit_ok = false;
		
		do {
			////////////////////////////////////////////////////////////////
			System.out.print("5.자본금 : ");
			String str_new_seed_money = sc.nextLine();
			if(!str_new_seed_money.equals("")) { // 입력한 자본금이 엔터가 아닐 경우
				try {
					long new_seed_money = Long.parseLong(String.join("", str_new_seed_money.split("[, ]")));
					
					if(login_cp.getSeed_money() == new_seed_money) { // 입력한 자본금이 기존 자본금과 같을 경우
						System.out.println(">> 기존의 자본금과 동일하므로 변경이 불가합니다.!!\n");
					}
					else { // 입력한 자본금이 기존 자본금과 다를 경우
						login_cp.setSeed_money(new_seed_money);
						
						if(login_cp.getSeed_money() == new_seed_money) { // 자본금 정책에 맞는지 틀리는지
							exit_ok = true;
						}
					}
					
				} catch(NumberFormatException e) {
					System.out.println(">> [경고] 자본금은 정수로만 입력하셔야 합니다!! <<\n");
				}
				
			}
			else { // 입력한 자본금이 엔터일 경우
				exit_ok = true;
			}
			
			////////////////////////////////////////////////////////////////
		}while(!exit_ok);
		// end of do~while--------------------------
		
		
	}// end of private void update_myInfo(Scanner sc, Company login_cp)--------
	
	// 모든 구직자 조회
	private void view_all_gujikja_info(List<Gujikja_imple> gu_list) {
		
		/*
			----------------------------------------------------------------------
			  아이디    비밀번호      성명    생년월일    성별    현재나이    가입일자
			----------------------------------------------------------------------
			eomjh	Qwe******	엄정화	1986-10-20	여	37	2024-09-02 17:26:33
			leess	Abc*******	이순신	1986-01-20	남	38	2024-09-02 17:26:33
			chaew	Wxy******	차은우	2001-06-20	남	23	2024-09-02 17:26:33
		*/
		
		if(gu_list.size() == 0) {
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else {
			title();
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<gu_list.size(); i++) {
				sb.append(gu_list.get(i).getInfo()+"\n");
			}// end of for--------------

			System.out.println(sb.toString());
			
		}
		
	}// end of private void view_all_gujikja_info(CommonMember[] cmbr_arr)----------

	private void title() {
		System.out.println("-".repeat(70)+"\n"
				+ "  아이디    비밀번호      성명    생년월일    성별    현재나이    가입일자\n"
				+ "-".repeat(70));
	}

	// 구직자 검색하기
	private void search_gujikja(Scanner sc, List<Gujikja_imple> gu_list) {
		String str_menuno;
		do {
			//////////////////////////////////////////////////////
			System.out.println(">>> 구직자 검색메뉴 <<<\n"
	                + "1.성별검색    2.연령대검색    3.구직자 연령대 및 성별검색    4.구인회사 전용메뉴로 돌아가기"); 
			System.out.print("▷ 검색메뉴번호 입력 : ");
			str_menuno = sc.nextLine();
			switch (str_menuno) {
				case "1": // 성별검색
					search_gender_gujikja(sc, gu_list);
					break;
	
				case "2": // 연령대검색
					search_ageline_gujikja(sc, gu_list);
					break;
	
				case "3": // 구직자 연령대 및 성별검색
					search_ageline_gender_gujikja(sc, gu_list);
					break;
	
				case "4": // 구인회사 전용메뉴로 돌아가기
					
					break;
	
				default:
					System.out.println(">> [경고] 구직자 검색메뉴에 없는 번호입니다. <<\n");
					break;
			}// end of switch (str_menuno)------------------
			//////////////////////////////////////////////////////
		} while(!"4".equals(str_menuno));
		// end of do~while------------------------
	}// end of private void search_gujikja(Scanner sc, List<Gujikja_imple> gu_list)-----

	// == 구직자 성별검색 ==
	private void search_gender_gujikja(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else { // 구직자가 있는 경우
			
			boolean isUse_input_gender = false;
			String input_gender;
			do {
				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
				input_gender = sc.nextLine().trim();	// "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
	            											// ""  "      " "강아지" --> 비정상
				
				switch (input_gender) {
				case "남":
				case "여":
					isUse_input_gender = true;
					break;
	
				default:
					System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
					break;
				}// end of switch (input_gender.trim())---------------
			} while(!isUse_input_gender);
			// end of do~while
			
			// == 입력받은 성별에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			for(int i=0; i<gu_list.size(); i++) {
				if(gu_list.get(i).gender().equals(input_gender)) {
					isFind = true;
					sb.append(gu_list.get(i).getInfo()+"\n");
				}
			}// end of for-----------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 성별 "+input_gender+"자 구직자는 없습니다]\n");
			}
		}
		
	}// end of private void search_gender_gujikja(Scanner sc, List<Gujikja_imple> gu_list)------

	// == 연령대검색 ==
	private void search_ageline_gujikja(Scanner sc, List<Gujikja_imple> gu_list) {
		
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
		}
		else { // 구직자가 있는 경우
			boolean isUse_ageLine = false;
			String input_ageline;
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				input_ageline = String.join("", sc.nextLine().split(" "));	// "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
                															// "25" "강아지" "-20" --> 비정상
				
				switch (input_ageline) {
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
				}// end of switch (input_ageline)--------
			} while(!isUse_ageLine);
			// end of do~while-------------
			
			StringBuilder sb = new StringBuilder();
			boolean isFind = false;
			
			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			for(int i=0; i<gu_list.size(); i++) {
				if(gu_list.get(i).age()/10*10 == Integer.parseInt(input_ageline)) {
					isFind = true;
					sb.append(gu_list.get(i).getInfo()+"\n");
				}
			}
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 연령대가 "+input_ageline+"대인 구직자는 없습니다]\n");
			}
			
		}
		
	}// end of private void search_ageline_gujikja(Scanner sc, List<Gujikja_imple> gu_list)-------

	// == 구직자 연령대 및 성별검색 ==
	private void search_ageline_gender_gujikja(Scanner sc, List<Gujikja_imple> gu_list) {
		if(gu_list.size() == 0) { // 구직자가 없는 경우
			System.out.println(">> 구직자로 가입된 회원이 아무도 없습니다. <<\n");
			return;
		}
		else { // 구직자가 있는 경우
			boolean isUse_ageLine = false;
			String input_ageline;
			do {
				System.out.print("▷ 검색하고자 하는 연령대[예: 20] => ");
				input_ageline = String.join("", sc.nextLine().split(" "));	// "0" "10" "20" "30" "40" "50" "60" "70" "80" --> 정상
                															// "25" "강아지" "-20" --> 비정상
				
				switch (input_ageline) {
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
				}// end of switch (input_ageline)--------
			} while(!isUse_ageLine);
			// end of do~while-------------
			
			boolean isFind = false;

			// == 입력받은 연령대에 해당하는 구직자 찾기 == //
			for(int i=0; i<gu_list.size(); i++) {
				if(gu_list.get(i).age()/10*10 == Integer.parseInt(input_ageline)) {
					isFind = true;
					break;
				}
			}// end of for-----------
			
			if(!isFind) {
				System.out.println("[검색결과 연령대가 "+input_ageline+"대인 구직자는 없습니다]\n");
				return;
			}
			
			boolean isUse_input_gender = false;
			String input_gender;
			do {
				System.out.print("▷ 검색하고자 하는 성별[남/여] => ");
				input_gender = sc.nextLine().trim();	// "남" "여" "   남" "여   " "    남   " "    여   " --> 정상
	            											// ""  "      " "강아지" --> 비정상
				
				switch (input_gender) {
				case "남":
				case "여":
					isUse_input_gender = true;
					break;
	
				default:
					System.out.println("[경고] \"남\" 또는 \"여\" 만 입력하세요!!\n");
					break;
				}// end of switch (input_gender.trim())---------------
			} while(!isUse_input_gender);
			// end of do~while
			
			/////////// == 성별 검색 시작 == ////////////

			// == 입력받은 성별에 해당하는 구직자 찾기 == //
			StringBuilder sb = new StringBuilder();
			isFind = false;
			
			for(int i=0; i<gu_list.size(); i++) {
				if(gu_list.get(i).age()/10*10 == Integer.parseInt(input_ageline)
						&& gu_list.get(i).gender().equals(input_gender)) {
					isFind = true;
					sb.append(gu_list.get(i).getInfo()+"\n");
				}
			}// end of for-----------------
			
			if(isFind) {
				title();
				System.out.println(sb.toString());
			}
			else {
				System.out.println("[검색결과 연령대가 "+input_ageline+"대인 "+input_gender+"자 구직자는 없습니다]\n");
			}
		}
	}// end of private void search_ageline_gender_gujikja(Scanner sc, List<Gujikja_imple> gu_list)-----
	
	// == 채용공고 입력하기 ==
	private void register_recruit(Scanner sc, Company_imple login_cp, List<Recruit_imple> rc_list) {
		
		System.out.println("====== "+login_cp.getName()+" 채용공고 등록 ======");
		
		Recruit_imple rc = new Recruit_imple();
		
		do {
			System.out.print("1.채용제목 : ");
			rc.setSubject(sc.nextLine());
		} while(!(rc.getSubject() != null));
		
		do {
			System.out.print("2.채용분야[예: 정규사무직/계약직] : ");
			rc.setWork_type(sc.nextLine());
		} while(!(rc.getWork_type() != null));

		do {
			System.out.print("3.채용인원 : ");
			try {
				rc.setCnt(Integer.parseInt(sc.nextLine()));
			} catch(NumberFormatException e) {
				System.out.println("[경고] 정수로만 입력하세요!!\n");
			}
		} while(!(rc.getCnt() > 0));

		do {
			System.out.print("4.연봉 : "); //  5000  또는 5,000
			try {
				rc.setYearpay(Integer.parseInt(String.join("",sc.nextLine().split("[, ]"))));
			} catch(NumberFormatException e) {
				System.out.println("[경고] 정수로만 입력하세요!!\n");
			}
		} while(!(rc.getYearpay() > 0));
		
		do {
			System.out.print("5.채용마감일자[예: 20240910] : ");
			rc.setFinish_day(sc.nextLine());
		} while(!(rc.getFinish_day() != null));
		
		rc.setCp(login_cp);
		
		rc_list.add(rc);
		
		System.out.println("\n채용공고 등록 완료!!\n");
		
	}// end of private void register_recruit(Scanner sc, Company login_cp, List<Recruit_imple> rc_list)--------

	// == 우리회사 채용공고 조회(채용마감일자가 지나지 않은 것만 조회한다.) ==
	private void view_recruit_mycompany(Company_imple login_cp, List<Recruit_imple> rc_list) {
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		for(int i=0; i<rc_list.size(); i++) {
			if(login_cp.getId().equals(rc_list.get(i).getCp().getId())) {
				
				try {
					Date finish_day = sdf.parse(rc_list.get(i).getFinish_day());
					if(finish_day.after(now)) {
						System.out.println(rc_list.get(i).recruit_info());
					}
				} catch (ParseException e) { }
				
			}
		}
	}// end of private void view_recruit_mycompany(Company login_cp, List<Recruit_imple> rc_list)------------

	// 우리회사 채용공고 지원자 조회
	private void view_gujikja_my_recruitApply(Company_imple login_cp, List<RecruitApply> rcapply_list) {
		
		StringBuilder sb = new StringBuilder();
		
		boolean is_existence = false;
		
		for(int i=0; i<rcapply_list.size(); i++) {
			
			if(login_cp.getId().equals(rcapply_list.get(i).getRc().getCp().getId())) {
				is_existence = true;
				sb.append(
						rcapply_list.get(i).getRc().getRecruit_no() + "\t" +
						rcapply_list.get(i).getRc().getSubject() + "\t" +
						rcapply_list.get(i).getGu().getName() + "\t" +
						rcapply_list.get(i).getGu().gender() + "\t" +
						rcapply_list.get(i).getGu().age() + "\t" +
						rcapply_list.get(i).getApply_motive() + "\n"
						);
			}
			
		}// end of for-------------------
		
		if(is_existence) {
			System.out.println("=".repeat(100));
			System.out.println("채용공고번호\t채용제목\t\t지원자명\t성별\t나이\t지원동기");
			System.out.println("=".repeat(100));
			System.out.println(sb.toString());
		}
		else {
			System.out.println(">> 채용공고에 지원자가 없습니다. <<\n");
		}
		
		
	}// end of private void view_gujikja_my_recruitApply(Company login_cp, List<RecruitApply> rcapply_list)-----
}
