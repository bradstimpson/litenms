package com.telioss.litenms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class DomParser {

        //No generics
        List<HashMap<String,String>> myNodes;
        Document dom;


        public DomParser(){
                //create a list to hold the employee objects
                myNodes = new ArrayList<HashMap<String,String>>();
        }

        /**
         * @return the myNodes
         */
        public List<HashMap<String,String>> getMyNodes()
        {
            return myNodes;
        }
        
        /**
         * @return the myNodes at an index
         */
        public HashMap<String,String> getNode(int index)
        {
            return (HashMap<String,String>)myNodes.get(index);
        }
        
        public void parse(String filename) throws FileNotFoundException {
                
                //parse the xml file and get the dom object
                parseXmlFile(new FileInputStream(filename));
                
                //get each employee element and create a Employee object
                parseDocument();
                
                //Iterate through the list and print the data
                printData();
                
        }
        
        public void parse(InputStream is) throws FileNotFoundException {
            
            //parse the xml file and get the dom object
            parseXmlFile(is);
            
            //get each employee element and create a Employee object
            parseDocument();
            
            //Iterate through the list and print the data
            printData();
            
    }
        
        
        public void parseXmlFile(InputStream is) throws FileNotFoundException{
                //get the factory
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                
                try {
                        
                        //Using factory get an instance of document builder
                        DocumentBuilder db = dbf.newDocumentBuilder();
                        
                        //parse using builder to get DOM representation of the XML file
                        dom = db.parse(is);
                        

                }catch(ParserConfigurationException pce) {
                        pce.printStackTrace();
                }catch(SAXException se) {
                        se.printStackTrace();
                }catch(IOException io) {
                    throw new FileNotFoundException("The specified filename was not found");
                }
        }

        
        private void parseDocument(){
                //get the root elememt
                Element docEle = dom.getDocumentElement();
                
                //get a nodelist of <employee> elements
                NodeList nl = docEle.getElementsByTagName("Node");
                if(nl != null && nl.getLength() > 0) {
                        for(int i = 0 ; i < nl.getLength();i++) {
                                
                                //get the employee element
                                Element el = (Element)nl.item(i);
                                
                                //get the Employee object
                                HashMap<String,String> node = getNode(el);
                                
                                //add it to list
                                myNodes.add(node);
                        }
                }
        }


        /**
         * I take an employee element and read the values in, create
         * a HashMap object and return it
         * @param nodeEl
         * @return
         */
        private HashMap<String,String> getNode(Element nodeEl) {
                
                HashMap<String,String> node = new HashMap<String,String>();
                //for each <node> element get text or int values of 
                //all the params

                node.put("id",getTextValue(nodeEl,"id"));
                node.put("name",getTextValue(nodeEl,"name"));
                node.put("status",getTextValue(nodeEl,"status"));
                node.put("alarm",getTextValue(nodeEl,"alarm"));
                node.put("type",getTextValue(nodeEl,"type"));
                node.put("ip",getTextValue(nodeEl,"ip"));
                node.put("latitude",getTextValue(nodeEl,"latitude"));
                node.put("longitude",getTextValue(nodeEl,"longitude"));
                node.put("snr",getTextValue(nodeEl,"snr"));
                node.put("wireless",getTextValue(nodeEl,"wireless"));
                node.put("uptime",getTextValue(nodeEl,"uptime"));
                node.put("gatewayId",getTextValue(nodeEl,"gatewayId"));
                
                return node;
        }


        /**
         * I take a xml element and the tag name, look for the tag and get
         * the text content 
         * i.e for <employee><name>John</name></employee> xml snippet if
         * the Element points to employee node and tagName is name I will return John  
         * @param ele
         * @param tagName
         * @return
         */
        private String getTextValue(Element ele, String tagName) {
                String textVal = null;
                NodeList nl = ele.getElementsByTagName(tagName);
                if(nl != null && nl.getLength() > 0) {
                        Element el = (Element)nl.item(0);
                        textVal = el.getFirstChild().getNodeValue();
                }

                return textVal;
        }

        
        /**
         * Calls getTextValue and returns a int value
         * @param ele
         * @param tagName
         * @return
         */
        private int getIntValue(Element ele, String tagName) {
                //in production application you would catch the exception
                return Integer.parseInt(getTextValue(ele,tagName));
        }
        
        /**
         * Iterate through the list and print the 
         * content to console
         */
        private void printData(){
                
                System.out.println("No of Nodes '" + myNodes.size() + "'.");
                
                Iterator it = myNodes.iterator();
                while(it.hasNext()) {
                        System.out.println(it.next().toString());
                }
        }
}
