package it.reply.client.util;

import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

	public static String readFromFile(String path) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream is = null;
		try {
			File file = new File(path);
			is = new FileInputStream(file);
			byte buf[] = new byte[4096];
			int len;
			while ((len = is.read(buf)) != -1)
				baos.write(buf, 0, len);
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			if (is != null)
				is.close();
		}
		return new String(baos.toByteArray());
	}
	
    public static String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }
    
    public static String getUTCTimeString(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssX");
    	sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    	return sdf.format(date);
    }

}
