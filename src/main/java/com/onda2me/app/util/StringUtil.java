package com.onda2me.app.util;

import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * 
 * 문자열 관리를 위한 유티릴티 클래스
 * 
 * @author Administrator
 *
 */
public class StringUtil {

	public StringUtil() {
	}


	/**
	 * 문자열을 분리자를 기준으로 분리하여 String 배열로 반환한다.
	 * @param str 문자열
	 * @param dilim 분리자
	 * @return
	 */
	public static String[] split(String str, String dilim) {
		StringTokenizer st = new StringTokenizer(str, dilim);
		String[] list = new String[st.countTokens()];
		int i = 0;
		while (st.hasMoreElements()) {
			list[i] = st.nextToken();
			i++;
		}

		return list;
	}

	/**
	 * 문자열을 구분자를 이용하여 분리하고 분리한 문자열의 일부를 반환한다.
	 *
	 * @param str
	 *            문자열
	 * @param dilim
	 *            분리자
	 * @param num
	 *            인덱스
	 * @return 주어진 문자열을 주어진 분리자로 분리한후 주어진 인덱스에 해당하는 문자열을 반환한다.
	 * 		   만일 주어진 문자열이 null 이거나
	 *         분자열이 분리자를 포함하지 않거나
	 *         주어진 인덱스값이 옳바르지 않은 경우 공백 문자열을 반환한다.
	 */
	public static String splitValue(String str, String dilim, int num) {

		if(str == null){
			return "";
		}

		if(num < 0 ){
			return "";
		}

		String[] values = split(str, dilim);

		if(values.length < num+1){
			return "";
		}

		if (values.length > num) {
			return values[num];
		}else{
			return "";
		}
	}

	/**
	 * 대상문자열(strTarget)에서 지정문자열(strSearch)이 검색된 횟수를,
	 * 지정문자열이 없으면 0 을 반환한다.
	 *
	 * @param strTarget 대상문자열
	 * @param strSearch 검색할 문자열
	 * @return 지정문자열이 검색되었으면 검색된 횟수를, 검색되지 않았으면 0 을 반환한다.
	 */
	public static int search(String strTarget, String strSearch){
		int result=0;
		String strCheck = new String(strTarget);
		for(int i = 0; i < strTarget.length(); ){
			int loc = strCheck.indexOf(strSearch);
			if(loc == -1) {
				break;
			} else {
				result++;
				i = loc + strSearch.length();
				strCheck = strCheck.substring(i);
			}
		}
		return result;
	}
	
	/**
	 * 대상문자열(strTarget)에서 지정문자열(strSearch)의 위치를,
	 * 지정문자열이 없으면 -1 을 반환한다.
	 *
	 * @param strTarget 대상문자열
	 * @param strSearch 검색할 문자열
	 * @return 지정문자열이 검색되었으면 검색된 위치를, 검색되지 않았으면 -1 을 반환한다.
	 */
	public static int searchIndex(String strTarget, String strSearch){

		
		return new String(strTarget).indexOf(strSearch);
	}	

	/**
	 * 대상문자열(strTarget)에서 구분문자열(strDelim)을 기준으로 문자열을 분리하여
	 * 각 분리된 문자열을 배열에 할당하여 반환한다.
	 *
	 * @param strTarget 분리 대상 문자열
	 * @param strDelim 구분시킬 문자열로서 결과 문자열에는 포함되지 않는다.
	 * @param bContainNull 구분되어진 문자열중 공백문자열의 포함여부.
	 *                     true : 포함, false : 포함하지 않음.
	 * @return 분리된 문자열을 순서대로 배열에 격납하여 반환한다.
	 */
	public static String[] split(String strTarget, String strDelim, boolean bContainNull){
		// StringTokenizer는 구분자가 연속으로 중첩되어 있을 경우 공백 문자열을 반환하지 않음.
		// 따라서 아래와 같이 작성함.
		int index = 0;
		String[] resultStrArray = new String[search(strTarget,strDelim)+1];
		String strCheck = new String(strTarget);
		while(strCheck.length() != 0) {
			int begin = strCheck.indexOf(strDelim);
			if(begin == -1) {
				resultStrArray[index] = strCheck;
				break;
			} else {
				int end = begin + strDelim.length();
				if(bContainNull){
					resultStrArray[index++] = strCheck.substring(0, begin);
				}
				strCheck = strCheck.substring(end);
				if(strCheck.length()==0 && bContainNull){
					resultStrArray[index] = strCheck;
					break;
				}
			}
		}
		return resultStrArray;
	}

	
	/**
	 * 주어진 문자열이 null 또는 공백문자열("") 인지를 체크한다.
	 *
	 * @param str
	 *            문자열
	 * @return 주어진 문자열이 null 또는 공백문자열("") 이라면 공백문자열("")을 반환하고 그렇지 않다면 주어진 문자열을
	 *         반환한다.
	 */
	public static String nullToString(String str) {
		return nullToString(str, "");
	}

