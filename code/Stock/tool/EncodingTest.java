
public class EncodingTest {
	
	public static void main(String[] args) {
		System.out.println(bytesToHexString("浦发银行".getBytes()));
	}
	
	private static String bytesToHexString(byte[] bytes){
		StringBuilder stringBuilder = new StringBuilder("");  
		if (bytes != null && bytes.length > 0) {
			for(int i = 0;i< bytes.length;i++){
			    int v = bytes[i] & 0xFF;  
		        String hv = Integer.toHexString(v);  
		        if (hv.length() < 2) {  
		            stringBuilder.append(0);  
		        }  
		        stringBuilder.append(hv);
		        stringBuilder.append("|");
			}
		}
		return stringBuilder.toString();
	}

}
