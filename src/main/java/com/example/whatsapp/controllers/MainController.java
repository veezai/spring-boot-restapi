package com.example.whatsapp.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.whatsapp.response.ClientDetails;
import com.example.whatsapp.utils.ParseDynamicJson;

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
	public ResponseEntity<String> verifyCallbackUrlFromDashboard(@RequestBody Map<String, String> verifyMap) {

		String mode = verifyMap.get("hub.mode");
		String challenge = verifyMap.get("hub.challenge");
		String token = verifyMap.get("hub.verify_token");

		if(mode=="subscribe" && token==myToken) {
			return ResponseEntity.ok(challenge);
		} else {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
			

	}

	@PostMapping("/webhook")
	public void fetchResponseAndReply(@RequestBody Map jsonMap){

		JSONObject inputJsonOBject = new JSONObject(jsonMap);
		ParseDynamicJson.getKey(inputJsonOBject, "display_phone_number");
		String display_phone_number = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "phone_number_id");
		String phone_number_id = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "name");
		String name = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "from");
		String from = ParseDynamicJson.po;

//		ClientDetails clientDetails = new ClientDetails(v1,
//				v2,
//				v3,
//				v4);
//		return clientDetails;



	}
	
	

}
