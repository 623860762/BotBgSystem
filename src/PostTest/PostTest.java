package PostTest;import java.io.BufferedReader;import java.io.InputStream;import java.io.InputStreamReader;import java.io.OutputStreamWriter;import java.net.HttpURLConnection;import java.net.URL;public class PostTest {	/**	 * @param args	 */	public static void main(String[] args) throws Exception{		URL url = new URL("http://127.0.0.1:8080/BotBgSystem/api/appendIndex");        // 设定连接的相关参数        HttpURLConnection connection= (HttpURLConnection) url.openConnection();        connection.setDoOutput(true);        connection.setRequestMethod("POST");        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");                // 向服务端发送key = value对        out.write("data=");        out.flush();        out.close();                // 获取服务端的反馈        String strLine="";        String strResponse ="";        InputStream in =connection.getInputStream();        BufferedReader reader = new BufferedReader(new InputStreamReader(in));        while((strLine =reader.readLine()) != null)        {                 strResponse +=strLine +"\n";        }        System.out.print(strResponse);	}}