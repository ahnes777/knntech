package testcls;

import java.net.InetAddress;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class snmpcall {
	
//	public String ip;
//	public String port;
//	public String community;
//	public String oid;
//	
//	public snmpcall(String ip, String port, String community, String oid) {
//		this.ip = ip;
//		this.port = port;
//		this.community = community;
//		this.oid = oid;
//	}

	public Vector SnmpGet(String ip, String port, String community, String oid) throws java.io.IOException {

		Vector variableBindings = null;
		// 1. Make Protocol Data Unit
		PDU pdu = new PDU();
		pdu.add(new VariableBinding(new OID(oid)));
		pdu.setType(PDU.GET);
		// pdu.setType(PDU.GETBULK );
		//String temp11 = null;
		// pdu.setType(PDU.NOTIFICATION);

		// 2. Make target
		CommunityTarget target = new CommunityTarget();
		UdpAddress targetAddress = new UdpAddress();
		/*
		 * targetAddress.setInetAddress(InetAddress.getByName(defaultIP));
		 * targetAddress.setPort(defaultPort); target.setAddress(targetAddress);
		 * target.setCommunity(new OctetString("public"));
		 */
		targetAddress.setInetAddress(InetAddress.getByName(ip));
		targetAddress.setPort(Integer.parseInt(port));
		target.setAddress(targetAddress);
		target.setCommunity(new OctetString(community));
		target.setVersion(SnmpConstants.version1 );

		// 3. Make SNMP Message. Simple!
		Snmp snmp = new Snmp(new DefaultUdpTransportMapping());

		// 4. Send Message and Recieve Response
		// Array test1;
		snmp.listen();		
		ResponseEvent response = snmp.send(pdu, target);
		if (response.getResponse() == null) {
			System.out.println("Error: There is some problems.");
			variableBindings.add("no-answer");
			
		} else {
			variableBindings = response.getResponse().getVariableBindings();
			//test1 = variableBindings ;
			// for( int i = 0; i < variableBindings.size(); i++){
			// //System.out.println(variableBindings.get(i));
			// temp11 = (String) variableBindings.get(i).toString();
			// }
		}
		snmp.close();

		return variableBindings;
	}
	
}
