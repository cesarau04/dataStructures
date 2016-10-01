package homework1;

public class RecString {
	
	@SuppressWarnings("unused")
	private static int StrLen(String n){
		if (n.equals("")){
			return 0;
		}else{
			return StrLen(n.substring(1)) + 1;
		}
	}
}
