package com.qydev.mifeng.sax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser {
	
	public static void main(String[] args) throws SAXException, IOException{
//		System.out.println(System.getProperty("user.dir"));
//		File f = new File(".");
//		String absolutePath = f.getAbsolutePath();
//		System.out.println(absolutePath);
//		//this.getClass().
//		
//		File f1 = null;
//		try{
//			 f1 = new File("mm/a.text");
//			 if(!f1.getParentFile().exists())
//					f1.getParentFile().mkdirs();
//				//f1.getParent().m
//			 f1.getParentFile().mkdirs();
//			 if(!f1.getParentFile().exists())
//					//f1.getParentFile().mkdirs();
//				    f1.createNewFile();
//		
//		}catch(FileNotFoundException e){
//			System.out.println("exception isisi");
//			f1.getParentFile().mkdirs();
//		}
//		FileOutputStream fos = new FileOutputStream(f1);
//		fos.write("welcome to shanghai".getBytes());
//		f1.createNewFile();
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			 parser = factory.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XmlContentParser xmlContentParser = new XmlContentParser();
		//parser.parse(new File("book.xml"), xmlContentParser);
		//parser.parse(new File(XMLParser.class.getResource("/com/qydev/mifeng/sax/book.xml").getPath()), xmlContentParser);
		parser.parse(new File("src/com/qydev/mifeng/sax/book.xml"), xmlContentParser);
		List<Book> bookList = xmlContentParser.getBookList();
		for(Book book:bookList){
			System.out.println(book);
		}
		
		
	}

	public static class XmlContentParser extends DefaultHandler{
		private Book book;
		private List<Book> books = new ArrayList<Book>();
		private String preTag;
		
		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			System.out.println("start the document: ");
		}
		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
			System.out.println("end the document: ");
			System.out.println("XML文件解析结束...");  
	        System.out.println("结果:" + books);  
		}
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes)
				throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if(qName.equals("book") && book == null){
				book = new Book();
			}
			preTag = qName;
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			super.endElement(uri, localName, qName);
			if("book".equals(qName) && book!=null){
				books.add(book);
				book = null;
			}
			
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			super.characters(ch, start, length);
			 System.out.println("characters-----------");
		        // System.out.println("    ch: " + Arrays.toString(ch) );
		        System.out.println("    ch: " + ch);
		        System.out.println("    start: " + start);
		        System.out.println("    length: " + length);
		        if(book != null && preTag != null && !"book".equals(preTag) && !"shelf".equals(preTag) ){
		        	String data = new String(ch, start, length);
		        	if(!"".equals(data.trim())){
		        		if("name".equals(preTag)){
		        			book.setBookName(data);
		        		}
		        		else if("author".equals(preTag)){
		        			book.setAutor(data);
		        		}
		        		else if("price".equals(preTag)){
		        			book.setPrice(Integer.valueOf(data));
		        		}
		        	}
		        	
		        }
		}
		
		public List<Book> getBookList(){
			return books;
			
		}
		
	}
	public static class Book{
		private String bookName;
		private String autor;
		private int price;
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
			//this.getClass().getResource(name)
		}
		public String getAutor() {
			return autor;
		}
		public void setAutor(String autor) {
			this.autor = autor;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		
		public String toString(){
			return "book name: "+ getBookName()+", book author: "+getAutor()+", book price: "+getPrice();
		}
		
	}
}