	/**
	 * 주어진 문자열(regex)이 null 또는 공백문자열("") 인지를 체크한다.
	 *
	 * @param regex
	 *            원본 문자열
	 * @param replacement
	 *            대치할 문자열
	 * @return 원본 문자열이 null 또는 공백문자열("") 이라면 대치할 문자열을 반환하고 그렇지 않다면 원본 문자열을 반환한다.
	 */
	public static String nullToString(String regex, String replacement) {

		if (regex == null || "".equals(regex)) {
			return replacement;
		}
		return regex;
	}

	/**
	 * 주어진 문자열이 null또는 공백문자열("") 인지를 체크한다.
	 *
	 * @param str
	 *            원본 문자열
	 * @param i
	 *            대치할 값
	 * @return int 원본 문자열이 null또는 공백문자열("") 이라면 대치할 값을 반환하고 그렇지 않다면 원본 문자열을
	 *         Integer로 변환하여 반환한다 만일 원본 문자열을 Integer로 변환하는중 에러가 발생하면 0을 반환한다.
	 */
	public static int nullToInteger(String str, int i) {
		int value = 0;
		if ("".equals(nullToString(str))) {
			value = i;
		} else {
			try {
				value = Integer.parseInt(str);
			} catch (Exception e) {
			}
		}

		return value;
	}

	public static double nullToInteger(Object str) {
		if(str == null)
			return 0;

		return nullToInteger(str.toString());
	}
	
	public static int nullToInteger(String str) {

		return nullToInteger(str, 0);
	}
	
	public static boolean nullToBoolean(String str){
		return nullToBoolean(str, false);
	}
	public static boolean nullToBoolean(String str, boolean value) {
		str = nullToString(str);

		if(str.equalsIgnoreCase("true")){
			return true;
		} else if(str.equalsIgnoreCase("false")){
			return false;
		} else {
			return value;
		}
	}

	public static double nullToDouble(Object str) {
		if(str == null)
			return 0;

		return nullToDouble(str.toString());
	}	

	public static double nullToDouble(String str) {
		double value = 0;
		if (!"".equals(nullToString(str))) {
			try {
				value = Double.parseDouble(str);
			} catch (Exception e) {
			}
		}

		return value;
	}
	
	/**
	 * 주어진 문자열중 일부 문자열을 새로운 문자열로 변환하여 반환한다.
	 *
	 * @param original
	 *            원본 문자열
	 * @param pattern
	 *            변환되어야 하는 문자열
	 * @param replace
	 *            대치할 문자열
	 * @return 원본 문자열에서 변환되어야 하는 문자열을 대치할 문자열로 대치하여 반환한다.
	 */
	public static String replace(String original, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer buffer = new StringBuffer();

		while ((e = original.indexOf(pattern, s)) >= 0) {
			buffer.append(original.substring(s, e));
			buffer.append(replace);
			s = e + pattern.length();
		}
		buffer.append(original.substring(s));

		return buffer.toString();
	}
	
	public static String replace(String original, String replace, int start) {

		StringBuffer buffer = new StringBuffer();

		int s = start;
		int e = original.length();
		
		buffer.append(original.substring(0, start));
		
		
		while ( (e-s) >= 0) {
			
			buffer.append(replace);
			s++;
		}
		
		return buffer.toString();
	}	

	/**
	 * 주어진 문자열의 길이를 조정한다.
	 *
	 * @param str
	 *            원본 문자열
	 * @param tail
	 *            원본 문자열의 길이를 조정한후 뒤에 따라붙을 문자열
	 * @param start
	 *            문자열의 길이를 조정할 시작 위치
	 * @param end
	 *            원본 문자열의 길이를 조정할 최대 사이즈
	 * @return String 원본 문자열의 길이가 size 보다 크다면 size 크기로 조정하고 tail 을 추가한 문자열을
	 *         반환한다. 만일 원본문자열의 길이가 size 보다 작다면 원본문자열을 그대로 반환한다.
	 */
	public static String substring(String str, String tail, int size) {
		return substring(str, tail, 0, size);
	}

