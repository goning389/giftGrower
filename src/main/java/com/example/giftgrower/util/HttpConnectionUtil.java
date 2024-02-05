/**
 *
 */
package com.example.giftgrower.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class HttpConnectionUtil
{
	protected static final Log log = LogFactory.getLog(HttpConnectionUtil.class);

	/** HttpURLConnection GET 방식 */
	public static String getRequest(String apiURL, String headerStr) {

		String response = "";

		try {

			final URL url = new URL(apiURL);
			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 전송 방식

			//요청에 필요한 Header에 포함될 내용
			if(headerStr != null && !headerStr.equals("") ) {
				conn.setRequestProperty("Authorization", headerStr);
			}

			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);

			log.info("########## getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
			log.info("########## getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			final int responseCode = conn.getResponseCode();

			if(responseCode == 200){
				final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String inputLine;
				final StringBuffer sb = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}

				br.close();

				response = sb.toString();
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/** HttpURLConnection POST 방식 */
	public static String postRequest(String apiURL, String headerStr, Map<String, Object> requestMap) {

		String response = "";

		try {

			final URL url = new URL(apiURL);

			log.info("########## apiURL :" + apiURL);

			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 전송 방식

			//요청에 필요한 Header에 포함될 내용
			if(headerStr != null && !headerStr.equals("") ) {
				conn.setRequestProperty("Authorization", headerStr);
			}

			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)

			if (requestMap != null) {
				final String requestBody = getParamFromMap(requestMap);
				log.info("########## requestBody:" + requestBody);

				final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				bw.write(requestBody);
				bw.flush();
				bw.close();
			}

			log.info("########## getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
			log.info("########## getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			final int responseCode = conn.getResponseCode();

			if(responseCode == 200){
				final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String inputLine;
				final StringBuffer sb = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}

				br.close();

				response = sb.toString();
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/** HttpURLConnection delete 방식 */
	public static String deleteRequest(String apiURL, String headerStr) {

		String response = "";

		try {

			final URL url = new URL(apiURL);

			log.info("########## apiURL :" + apiURL);

			final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE"); // 전송 방식

			//요청에 필요한 Header에 포함될 내용
			if(headerStr != null && !headerStr.equals("") ) {
				conn.setRequestProperty("Authorization", headerStr);
			}

			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초)
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);

			log.info("########## getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
			log.info("########## getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			final int responseCode = conn.getResponseCode();

			if(responseCode == 200){
				final BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String inputLine;
				final StringBuffer sb = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					sb.append(inputLine);
				}

				br.close();

				response = sb.toString();
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static String getParamFromMap(Map<String, Object> map) {

		final StringBuilder postData = new StringBuilder();

		for(final Map.Entry<String, Object> entry : map.entrySet()) {
			if(postData.length() != 0)
			{
				postData.append("&");
			}

			postData.append(entry.getKey());
			postData.append("=");
			postData.append(entry.getValue());

		}

		return postData.toString();
	}
}
