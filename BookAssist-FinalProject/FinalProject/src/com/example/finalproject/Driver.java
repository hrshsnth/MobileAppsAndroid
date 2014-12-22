package com.example.finalproject;

import java.security.SignatureException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;



public class Driver {
	  // private static int[] heap = new int[50];  
	   static int[] temp = {1,2,3,4,5};
	   
	/*   public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException, SignatureException{
		   
		   String key = "1234567890";
		   String access_key = "AKIAIOSFODNN7EXAMPLE";
           String st = "GET webservices.amazon.com/onca/xmlAWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&ItemId=0679722769&Operation=ItemLookup&ResponseGroup=ItemAttributes%2COffers%2CImages%2CReviews&Service=AWSECommerceService&Timestamp=2009-01-01T12%3A00%3A00Z&Version=2009-01-06";
           String st2 = "GET\nwebservices.amazon.com\n/onca/xml\nAWSAccessKeyId=AKIAIOSFODNN7EXAMPLE&ItemId=0679722769&Operation=I\ntemLookup&ResponseGroup=ItemAttributes%2COffers%2CImages%2CReview\ns&Service=AWSECommerceService&Timestamp=2009-01-01T12%3A00%3A00Z&\nVersion=2009-01-06";
           String f = calculateRFC2104HMAC(st2, key);
           //System.out.println(f);
          
		   SignedRequestsHelper obj = new SignedRequestsHelper();
           HashMap<String, String> map = new HashMap<String, String>();
           map.put("Service","AWSECommerceService");
           map.put("Operation","ItemSearch");
           map.put("Condition","New");
           map.put("Availability","Available");
           map.put("SearchIndex","All");
           map.put("Keywords","Cracking the coding interview");
           map.put("AssociateTag", "twitter0041-20");
           map.put("ResponseGroup", "OfferFull, ItemAttributes");
           //map.put("MerchantId", "Amazon");
           String result = obj.sign(map);
           System.out.println(result);
           
	   }*/
	   public static String calculateRFC2104HMAC(String data, String key)
			   throws java.security.SignatureException
			   {
			   String result;
			   try {

			   // get an hmac_sha1 key from the raw key bytes
			   SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");

			   // get an hmac_sha1 Mac instance and initialize with the signing key
			   Mac mac = Mac.getInstance("HmacSHA256");
			   mac.init(signingKey);

			   // compute the hmac on input data bytes
			   byte[] rawHmac = mac.doFinal(data.getBytes());

			   // base64-encode the hmac
			  result = Base64.encodeBase64String(rawHmac);
			  // result = new String(encode(rawHmac));
			   } catch (Exception e) {
			   throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
			   }
			   return result;
			   }
	   
	   
	   private static char[] encode(byte[] bytes) {
		    final int amount = bytes.length;
		    char[] result = new char[2 * amount];

		    int j = 0;
		    for (int i = 0; i < amount; i++) {
		      result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];
		      result[j++] = HEX[(0x0F & bytes[i])];
		    }

		    return result;
		  }

		  private static final char[] HEX = {
		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
		  };  
	   

}
