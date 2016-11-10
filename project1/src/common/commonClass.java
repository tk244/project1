package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class commonClass {

	/**
	* 文字列をハッシュ化するメソッド
	*
	* @param text ハッシュ化するテキスト
	*
	* @return ハッシュ化した計算値(16進数)
	 * @throws Exception 
	*/
	public String encryptStr(String text) throws Exception {
	    // 変数初期化
	    MessageDigest md = null;
	    StringBuffer buffer = new StringBuffer();
	 
		try {
			
		    try {
		        // メッセージダイジェストインスタンス取得
		        md = MessageDigest.getInstance("SHA-256");
		    } catch (NoSuchAlgorithmException e) {
		    	throw new Exception(e.getMessage());
		    }
		 
		    // メッセージダイジェスト更新
		    md.update(text.getBytes());
		 
		    // ハッシュ値を格納
		    byte[] valueArray = md.digest();
		 
		    // ハッシュ値の配列をループ
		    for(int i = 0; i < valueArray.length; i++){
		        // 値の符号を反転させ、16進数に変換
		        String tmpStr = Integer.toHexString(valueArray[i] & 0xff);
		 
		        if(tmpStr.length() == 1){
		            // 値が一桁だった場合、先頭に0を追加し、バッファに追加
		            buffer.append('0').append(tmpStr);
		        } else {
		            // その他の場合、バッファに追加
		            buffer.append(tmpStr);
		        }
		    }
	 
	    }catch (Exception e){
	    	throw new Exception(getClass().getName() + " " + e.getMessage());
	    }
	    // 完了したハッシュ計算値を返却
	    return buffer.toString();
	}
}
