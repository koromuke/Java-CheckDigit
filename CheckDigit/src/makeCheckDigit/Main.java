package makeCheckDigit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	
	/**
	 * メイン実行
	 * 
	 */
	public static void main(String[] args) {

		//入力されたコード
		String inputCode = getCode();
		
		
//		半角数字かどうか調べます。
		Pattern checkHalf = Pattern.compile("^[0-9]*$");
		Matcher matchHalf = checkHalf.matcher(inputCode);
		
		if (matchHalf.find() == false) {
			
			System.out.println("半角数字を入力してください。");
			System.exit(0);
		}
		
//		System.out.println(matchHalf.find());
		
		//入力されたコードからチェックデジットを照合
		calcCheckDigit(inputCode);
		
	}
	

	public static String getCode() {
		
		
		
		String code = "";
		
		
		
			System.out.println("↓　Input Code ↓");
			
		//入力した値を認識
		BufferedReader codeReader = new BufferedReader(new InputStreamReader(System.in));
		
	
		try {
			code = codeReader.readLine();
			
			/**
			 * 予想外の入力にたいしてメッセージを出力
			 * 
			 * 5 文字より上、または 1 文字より上で 5 文字より下の時
			 * 
			 */
				if (code.length() > 5 || (code.length() < 5 && code.length() >= 1)) {
				
					System.out.println("5桁の数字を入力してください。");
					
					System.exit(0);
					
			//なにも入力されていないとき
				}else if(code.equals("") == true){
					
					System.out.println("数字を入力してください。");
					
					System.exit(0);
				}
			
			codeReader.close();
			
		
		} catch (IOException e1) {
			System.out.println("予期せぬエラーが発生しました。");
			e1.printStackTrace();
		}
		return code;
	}
	
	
	
	
	
	
	
	
	//前４桁を計算し、出力するメソッド
	public static int calcCheckDigit(String code) {
		
		//コードを１つずつに分ける
		String[] codes =code.split("");
		
		//初期化
		int forCheck = 0;
		int countZero = 0;
		int repeatNum = 0;
			
		try {
			//コードを一つずつ計算
			for (int i = 0; i < codes.length-1; i++) {
				
				int checkNum = Integer.parseInt(codes[i]);
				
				//エラーの条件となる要素をカウント
				if(i <= 3) {
					if (checkNum == 0) {
						
						countZero ++;
					}	
				}
					
				if(i <= 2 && codes[i].equals(codes[i +1])) {
						
						repeatNum ++;
						
					}
				
			
				checkNum += 1;
				
				//10になったら0とみなす
				if(checkNum == 10) {
					
					checkNum = 0;
					
				}
			
				//計算済み4桁の合計を求める
				forCheck += checkNum;
	
			}
			
			//前４桁の入力が正しいかを判断する(0が3つ以上またはすべて同じ数字)
			if (countZero >= 3) {
				
				System.out.println("入力された数字は正しくありません。");
				System.exit(0);
				
			}else if (repeatNum >= 3) {
				
				System.out.println("入力された数字は正しくありません。");
				System.exit(0);
				
			}
			
			//３で割ったあまりを計算
			int checkdigit = forCheck % 3;
			
			
			
			
			//チェックデジットと照合をして結果を出力。
			if (checkdigit == Integer.parseInt(codes[codes.length-1])) {
				
				System.out.println("デジタルチェックOK。");
		
			}else {
				
				System.out.println("デジタルチェックNG。");
				
				
			}//if終了


		} catch (NumberFormatException e) {
			System.out.println("5桁の数字を入力してください。");
		}
			
			
		return forCheck;
		
		
		
	}
		
		
}
