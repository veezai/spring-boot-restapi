package com.example.whatsapp.controllers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.whatsapp.response.ClientDetails;
import com.example.whatsapp.utils.ParseDynamicJson;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class MainController {


	@Value("${TOKEN}")
	private String token;

	@Value("${MYTOKEN}")
	String myToken;

	@GetMapping("/")
	public String wc() {
		return "Welcome";
	}

	//to verify the callback url from dashboard side - cloud api side
	@GetMapping("/webhooks")
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

	@PostMapping("/webhooks")
	public void fetchResponseAndReply(@RequestBody Map qparams){

		JSONObject inputJsonOBject = new JSONObject(qparams);
//		ParseDynamicJson.getKey(inputJsonOBject, "display_phone_number");
//		String display_phone_number = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "phone_number_id");
		String phone_number_id = ParseDynamicJson.po;
//		ParseDynamicJson.getKey(inputJsonOBject, "name");
//		String name = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "from");
		String from = ParseDynamicJson.po;
		ParseDynamicJson.getKey(inputJsonOBject, "body");
		String msg_body = ParseDynamicJson.po;

		System.out.println(inputJsonOBject.toString());


//		Create request body
		JSONObject text = new JSONObject();
		text.put("body", "Hi.. I'm Vijay...");
		JSONObject request = new JSONObject();
		request.put("messaging_product", "whatsapp");
		request.put("to", from);
		request.put("text", text);


// 		set headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);

// 		send request and parse result
		/*RestTemplate restTemplate = new RestTemplate();
		String url = "https://graph.facebook.com/v13.0/"+phone_number_id+"/messages?access_token="+token;
		ResponseEntity<String> sendResponse = restTemplate
				.exchange(url, HttpMethod.POST, entity, String.class);
		if (sendResponse.getStatusCode() == HttpStatus.OK) {
			JSONObject userJson = new JSONObject(sendResponse.getBody());
			System.out.println(userJson.toString());
		} else if (sendResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			// nono... bad credentials
		}*/

	}
	
	

}
