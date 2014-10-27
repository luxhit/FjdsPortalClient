package com.fn.taxclientportal.support.rss.parse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fn.taxclientportal.ui.app.TaxAppContext;
import com.fn.taxclientportal.ui.app.TaxConstants;

import android.util.Log;

/**
 * Parses an RSS feed and adds the information to a new RSSFeed object. Has the
 * ability to report progress to a ProgressBar if one is passed to the
 * constructor.
 * 
 * @author Isaac Whitfield
 * @version 06/08/2013
 */
public class DOMParser {

	// Create a new RSS feed
	private RSSFeed feed = new RSSFeed();

	public RSSFeed parseXML(String feedURL)
			throws ParserConfigurationException, SAXException, IOException {

		// Create a new URL
		URL url = null;
		// Find the new URL from the given URL
		url = new URL(feedURL);

		// Create a new DocumentBuilder
		DocumentBuilder builder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();

		// Parse the XML
		Document doc = builder.parse(new InputSource(new InputStreamReader(url
				.openStream(), TaxConstants.Rss.DEFAULT_ENCODING)));
		// Normalize the data
		doc.getDocumentElement().normalize();

		// Get all <item> tags.
		NodeList list = doc.getElementsByTagName("item");
		// Get size of the list
		int length = list.getLength();

		// For all the items in the feed
		for (int i = 0; i < length; i++) {
			// Create a new node of the first item
			Node currentNode = list.item(i);
			// Create a new RSS item
			RSSItem item = new RSSItem();

			// Get the child nodes of the first item
			NodeList nodeChild = currentNode.getChildNodes();
			// Get size of the child list
			int cLength = nodeChild.getLength();

			// For all the children of a node
			for (int j = 1; j < cLength; j = j + 2) {
				// Get the name of the child
				String nodeName = nodeChild.item(j).getNodeName(), nodeString = null;
				// If there is at least one child element
				if (nodeChild.item(j).getFirstChild() != null) {
					// Set the string to be the value of the node
					nodeString = nodeChild.item(j).getFirstChild()
							.getNodeValue();
				}
				// If the string isn't null
				if (nodeString != null) {
					// Set the appropriate value
					if ("title".equals(nodeName)) {
						item.setTitle(nodeString);
					} else if ("content:encoded".equals(nodeName)) {
						item.setDescription(nodeString);
					} else if ("pubDate".equals(nodeName)) {
						item.setDate(nodeString.replace(" +0000", ""));
					} else if ("author".equals(nodeName)
							|| "dc:creator".equals(nodeName)) {
						item.setAuthor(nodeString);
					} else if ("link".equals(nodeName)) {
						item.setURL(nodeString);
					} else if ("thumbnail".equals(nodeName)) {
						item.setThumb(nodeString);
					} else if ("description".equals(nodeName)) {
						Log.d("feed", nodeString);
					}
				}
			}
			// Add the new item to the RSS feed
			feed.addItem(item);
		}
		// Return the feed
		return feed;
	}
}
