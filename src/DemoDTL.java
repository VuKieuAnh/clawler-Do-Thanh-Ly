import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoDTL {
    private static String getContentFrom(String link) throws IOException {
        URL url = new URL(link);
        Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
        scanner.useDelimiter("\\\\Z");
        String content = scanner.next();
        scanner.close();
        // xoá các ký tự ngắt dòng (xuống dòng)
        content = content.replaceAll("\\R", "");
        content = content.replaceAll("\\n", "");
        return content;
    }
    public static String getEvent(String c){
        String result= "";
        // Regex
        Pattern p1 = Pattern.compile("<ul class=\"block-content\">(.*?)</ul>");
        Matcher m1 = p1.matcher(c);
        while (m1.find()) {
            result = m1.group(1);
        }
        return result;
    }
    public static void main(String[] args) throws IOException {
        String content = getContentFrom("https://dothanhly.f88.vn/Home/ProductFilter?category=00000005&name=&fromPrice=&toPrice=&Fillter=0");
        String c = getEvent(content);
        // Regex ten sp
        Pattern p1 = Pattern.compile("<div class=\"name\">(.*?)</div>");
        Matcher m1 = p1.matcher(c);
        while (m1.find()) {
            System.out.println(m1.group(1).trim());
        }
        // Regex link
        Pattern p2 = Pattern.compile("<a class=\"prd-img\" href=\"(.*?)\" data-id");
        Matcher m2 = p2.matcher(c);
        String rootUrl = "https://dothanhly.f88.vn/";
        while (m2.find()) {
            System.out.println(rootUrl + m2.group(1).trim());
        }
        // Regex gia F88
        Pattern p3 = Pattern.compile("<div class=\"price\">(.*?)</div>");
        Matcher m3 = p3.matcher(c);
        while (m3.find()) {
            System.out.println( m3.group(1).trim());
        }
        // Regex gia moi
        Pattern p4 = Pattern.compile("<span class=\"pricing\">(.*?)</span>");
        Matcher m4 = p4.matcher(c);
        while (m4.find()) {
            System.out.println( m4.group(1).trim());
        }
    }
}
