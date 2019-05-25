package com.liugawaheliujinnao.singleFunction.wechatEnterpriseAccount.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.jaxen.JaxenException;
import org.jaxen.SimpleNamespaceContext;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;
import org.xml.sax.InputSource;

import java.io.*;
import java.util.*;

/**
 * @Description:
 * @Author: LiugawaHeLiujinnao
 * @Date: 2019-05-25
 */
public class XMLParse {

    private List list = null;

    private Document doc = null;

    private File file = null;

    private String fileName = null;

    private String filePath = "";

    private Iterator iter = null;

    private int sortType = 1;

    private Element el = null;

    private Element rootEl = null;

    private XMLWriter writer = null;

    private OutputFormat format = null;

    private String encodStyle = "UTF-8";

    private int elCount = -1;

    public XMLParse() {
    }

    // 解析xml文件
    public boolean parseXML(String filePath, String fileName, String encodStyle) {
        this.encodStyle = encodStyle;
        this.filePath = filePath;
        return parseXML(fileName);
    }

    public boolean parseXML(String fileName, String encodStyle) {
        this.encodStyle = encodStyle;
        return parseXML(fileName);
    }

    public boolean parseXML(String fileName) {
        this.fileName = fileName;
        if (encodStyle != null) {
            setFormat(encodStyle);
        }
        setFile();
        return parseXML();
    }

    public boolean parseXML(Reader read) {
        try {
            SAXReader saxReader = new SAXReader();
            this.setDoc(saxReader.read(new InputSource(read)));
            this.getDoc().setXMLEncoding(this.encodStyle);
            this.setRootEl(this.getDoc().getRootElement());
            return true;
        } catch (DocumentException doce) {
            // doce.printStackTrace();
            return false;
        }
    }

    public boolean parseXMLStr(String xmlStr) throws DocumentException {
        try {
            SAXReader saxReader = new SAXReader();
            //saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            //saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            //saxReader.setFeature("http://apache.org/xml/features/disallow-doctype-decl",true);
            //saxReader.setValidation(false);
            //saxReader.setIncludeExternalDTDDeclarations(include);
            saxReader.setEntityResolver(new IgnoreDTDEntityResolver());
            this.setDoc(saxReader.read(new StringReader(xmlStr)));
            this.getDoc().setXMLEncoding(this.encodStyle);
            this.setRootEl(this.getDoc().getRootElement());
            return true;
        } catch (DocumentException doce) {
            System.err.println(doce);
            throw doce;
        }
    }

    public void createDocument() {
        createDocument("xml-root");
    }

    public void createDocument(String elementName) {
        this.setDoc(DocumentHelper.createDocument(DocumentHelper
                .createElement(elementName)));
        this.search("//" + elementName);
        this.next();
    }

    public Element createElement(String elementName) {
        return DocumentHelper.createElement(elementName);
    }

    public void setFile(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        setFile();
    }

    public void setFile(String fileName) {
        this.fileName = fileName;
        setFile();
    }

    public void setFile() {
        if (fileName != null) {
            file = new File(filePath + fileName + ".xml");
        }
    }

    public boolean parseXML() {
        try {
            SAXReader saxReader = new SAXReader();
            if (doc != null) {
                doc = null;
            }
            doc = saxReader.read(file);
            doc.setXMLEncoding(encodStyle);
            this.setRootEl(this.doc.getRootElement());
            return true;
        } catch (DocumentException doce) {
            doce.printStackTrace();
            return false;
        }
    }

    public boolean parseXML(InputStream is) {
        try {
            SAXReader saxReader = new SAXReader();
            if (doc != null) {
                doc = null;
            }
            doc = saxReader.read(is);
            doc.setXMLEncoding(encodStyle);
            this.setRootEl(this.doc.getRootElement());
            return true;
        } catch (DocumentException doce) {
            doce.printStackTrace();
            return false;
        }
    }

    public boolean search(String XPath) {
        list = doc.selectNodes(XPath);
        if (list != null) {
            this.elCount = this.list.size();
            this.setIter(list.iterator());
            return true;
        } else {
            return false;
        }
    }

