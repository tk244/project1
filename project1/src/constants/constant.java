package constants;

public class constant {

	// privateコンストラクタでインスタンス生成を抑止
	private constant(){} 
	
	// 定数
	public static final String url = "jdbc:mysql://localhost/jdbctestdb?useUnicode=true&characterEncoding=utf8";
	public static final String user = "test";
	public static final String password = "testpass";	
	
	public static final String log4j = "WEB-INF/log4j.xml";	
	
	public static final String localurl = "http://localhost:8080/project1/";
	
	public static final String serverError = "サーバーエラー";
}
