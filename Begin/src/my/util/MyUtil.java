package my.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MyUtil {
	
	// === 현재시각을 출력해주는 static 메소드를 생성한다. === //
	public static String current_time() {
		
		Date now = new Date(); // 현재시각
		SimpleDateFormat sdfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdfmt.format(now);
	}
	
	// === 소문자 3개와 숫자 4개와 대문자 3개로 이루어진 랜덤한 인증키 만들기 === //
	// 예:  zac5652BPB
	
	public static String certification_key() {
		
		String result = "";
		Random random = new Random();
		
		// 처음얼마부터 마지막얼마까지 랜덤한 정수
	    // ==> rndom.nextInt(마지막수 - 처음수 + 1) + 처음수;
		
		// 소문자 3개
		for(int i=0; i<3; i++) {
			result += (char)(random.nextInt('z'-'a'+1)+'a');
		}// end of for---------------
		
		// 숫자 4개
		for(int i=0; i<4; i++) {
			result += random.nextInt(9 - 0 + 1) + 0;
		}// end of for---------------
		
		// 대문자 3개
		for(int i=0; i<3; i++) {
			result += (char)(random.nextInt('Z'-'A'+1)+'A');
		}// end of for---------------
		
		
		return result;
	}
	
	public static String certification_key2() {
		
		String result = "";
		Random random = new Random();
		
		// 처음얼마부터 마지막얼마까지 랜덤한 정수
	    // ==> rndom.nextInt(마지막수 - 처음수 + 1) + 처음수;
		
		
		for(int i=0;i<10;i++) {
			int choice = (char)(random.nextInt(3-1+1)+1);
			/*
			switch(choice) {
			case 1:
				result += (char)(random.nextInt('z'-'a'+1)+'a');
				break;
			case 2:
				result += random.nextInt(9 - 0 + 1) + 0;
				break;
			case 3:
				result += (char)(random.nextInt('Z'-'A'+1)+'A');
				break;
			default:
				break;
			}*/
			if(choice == 1) {
				result += (char)(random.nextInt('z'-'a'+1)+'a');
			}
			else if(choice == 2) {
				result += random.nextInt(9 - 0 + 1) + 0;
			}
			else {
				result += (char)(random.nextInt('Z'-'A'+1)+'A');
			}
			
		}
		
		return result;
	}
	
	public static String spaceDelete_for(String str) {
		
		String result = "";
		
		if(str!=null) {
			for(int i=0; i<str.length(); i++) {
				char ch = str.charAt(i);
				if(' ' != ch) {
					result += ch;
				}
			}// end of for-------------------------
		}
		
		return result;
	}

	public static String spaceDelete_while(String str) {
		
		String result = "";
		
		// 선생님 답안
		int i = 0;
		while(str!=null && !"".equals(str) && i < str.length()) {
			if(str.charAt(i) != ' ') {
				result +=str.charAt(i);
			}
			
			i++;
		}// end of while----------------------------
		
		return result;
		
		/* 내 답안
		int cnt = 0;
		
		while(cnt<str.length()) {
			char ch = str.charAt(cnt);
			if(' ' != ch) {
				result += ch;
			}
			cnt++;
		}// end of while--------------------------
		
		return result;
		*/
	}
	

	public static String spaceDelete_dowhile(String str) {

		// 선생님 답안
		String result = "";
		int i = 0;

		if(str!=null && !"".equals(str)) {
			do {
				if(str.charAt(i) != ' ') {
					result += str.charAt(i);
				}
				
				i++;
			} while (!(i == str.length()));
		}// end of do~while---------------------
		
		return result;
		
		/* 내 답안
		String result = "";
		int cnt = 0;
		
		if(str.length()==0)
			return result;
		
		do {
			char ch = str.charAt(cnt);
			if(' ' != ch) {
				result += ch;
			}
			cnt++;
		} while(cnt<str.length());
		
		return result;
		*/
	}

	// == 비밀번호가 규칙에 맞는지 틀리는지 알려주는 static 메소드 생성하기 == //
    /*
      비밀번호 규칙은 비밀번호의 길이는 8글자 이상 15글자 이하이어야 하고,
      또한 비밀번호는 영문대문자, 영문소문자, 숫자, 특수기호가 혼합되어야만 한다.
      우리는 규칙에 맞으면 true 를 리턴해주고, 규칙에 틀리면 false 를 리턴하도록 만든다.  
    */
	public static boolean isCheckPasswd(String passwd) {
		
		// passwd  ==>  null
		// passwd  ==>  Ab3$
		if(passwd == null)
			return false;
		
		// 예를 들어서
		// 입력받은 passwd 가 Ab3$ 이라면
		// 입력받은 passwd 가 Ab3$sdfasdfsdfsfsdfsdf#% 이라면
		if(passwd.length() < 8 || passwd.length() > 15)
			return false;
		
		// 이제부터는 입력받은 passwd의 글자수가 8글자 이상 15글자 이하인 경우이다.
		// 예를들어서
	    // 입력받은 passwd 가 C5d#하하호호 이라면  C5d#하하s@! 이라면
	    // 또는 
	    // 입력받은 passwd 가 C5dawxab 이라면   c5dawxab# 이라면
	    // 입력받은 passwd 가 C5dawxab@ 이라면  c5dawxab#T 이라면
		
		boolean find_upper = false;    // 영문대문자 발견유무
		boolean find_lower = false;    // 영문소문자 발견유무
		boolean find_digit = false;    // 숫자 발견유무
		boolean find_special = false;  // 특수문자 발견유무
		
		for(int i=0; i<passwd.length(); i++) {
			// 암호에 한글이 들어가 있는지 알아본다.
			char ch = passwd.charAt(i);
			if('가' <= ch && ch <= '힣')
				return false;
			
			if(Character.isUpperCase(ch))      // 영문대문자 발견
				find_upper = true;
			
			else if(Character.isLowerCase(ch)) // 영문소문자 발견
				find_lower = true;
			
			else if(Character.isDigit(ch))     // 숫자 발견
				find_digit = true;
			else                               // 특수문자 발견
				find_special = true;
			
		}// end of for------------------------
		
		return find_upper && find_lower && find_digit && find_special;
	}

	public static long delete_character(String money) {
		
		// money => "$2,000,000"
		// money => "2,000,000$"
		// money => "$2,000,000$"
		// money => "$$2,000,000$"
		// money => "2,$000,$000$"    "2," + "000,$000$"  ==> "2,000,$000$"
		//          "2,000,$000$"     "2,000," + "000$"   ==> "2,000,000$"
		//          "2,000,000$"      "2,000,000" + ""    ==> "2,000,000"
		
		// money => "2,000,000"
		do {
			int dollor_index = money.indexOf("$");
			
			if(dollor_index == -1)
				break;
			
			money = money.substring(0, dollor_index) + money.substring(dollor_index+1);
		} while (true);
		// end of do~while------------------

		do {
			int comma_index = money.indexOf(",");
			
			if(comma_index == -1)
				break;
			
			money = money.substring(0, comma_index) + money.substring(comma_index+1);
		} while (true);
		// end of do~while-----------------
		
	//	System.out.println(money);
	//  "2000000"
		
		return Long.parseLong(money);
		// 2000000
	}

	// === 숫자에 3자리 마다 ,를 추가해서 문자열로 리턴시켜주는 메소드 생성하기 === //
	public static String append_comma(long num) {
		
		// >> 숫자를 문자열로 변경시켜주는 메소드 << //
		/*
		Integer.toString(12345); // ==> "12345"
		Long.toString(1234567890123456789L); // ==> "1234567890123456789"
		
		String.valueOf(12345); // ==> "12345"
		String.valueOf(1234567890123456789L); // ==> "1234567890123456789"
		*/
		
		String temp = String.valueOf(num);
		// num 이 2500000 이라면 temp 는 "2500000"
	    // num 이 892500000 이라면 temp 는 "892500000"
		
		char[] origin_arr = temp.toCharArray();
		/*
	        -----------------------------
	        |'2'|'5'|'0'|'0'|'0'|'0'|'0'|  ==> 길이 7
	        -----------------------------
	        
	        -------------------------------------
	        |'8'|'9'|'2'|'5'|'0'|'0'|'0'|'0'|'0'|  ==> 길이 9
	        -------------------------------------
		*/

		int comma_cnt = (origin_arr.length%3 == 0) ? origin_arr.length/3-1 : origin_arr.length/3;
		char[] result_arr = new char[origin_arr.length+comma_cnt];
		
		for(int i=origin_arr.length-1, j=result_arr.length-1, count=1; i>=0; i--, j--, count++) {
			if(count%4 != 0) {
				result_arr[j] = origin_arr[i];
			}
			else {
				result_arr[j] = ',';
				i++;
			}
		}// end of for-----------------------
		
    //  |'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|
		
		
		/*
	        -------------------------------------
	        |' '|' '|' '|' '|' '|' '|' '|' '|' '|  ==> 길이 7 + 2
	        -------------------------------------
	      
	        -------------------------------------
	        |'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|  ==> 길이 7 + 2
	        -------------------------------------
	        
	        ---------------------------------------------
	        |'8'|'9'|'2'|','|'5'|'0'|'0'|','|'0'|'0'|'0'|  ==> 길이 9 +2
	        ---------------------------------------------
		*/

		return String.valueOf(result_arr);
		//     "2,500,000"
	}

	// === 주민번호 7자리를 입력받아서 올바른 주민번호 인지 검사해주는 메소드 생성 === //
	public static boolean isCheckJubun(String jubun) {
		
		// 숫자로만 이루어지지 않았으므로
		try{
			Integer.parseInt(jubun);
		} catch(NumberFormatException e) {
			return false;
		}
			
		// 입력받는 주민번호 길이는 오로지 7글자가 아니므로
		if(jubun.length()!=7)
			return false;
		
		// 주민번호 마지막 글자는 1 또는 2 또는 3 또는 4
		
		switch (jubun.substring(6)) {
		case "1":
		case "2":
		case "3":
		case "4":
			
			break;

		default:
			return false;
		}
			
		// 달력에 존재하지 않는 날짜이므로
		// "9810321"
		// "0110323"
		String str = ("1".equals(jubun.substring(6)) || "2".equals(jubun.substring(6)))?"19":"20";
		jubun = str + jubun.substring(0, 6);
		// "199810321"
		// "200110323"
		
		/* === 입력한 날짜가 실제로 유효한 날짜 인지 검사하기 === */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		sdf.setLenient(false); // <<<<<<< 꼭 써야한다 !!!
		// false 로 해주어야만 입력한 값을 날짜 타입으로 변경할때 날짜로 되지 못하는 값일 경우 오류가 발생한다.
	    // 날짜로 파싱될 때 허술하게 하지 말고 엄격하게 하라고 설정해주는 것이라고 생각하면 된다.
		
		// === 문자열을 날짜 형태로 변환하기 === //
		
		try {
			sdf.parse(jubun);
		} catch (ParseException e) {
			return false;
		}
		
		return true;
	}

	// === 주민번호 7자리를 입력받아서 만 나이를 구해주는 메소드 생성 === //
	public static int age(String jubun) {
		
		if(!isCheckJubun(jubun)) {
			System.out.println(jubun + "은 올바른 주민번호가 아닙니다.");
			return -1;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int current_year = Integer.parseInt(sdf.format(new Date()));
		
		String str = ("1".equals(jubun.substring(6)) || "2".equals(jubun.substring(6)))?"19":"20";
		int birth_year = Integer.parseInt(str+jubun.substring(0, 2));
		
		// String 을 Date 타입으로 만들기
		int add = 0;
		sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date current_year_birthday = sdf.parse(current_year + jubun.substring(2, 6));
			add = (current_year_birthday.after(new Date()))?-1:0;
		} catch(ParseException e) {}
		
		// age = 현재년도 - 태어난년도 + 0  (올해생일이 현재일과 같거나 이전인 경우)
		// age = 현재년도 - 태어난년도 + -1 (올해생일이 현재일 보다 이후인 경우)
		
		return current_year - birth_year + add;
	}

	public static int yeongum_start_year(String jubun) {
		
		// 내가 1997년생 이라면 ? 년에 만 65세
		// 내가 2000년생 이라면 ? 년에 만 65세
		// ? - 1997 = 65
		// ? - 2000 = 65
		// 그러므로 ? = 65 + 1997
		// 그러므로 ? = 65 + 2000
		
		if(!isCheckJubun(jubun)) {
			System.out.println(jubun + "은 올바른 주민번호가 아닙니다.");
			return -1;
		}
		
		String add = ("1".equals(jubun.substring(6))||"2".equals(jubun.substring(6)))?"19":"20";
		
		return 65 + Integer.parseInt(add + jubun.substring(0,2));
	}

	public static String today() {
		Calendar calendarDate = Calendar.getInstance();
		int year = calendarDate.get(Calendar.YEAR);
		int month = calendarDate.get(Calendar.MONTH)+1;
		int date = calendarDate.get(Calendar.DATE);

		String month_str = (month<10)?"0"+month:String.valueOf(month);
		String date_str = (date<10)?"0"+date:String.valueOf(date);
		
		int hour = calendarDate.get(Calendar.HOUR_OF_DAY);
		int minute = calendarDate.get(Calendar.MINUTE);
		int second = calendarDate.get(Calendar.SECOND);

		String hour_str = (hour<10)?"0"+hour:String.valueOf(hour);
		String minute_str = (minute<10)?"0"+minute:String.valueOf(minute);
		String second_str = (second<10)?"0"+second:String.valueOf(second);

		//Date now = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int day_of_week = calendarDate.get(Calendar.DAY_OF_WEEK);
		String week_str = "";
		switch (day_of_week) {
		case 1:
			week_str = "일";
			break;
		case 2:
			week_str = "월";
			break;
		case 3:
			week_str = "화";
			break;
		case 4:
			week_str = "수";
			break;
		case 5:
			week_str = "목";
			break;
		case 6:
			week_str = "금";
			break;
		case 7:
			week_str = "토";
			break;
		}
		
		// return sdf.format(now)+" "+week_str+"요일";
		return year+"-"+month_str+"-"+date_str+" "+hour_str+":"+minute_str+":"+second_str+" "+week_str+"요일";
	}

	public static String today2() {
		
		Calendar calendarDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		int day_of_week = calendarDate.get(Calendar.DAY_OF_WEEK);
		String week_str = "";
		switch (day_of_week) {
		case 1:
			week_str = "일";
			break;
		case 2:
			week_str = "월";
			break;
		case 3:
			week_str = "화";
			break;
		case 4:
			week_str = "수";
			break;
		case 5:
			week_str = "목";
			break;
		case 6:
			week_str = "금";
			break;
		case 7:
			week_str = "토";
			break;
		}
				
		return sdf.format(calendarDate.getTime())+" "+week_str+"요일";
	}
	
}
