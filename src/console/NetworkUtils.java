package console;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.zip.GZIPInputStream;

public class NetworkUtils {
    public static String downloadHttpClientJsonPost(String url, String json, int timeout) throws URISyntaxException, IOException {

        String content = readInputStream(ISdownloadHttpClientJsonPostHelp(url, json, timeout));
        return content;
    }

    private static String readInputStream(InputStream stream) throws IOException {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        StringWriter writer = new StringWriter();
        InputStreamReader reader = new InputStreamReader(stream, "UTF8");
        try {
            while (-1 != (n = reader.read(buffer)))
                writer.write(buffer, 0, n);

            return writer.toString();
        } finally {
            reader.close();
        }
    }

    private static InputStream ISdownloadHttpClientJsonPostHelp(String url, String json, int timeout) throws URISyntaxException, IOException {

        InputStream is;

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setConnectTimeout(timeout);
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Accept-Encoding", "gzip");
        con.setRequestProperty("Content-type", "application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        con.connect();

        OutputStream os = con.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write(json);
        writer.flush();
        writer.close();
        os.close();

        String encoding = con.getContentEncoding();

        int response = con.getResponseCode();
        if (response == HttpsURLConnection.HTTP_OK) {
            if (encoding != null && encoding.equals("gzip")) {
                is = new GZIPInputStream(con.getInputStream());
            } else {
                is = con.getInputStream();
            }
        } else {
            throw new RuntimeException("Service Error: " + con.getResponseMessage());
        }

        return is;
    }
}