    public boolean searchWithNamespace(String namespace,String xPath){
        HashMap map = new HashMap();
        map.put( "ns", namespace);

        XPath xpath = null;
        try {
            xpath = new Dom4jXPath( "//ns:"+xPath);
            xpath.setNamespaceContext( new SimpleNamespaceContext( map));
            list = xpath.selectNodes(doc);
            if (list != null) {
                this.elCount = this.list.size();
                this.setIter(list.iterator());
                return true;
            } else {
                return false;
            }
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean next() {
        if (iter.hasNext()) {
            el = (Element) iter.next();
            return true;
        }
        return false;
    }

    public String getNodeValue(String nodeName) {
        Iterator subIter = el.elementIterator(nodeName);
        if (subIter.hasNext()) {
            return ((Element) subIter.next()).getTextTrim();
        } else {
            return "";
        }
    }

    public String getText() {
        return el.getTextTrim();
    }

    public String getHtmlText() {
        return el.getText();
    }

    public void setText(String txt) {
        el.setText(txt);
    }

    public String getAttributeValue(String attributeName) {
        return el.attributeValue(attributeName);
    }

    public String getElementName() {
        return el.getName();
    }

    // 元素排序
    @SuppressWarnings("unchecked")
    public void sort(final String attributeName) {
        if (this.list != null) {
            Collections.sort(list, new Comparator() {
                public int compare(Object o1, Object o2) {
                    Object i1 = null;
                    Object i2 = null;
                    if (sortType == 1) {
                        i1 = Integer.valueOf(((Element) o1)
                                .attributeValue(attributeName));
                        i2 = Integer.valueOf(((Element) o2)
                                .attributeValue(attributeName));
                    } else {
                        i1 = ((Element) o1).attributeValue(attributeName);
                        i2 = ((Element) o2).attributeValue(attributeName);
                    }
                    if (i1 == i2) {
                        return 0;
                    } else if (i1 instanceof Comparable) {
                        Comparable c1 = (Comparable) i1;
                        return c1.compareTo(i2);
                    } else if (i1 == null) {
                        return 1;
                    } else if (i2 == null) {
                        return -1;
                    } else {
                        return i1.equals(i2) ? 0 : (-1);
                    }
                }
            });
            this.setIter(list.iterator());
        }
    }

    public List getAllSubElementList() {
        return el.elements();
    }

    public List getSubElementList(String elName) {
        return el.elements(elName);
    }

    public Element getSubElement(String elName) {
        return el.element(elName);
    }

    public String getFirstSubElementAttr(String subElName, String attributeName) {
        List list = el.elements(subElName);
        if (list.size() != 0) {
            return ((Element) list.get(0)).attributeValue(attributeName);
        } else {
            return "";
        }
    }

    public void setAttribute(String name, String value) {
        el.addAttribute(name, value);
    }

    public void addElement(Element newEl) {
        el.add(newEl);
    }

    public Element addElement(String name) {
        return el.addElement(name);
    }

    public boolean removeElement(Element rel) {
        return el.remove(rel);
    }

    public void setFormat(String encodStyle) {
        format = OutputFormat.createPrettyPrint();
        format.setEncoding(encodStyle);
    }

    public boolean XMLOutPut() {
        try {
            if (!file.isFile()) {
                file.createNewFile();
            }
            if (format == null) {
                format = OutputFormat.createPrettyPrint();
                format.setEncoding(encodStyle);
            }
            writer = new XMLWriter(new FileWriter(file), format);
            writer.write(doc);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            // writeLog(e);
            return false;
        }
    }

    public void show(String attname) {
        if (list != null) {
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                Element el = (Element) iter.next();
                System.out.println(el.attributeValue(attname));
            }
        }
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public int getSortType() {
        return sortType;
    }

    public void setSortType(int sortType) {
        this.sortType = sortType;
    }

    public Iterator getIter() {
        return iter;
    }

    public void setIter(Iterator iter) {
        this.iter = iter;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public OutputFormat getFormat() {
        return format;
    }

    public void setFormat(OutputFormat format) {
        this.format = format;
    }

    public String getEncodStyle() {
        return encodStyle;
    }

    public void setEncodStyle(String encodStyle) {
        this.encodStyle = encodStyle;
    }

    public Element getEl() {
        return el;
    }

    public void setEl(Element el) {
        this.el = el;
    }

    public int getElCount() {
        return elCount;
    }

    public void setElCount(int elCount) {
        this.elCount = elCount;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Element getRootEl() {
        return rootEl;
    }

    public void setRootEl(Element rootEl) {
        this.rootEl = rootEl;
    }
}
