package pjy;


import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by 43997118 on 2016/8/31.
 */
class CityAndHotel{
	private String ID;
	private String Name;
	private String Citycode;
	private String City;
}
public class Htmlparser {

	protected static List<CityAndHotel> cityAndhotel=new ArrayList<CityAndHotel>();
	protected static List<String> city=new ArrayList<String>();
    protected static class HTMLParse extends HTMLEditorKit{
        public HTMLEditorKit.Parser getParser(){
            return super.getParser();
        }
    }
    protected static class ParserCB extends HTMLEditorKit.ParserCallback{
    	
    	protected int scriptStartpos=0;
    	protected int scriptEndpos=0;
    	protected boolean selectdone=false;
    	protected boolean scriptdone=false;
    	protected HTML.Tag currenttag;
        @Override
        public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a,int pos){
        	currenttag=t;
        	if(selectdone==true&&scriptdone==true) return;
            if(a.getAttribute(HTML.Attribute.ID)=="selCity"){
            	selectdone=true;
            }
            if(t==HTML.Tag.OPTION){
            	city.add((String) a.getAttribute(HTML.Attribute.VALUE));
            }
        }
        @Override
        public void handleStartTag(HTML.Tag t, MutableAttributeSet a,int pos){
        	scriptStartpos=pos;
            String href=(String) a.getAttribute(HTML.Attribute.HREF);
        }
        @Override
        public void handleEndTag(HTML.Tag t,int pos){
        	scriptEndpos=pos;
        }
       @Override
        public void handleText(char[]data,int pos){
           // if(t)
        }
    }
    public static void main(String[] args){
        try {
            //URL url=new URL("http://rewards.yourright.com.cn/catalog.aspx?bank=HSB");
            //HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            FileInputStream input=new FileInputStream(new File("C:\\Users\\43997118\\Desktop\\test1.html"));
            FileReader reader=new FileReader(new File("C:\\Users\\43997118\\Desktop\\test1.html"));
//            Scanner scanner=new Scanner(input,"utf-8");
//            while(scanner.hasNextLine()){
//                java.lang.String line=scanner.nextLine();
//                System.out.println(line);
//            }
            HTMLEditorKit.Parser parser=new HTMLParse().getParser();
            parser.parse(reader,new ParserCB(),true);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
