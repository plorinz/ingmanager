package it.reply.client.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClientUtil {

	private int connectTimeout = 60000;
	private int readTimeout = 60000;

	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public ClientResponse sendRequest(String urlString, String method, String content) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		StringBuffer response = new StringBuffer();
		ClientResponse clientResp = null;
		boolean disconnect = true;

		int resCode;
		try {
			// Get connection
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setRequestProperty("Content-Type", "application/json");
			// connection.setRequestProperty("connection", "close");
			// Sending content
			if (content != null) {
				connection.setDoOutput(true);
				os = connection.getOutputStream();
				os.write(content.getBytes());
				//os.flush();
			}
			// Get response code
			resCode = connection.getResponseCode();

			if (resCode < 400)
				is = connection.getInputStream();
			else
				is = connection.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			clientResp = new ClientResponse(resCode, response.toString());
			disconnect = false;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (os != null) {
//				try {
//					os.close();
//				} catch (Exception ignore) {
//				}
//			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
				}
			}
			
			if (disconnect && connection != null)
				connection.disconnect();
		}

		return clientResp;
	}
	
	public ClientResponse sendLoginRequest(String urlString, String method, String content) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		StringBuffer response = new StringBuffer();
		ClientResponse clientResp = null;
		boolean disconnect = true;

		int resCode;
		try {
			// Get connection
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("username", "ionescu");
			// connection.setRequestProperty("connection", "close");
			// Sending content
			if (content != null) {
				connection.setDoOutput(true);
				os = connection.getOutputStream();
				os.write(content.getBytes());
				//os.flush();
			}
			// Get response code
			resCode = connection.getResponseCode();

			if (resCode < 400)
				is = connection.getInputStream();
			else
				is = connection.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			clientResp = new ClientResponse(resCode, response.toString());
			disconnect = false;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (os != null) {
//				try {
//					os.close();
//				} catch (Exception ignore) {
//				}
//			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
				}
			}
			
			if (disconnect && connection != null)
				connection.disconnect();
		}

		return clientResp;
	}
	
	public ClientResponse sendTokenRequest(String urlString, String method, String content,String token) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		HttpURLConnection connection = null;
		StringBuffer response = new StringBuffer();
		ClientResponse clientResp = null;
		boolean disconnect = true;

		int resCode;
		try {
			// Get connection
			URL url = new URL(urlString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(method);
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("X-AUTH", token);
			// connection.setRequestProperty("connection", "close");
			// Sending content
			if (content != null) {
				connection.setDoOutput(true);
				os = connection.getOutputStream();
				os.write(content.getBytes());
				//os.flush();
			}
			// Get response code
			resCode = connection.getResponseCode();

			if (resCode < 400)
				is = connection.getInputStream();
			else
				is = connection.getErrorStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

			clientResp = new ClientResponse(resCode, response.toString());
			disconnect = false;
		} catch (Exception ex) {
			throw ex;
		} finally {
//			if (os != null) {
//				try {
//					os.close();
//				} catch (Exception ignore) {
//				}
//			}
			if (is != null) {
				try {
					is.close();
				} catch (Exception ignore) {
				}
			}
			
			if (disconnect && connection != null)
				connection.disconnect();
		}

		return clientResp;
	}

}
