/***************************** BEGIN LICENSE BLOCK ***************************

 The contents of this file are subject to the Mozilla Public License Version
 1.1 (the "License"); you may not use this file except in compliance with
 the License. You may obtain a copy of the License at
 http://www.mozilla.org/MPL/MPL-1.1.html
 
 Software distributed under the License is distributed on an "AS IS" basis,
 WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 for the specific language governing rights and limitations under the License.
 
 The Original Code is the "OGC Service Framework".
 
 The Initial Developer of the Original Code is the VAST team at the
 
 Contributor(s): 
    Alexandre Robin <alexandre.robin@spotimage.fr>
 
******************************* END LICENSE BLOCK ***************************/

package org.vast.ows.swe;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.vast.ogc.OGCRegistry;
import org.vast.ows.OWSUtils;
import org.vast.xml.DOMHelper;
import org.vast.xml.QName;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 * <p><b>Title:</b><br/>
 * SWES Common Reader V20
 * </p>
 *
 * <p><b>Description:</b><br/>
 * Utility methods to read SWES common structures
 * </p>
 *
 * <p>Copyright (c) 2010</p>
 * @author Alexandre Robin <alexandre.robin@spotimage.fr>
 * @date 22 jun. 10
 * @version 1.0
 */
public class SWESUtils
{
		
	public static Map<QName, Object> readXMLExtensions(DOMHelper dom, Element parentElt)
	{
		NodeList extensions = dom.getElements(parentElt, "extension");
		Map<QName, Object> extList = new HashMap<QName, Object>();
		
		for (int i=0; i<extensions.getLength(); i++)
		{
			Element extElt = (Element)extensions.item(i);
			Element contentElt = dom.getFirstChildElement(extElt);
			contentElt = (Element)extElt.removeChild(contentElt);
			QName extName = new QName(contentElt.getNamespaceURI(), contentElt.getLocalName()); 
			extList.put(extName, contentElt);
		}
		
		return extList;
	}
	
	
	public static void writeXMLExtensions(DOMHelper dom, Element parentElt, String prefix, String nsUri, Map<QName, Object> extObjs)
	{
		if (extObjs == null)
			return;
		
		dom.addUserPrefix(prefix, nsUri);
    	Iterator<Object> it = extObjs.values().iterator();
		while (it.hasNext())
        {
        	Object obj = it.next();
			if (obj instanceof Element)
        	{
        		Element extContent = (Element)obj;
        		dom.getDocument().adoptNode(extContent);
        		Element extElt = dom.createElement(prefix + ":extension");
        		extElt.appendChild(extContent);
        		parentElt.appendChild(extElt);
        	}
        }
	}
	
	
	public static void writeXMLExtensions(DOMHelper dom, Element parentElt, String version, Map<QName, Object> extObjs)
	{
		String swesUri = OGCRegistry.getNamespaceURI(OWSUtils.SWES, version);
		writeXMLExtensions(dom, parentElt, "swes", swesUri, extObjs);
	}
	
	
	public static Element findExtension(String nsUri, String localName, Map<QName, Object> extObjs)
	{
		QName qname = new QName(nsUri, localName);
		Object obj = extObjs.get(qname);
		return (Element)obj;
	}
}