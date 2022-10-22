package com.example.whatsapp.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.whatsapp.response.ClientDetails;
import com.example.whatsapp.utils.ParseDynamicJson;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MainController {
	
	String inputJson = "{\r\n"
			+ "  \"object\": \"whatsapp_business_account\",\r\n"
			+ "  \"entry\": [{\r\n"
			+ "      \"id\": \"WHATSAPP_BUSINESS_ACCOUNT_ID\",\r\n"
			+ "      \"changes\": [{\r\n"
			+ "          \"value\": {\r\n"
			+ "              \"messaging_product\": \"whatsapp\",\r\n"
			+ "              \"metadata\": {\r\n"
			+ "                  \"display_phone_number\": PHONE_NUMBER,\r\n"
			+ "                  \"phone_number_id\": PHONE_NUMBER_ID\r\n"
			+ "              },\r\n"
			+ "              \"contacts\": [{\r\n"
			+ "                  \"profile\": {\r\n"
			+ "                    \"name\": \"NAME\"\r\n"
			+ "                  },\r\n"
			+ "                  \"wa_id\": PHONE_NUMBER\r\n"
			+ "                }],\r\n"
			+ "              \"messages\": [{\r\n"
			+ "                  \"from\": PHONE_NUMBER,\r\n"
			+ "                  \"id\": \"wamid.ID\",\r\n"
			+ "                  \"timestamp\": TIMESTAMP,\r\n"
			+ "                  \"text\": {\r\n"
			+ "                    \"body\": \"MESSAGE_BODY\"\r\n"
			+ "                  },\r\n"
			+ "                  \"type\": \"text\"\r\n"
			+ "                }]\r\n"
			+ "          },\r\n"
			+ "          \"field\": \"messages\"\r\n"
			+ "        }]\r\n"
			+ "  }]\r\n"
			+ "}";

	@Value("${TOKEN}")
	private String token;

	@Value("${MYTOKEN}")
	String myToken;

	@GetMapping("/")
	public String wc() {
		return "Welcome";
	}

	//to verify the callback url from dashboard side - cloud api side
	@GetMapping("/webhook")
	public ResponseEntity<Object> verifyCallbackUrlFromDashboard(@RequestParam(name = "hub.mode") String mode,
																 @RequestParam(name = "hub.challenge") String challenge,
																 @RequestParam(name = "hub.verify_token") String verify_token) {


		if(mode.equals("subscribe") && verify_token.equals(myToken)) {
//			return ResponseEntity.ok(challenge);
			System.out.println("challenge:"+ challenge);
			return new ResponseEntity<Object>(challenge, HttpStatus.OK);
		} else {
//			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
		}
			

	}

	@PostMapping("/webhook")
	public void fetchResponseAndReply(@RequestBody Map qparams){

		JSONObject inputJsonOBject = new JSONObject(qparams);
		ParseDynamicJson.getKey(inputJsonOBject, "display_phone_number");
		String display_phone_number = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "phone_number_id");
		String phone_number_id = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "name");
		String name = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "from");
		String from = ParseDynamicJson.po;

		System.out.println("display_phone_number:" + display_phone_number + ", " +
				"phone_number_id:" + phone_number_id + ", " +
				"name:" + name + ", " +
				"from:" + from);

//		ClientDetails clientDetails = new ClientDetails(v1,
//				v2,
//				v3,
//				v4);
//		return clientDetails;



	}
	
	

}
