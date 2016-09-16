package parser;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import org.w3c.dom.Element;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by 43997118 on 2016/8/31.
 */
public class ImageSrcParser {
	protected static List<String> abs_path = new ArrayList<String>();
	protected static List<String> imagesrcs = new ArrayList<String>();
	protected static String path="";
    protected static class HTMLParse extends HTMLEditorKit{
        public HTMLEditorKit.Parser getParser(){
            return super.getParser();
        }
    }
    ImageSrcParser(String path){
    	this.path = path;
    }
    public void setImageSrc(List<String> list){
    	this.imagesrcs = list;
    }
    public List<String> getAbs_path(){
    	return this.abs_path;
    }
    public List<String> getImageSrc(){
    	return this.imagesrcs;
    }
    public static class ParserCB extends HTMLEditorKit.ParserCallback{
    	protected HTML.Tag currenttag;
    	protected boolean selectdone = false;
        @Override
        public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a,int pos){
        	if(t==HTML.Tag.IMG){
        		String currentattr=(String) a.getAttribute(HTML.Attribute.SRC);
        		if(currentattr.startsWith("http")){
        			abs_path.add(currentattr);
        			imagesrcs.add(currentattr);
        		}
        		else if(currentattr.startsWith("..")){
        			String sub_path = path.substring(0, (path.substring(0, path.lastIndexOf('/')).lastIndexOf('/')));
        			String s = sub_path + currentattr.substring(currentattr.indexOf("/"));
        			abs_path.add(s);
//        			System.out.println(s);
        		}
        		else if(currentattr.startsWith(".")||currentattr.startsWith("/")){
        			String sub_path = path.substring(0, path.lastIndexOf('/'));
        			String s = sub_path + currentattr.substring(currentattr.indexOf("/"));
        			abs_path.add(s);
//        			System.out.println(s);
        		}
        		else{
        			String sub_path = path.substring(0, path.lastIndexOf('/')+1);
        			String s = sub_path + currentattr;
        			abs_path.add(s);
//        			System.out.println(s);
        		}
        	}
        }
        @Override
        public void handleStartTag(HTML.Tag t, MutableAttributeSet a,int pos){
        	handleSimpleTag(t,a,pos);
        }
       @Override
        public void handleText(char[]data,int pos){
//    	   System.out.println(Arrays.toString(data));
//    	   if(tag==HTML.Tag.SCRIPT){
//    		   System.out.println(Arrays.toString(data));
//    	   }
            
        }
    }
    public static void main(String[] args){
        try {
            //URL url=new URL("http://rewards.yourright.com.cn/catalog.aspx?bank=HSB");
            //HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            FileInputStream input=new FileInputStream(new File("C:\\Users\\43997120\\Desktop\\test-1.html"));
            FileReader reader=new FileReader(new File("C:\\Users\\43997120\\Desktop\\test-1.html"));
            ImageSrcParser image = new ImageSrcParser("http://rewards.yourright.com.cn/images/top_pic_0.png");
            Scanner scanner=new Scanner(input,"utf-8");
//            while(scanner.hasNextLine()){
//                java.lang.String line=scanner.nextLine();
//                System.out.println(line);
//
//            }
            HTMLEditorKit.Parser parser=new HTMLParse().getParser();
            
            parser.parse(reader,new ParserCB(),true);
            System.out.println(abs_path);
//            for(String s:abs_path){
//            	System.out.println(s);
//            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 