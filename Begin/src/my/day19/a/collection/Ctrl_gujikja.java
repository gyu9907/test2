package my.day19.a.collection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public interface Ctrl_gujikja {

	// == 구직자 회원가입 ==
	// 구직자(Gujikja) 신규 회원가입시 
	// Gujikja 클래스의 field 의 요구사항에 모두 맞으면
	// List<Gujikja_imple> gu_list 와 Map<String, Gujikja_imple> gu_map 에 저장시켜주는 메소드 생성하기
	void register(Scanner sc, List<Gujikja_imple> gu_list, Map<String, Gujikja_imple> gu_map);
	
	/**
	 * 구직자 로그인 메소드
	 * @param sc
	 * @param gu_map 구직자목록 해시맵
	 * @return 로그인 한 구직자
	 */
	Gujikja_imple login(Scanner sc, Map<String ,Gujikja_imple> gu_map);
	
	/**
	 * 구직자 모두보기
	 * @param cmbr_arr 구직자와 구인회사 모두 존재하는 배열
	 */
	void view_all_info(List<Gujikja_imple> gu_list);
	
	/**
	 * 구직자 검색하기
	 * @param sc 검색 입력받기 위한 스캐너
	 * @param cmbr_arr 구직자와 구인회사 모두 존재하는 배열
	 */
	void search_menu(Scanner sc, List<Gujikja_imple> gu_list);
	
	// === 구직자 전용메뉴 메소드 생성하기 === //
	void gu_menu(Scanner sc, Gujikja_imple login_gu, List<Company_imple> cp_list, List<Recruit_imple> rc_list, List<RecruitApply> rcapply_list);
	
}
