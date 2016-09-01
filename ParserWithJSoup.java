package pjy;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ParserWithJSoup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file=new File("C:/Users/43997118/Desktop/test.html");
		Document doc=null;
		try {
			doc=Jsoup.parse(file,"utf-8");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("what happen?");
		}
		Elements imgs=doc.getElementsByTag("img");
		for(Element img:imgs){
			//System.out.format("url: %s,text: %s", img.absUrl("src"),img.text());
			System.out.println(img.tagName()+": ");
		}
	}

}