	public static String substring(String str, int start, int end) {
		return substring(str, "", start, end);
	}

	public static String substring(String str, int size) {
		return substring(str, "", 0, size);
	}
	
	public static String substring(String str, String tail, int start, int end) {

		if (str == null) {
			return "";
		} else if (str.length() == end) {
			return str.substring(start, end);
		} else if (str.length() > end) {
			return str.substring(start, end) + tail;
		} else {
			return str;
		}
	}	


	
	/**
	 * 주어진 문자열을 숫자형 문자로 변환한다.
	 *
	 * @param value
	 *            변환할 문자열
	 * @return String 변환된 문자열 "1234.56" -> "1,234.56"
	 */
	public static String numberFormat(String value) {
		return numberFormat(value, -1, -1);
	}

	public static String numberFormat(int value){
		return numberFormat(String.valueOf(value));
	}

	public static String numberFormat(long value){
		return numberFormat(String.valueOf(value));
	}

	public static String numberFormat(double value){
		return numberFormat(String.valueOf(value));
	}
	public static String numberFormat(double value, int max, int min){
		return numberFormat(String.valueOf(value), max, min);
	}	

	public static int toNumber(String value){
		return Integer.parseInt(value);
	}

	public static String toString(int i) {

		return String.valueOf(i);
	}

	/**
	 * 주어진 문자열의 소수점 이하의 자릿수가 max보다 큰경우 반올림하고 min보다 작은경우 0을 추가한다. 기본적으로 max값은
	 * min값보다 크거나 같아야 한다.
	 *
	 * @param value
	 *            변환할 문자열
	 * @param max
	 *            소수점 이하 최대 자릿수로 주어진 값이 0보다 작다면 적용되지 않는다.
	 * @param min
	 *            소수점 이하 최소 자릿수로 주어진 값이 0보다 작다면 적용되지 않는다.
	 * @return String 변환된 문자열 <br>
	 *         str="1234.56", max=1, min=0 -> "1,234.6"<br>
	 *         str="1234.56", max=-1, min=3 -> "1,234.560"
	 */
	public static String numberFormat(String value, int max, int min) {
		String formatValue = "";
		try {
			NumberFormat nf = NumberFormat.getInstance();
			if (max >= 0) {
				nf.setMaximumFractionDigits(max);
			}

			if (min >= 0) {
				nf.setMinimumFractionDigits(min);
			}

			formatValue = nf.format(Double.parseDouble(value));
		} catch (IllegalArgumentException e) {
			formatValue = value;
		}

		return formatValue;
	}


    public static String replace(String s, String as[], String as1[])
    {
        if(s == null || as == null || as1 == null)
            return s;
        for(int i = 0; i < as.length; i++)
            s = replace(s, as[i], as1[i]);

        return s;
    }

    public static int parseInt(String str){
    	int parseInt = 0;
    	try{
    		parseInt = Integer.parseInt(str);
    	}catch(Exception nf){}
    	return parseInt;
    }

    public static int parseInt(String str, int def){
    	int parseInt = 0;
    	try{
    		parseInt = Integer.parseInt(str);
    	}catch(Exception nf){parseInt = def;}
    	return parseInt;
    }

    public static boolean equals(String str1, String str2) {
    	    	
    	if(nullToString(str1).equals(str2)) {
    		return true;
    	} else {
    		return false;
    	}
    }
    

	/**
	 * 주어진 문자열이 특정 문자열의 포함 여부를 반환한다.
	 *
	 * @param original
	 *            원본 문자열
	 * @param pattern
	 *            특정 문자열
	 * @return 포함여부를 반환한다
	 */
	public static boolean isContain(String original, String pattern) {
		int s = 0;
		int e = 0;
		boolean isContain = false;

		while ((e = original.indexOf(pattern, s)) >= 0) {
			
			isContain = true;
			break;
		}
		return isContain;
	}    
    
	
    public static String leftPad(String str, int len, String padding)
    {
        return  String.format("%" + len + "s", str).replace(" ", padding);    
    }

    public static String rightPad(String str, int len, String padding)
    {
        return String.format("%-" + len + "s", str).replace(" ", padding);
    }    
}