package utils;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String METHOD_POST = "POST";
    private static final int DEFAULT_TIMEOUT = 5000;

    public HttpUtils() {
    }

    public static String doPost(String url, String params) throws IOException {
        return doPost(url, params, "UTF-8", 5000, 5000);
    }

    public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout) throws IOException {
        byte[] content = new byte[0];
        if (params != null) {
            content = params.getBytes(charset);
        }

        HttpURLConnection conn = null;
        OutputStream out = null;
        String response = null;

        try {
            try {
                conn = getConnection(new URL(url), "POST", "application/json;charset=" + charset);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException var15) {
                throw var15;
            }

            try {
                out = conn.getOutputStream();
                out.write(content);
                response = getResponseAsString(conn);
            } catch (IOException var14) {
                throw var14;
            }
        } finally {
            if (out != null) {
                out.close();
            }

            if (conn != null) {
                conn.disconnect();
            }

        }

        return response;
    }

    private static HttpURLConnection getConnection(URL url, String method, String contentType) throws IOException {
        HttpURLConnection conn = null;
        conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "application/xml,application/json,text/javascript,text/html");
        conn.setRequestProperty("Content-Type", contentType);
        return conn;
    }

    protected static String getResponseAsString(HttpURLConnection conn) throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        } else {
            String msg = getStreamAsString(es, charset);
            if (StringUtils.isEmpty(msg)) {
                throw new IOException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            } else {
                throw new IOException(msg);
            }
        }
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));
            StringWriter writer = new StringWriter();
            char[] chars = new char[256];
            boolean var5 = false;

            int count;
            while ((count = reader.read(chars)) > 0) {
                writer.write(chars, 0, count);
            }

            String var6 = writer.toString();
            return var6;
        } finally {
            if (stream != null) {
                stream.close();
            }

        }
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (!StringUtils.isEmpty(ctype)) {
            String[] params = ctype.split(";");
            String[] arr$ = params;
            int len$ = params.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                String param = arr$[i$];
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if (pair.length == 2 && !StringUtils.isEmpty(pair[1])) {
                        charset = pair[1].trim();
                    }
                    break;
                }
            }
        }

        return charset;
    }
}
