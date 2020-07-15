package testcls;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;

public class maincls {
	
	static String Nip = "127.0.0.1";
	static String Nport = "161";
	static String Noid = ".1.3.6.1.2.1.1.1.0";
	static String Ncommunity = "public";
	static Vector output_vector;
	static String str;
	static String[] strarray;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		snmpcall mysnmp = new snmpcall();
		try {
			output_vector = mysnmp.SnmpGet(Nip, Nport, Ncommunity, Noid);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		str = output_vector.get(0).toString();
		strarray = str.split("=");
		System.out.print(strarray[0]);
		System.out.print(strarray[1]);
	}
	
}
