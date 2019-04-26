import java.net.URL;

import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

public class CloudantDB {
	
	private String _id;
	private String _rev;
	private String member_name;
	private String SSN_no;
	private String benificiary_id;
	private String benificiary_name;
	private String benificiary_contactNo;
	private String benificiary_emailId;
	
	
	public CloudantDB(String member_name, String sSN_no, String benificiary_id, String benificiary_name,String benificiary_contactNo, String benificiary_emailId) {
		this.member_name = member_name;
		this.SSN_no = sSN_no;
		this.benificiary_id = benificiary_id;
		this.benificiary_name = benificiary_name;
		this.benificiary_contactNo = benificiary_contactNo;
		this.benificiary_emailId = benificiary_emailId;
	}
	

	@Override
	public String toString() {
		return "CloudantDB [_id=" + _id + ", _rev=" + _rev + ", member_name=" + member_name + ", SSN_no=" + SSN_no
				+ ", benificiary_id=" + benificiary_id + ", benificiary_name=" + benificiary_name
				+ ", benificiary_contactNo=" + benificiary_contactNo + ", benificiary_emailId=" + benificiary_emailId
				+ "]";
	}




	public String GetDataByID(String id) {
		
		
		String cloudantUserName = "97db821e-87a4-4507-b8ee-fcc95b72b447-bluemix";
		String cloudantPassword = "ae34609f865eac5720a3e08c9c0208840a9418090a98f9a4c1fcb9fa5573040b" ;
		
		String url="https://"+cloudantUserName+":"+cloudantPassword+"@"+cloudantUserName+".cloudant.com";

		try {
			
			Database db;

			CloudantClient client = ClientBuilder.url(new URL(url)).build();
			System.out.println("connected");
			db = client.database("deathclaim", true);
			CloudantDB cloudantdb =  db.find(CloudantDB.class, id);
			System.out.println(cloudantdb);
			return cloudantdb.toString();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("This is main method");
	}
	
}
