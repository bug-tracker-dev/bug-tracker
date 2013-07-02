package com.abc.bt.common.ws.interceptor;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.binding.soap.Soap11;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.common.logging.LogUtils;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.message.MessageUtils;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.StaxUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class OutFaultInterceptor extends AbstractSoapInterceptor {
	private static final Logger LOG = LogUtils.getL7dLogger(OutFaultInterceptor.class);
	public OutFaultInterceptor() {
		super(Phase.PREPARE_SEND);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		Fault f = (Fault) message.getContent(Exception.class);
		message.put(org.apache.cxf.message.Message.RESPONSE_CODE, f.getStatusCode());
		if (message.getVersion() == Soap11.getInstance()) {
			message.getInterceptorChain().add(Soap11FaultOutInterceptorInternal.INSTANCE);
		} else {
			message.getInterceptorChain().add(Soap12FaultOutInterceptorInternal.INSTANCE);
		}
	}
	
	static String getFaultMessage(SoapMessage message, SoapFault fault) {
        if (message.get("forced.faultstring") != null) {
            return (String) message.get("forced.faultstring");
        }
        boolean config = MessageUtils.getContextualBoolean(message, Message.EXCEPTION_MESSAGE_CAUSE_ENABLED, false);
        if (fault.getMessage() != null) {
            if (config && fault.getCause() != null && fault.getCause().getMessage() != null) {
                return fault.getMessage() + " Caused by: " + fault.getCause().toString();
            } else {
                return fault.getMessage();
            }
        } else if (config && fault.getCause() != null) {
            if (fault.getCause().getMessage() != null) {
                return fault.getCause().getMessage();
            } else {
                return fault.getCause().toString();
            }
        } else {
            return "Fault occurred while processing.";
        }
    }
	
	static class Soap11FaultOutInterceptorInternal extends AbstractSoapInterceptor {
        static final Soap11FaultOutInterceptorInternal INSTANCE = new Soap11FaultOutInterceptorInternal();
        
        public Soap11FaultOutInterceptorInternal() {
            super(Phase.MARSHAL);
        }
        public void handleMessage(SoapMessage message) throws Fault {    
            XMLStreamWriter writer = message.getContent(XMLStreamWriter.class);
            Fault f = (Fault) message.getContent(Exception.class);
    
            SoapFault fault = SoapFault.createFault(f, message.getVersion());
    
            try {
                Map<String, String> namespaces = fault.getNamespaces();
                for (Map.Entry<String, String> e : namespaces.entrySet()) {
                    writer.writeNamespace(e.getKey(), e.getValue());
                }
    
                String ns = message.getVersion().getNamespace();            
                String defaultPrefix = writer.getPrefix(ns);
                if (defaultPrefix == null) {
                    defaultPrefix = StaxUtils.getUniquePrefix(writer, ns, false);
                    writer.writeStartElement(defaultPrefix, "Fault", ns);
                    writer.writeNamespace(defaultPrefix, ns);
                } else {
                    writer.writeStartElement(defaultPrefix, "Fault", ns);
                }
    
                writer.writeStartElement("faultcode");
    
                String codeString = fault.getCodeString(getFaultCodePrefix(writer, fault.getFaultCode()), defaultPrefix);
    
                writer.writeCharacters(codeString);
                writer.writeEndElement();

                writer.writeStartElement("faultstring");
                writer.writeCharacters(getFaultMessage(message, fault));
                writer.writeEndElement();
                prepareStackTrace(message, fault);
    
                if (fault.getRole() != null) {
                    writer.writeStartElement("faultactor");
                    writer.writeCharacters(fault.getRole());
                    writer.writeEndElement();
                }
    
                if (fault.hasDetails()) {
                    Element detail = fault.getDetail();
                    writer.writeStartElement("detail");
                    
                    Node node = detail.getFirstChild();
                    while (node != null) {
                        StaxUtils.writeNode(node, writer, true);
                        node = node.getNextSibling();
                    }
    
                    // Details
                    writer.writeEndElement();
                }
    
                // Fault
                writer.writeEndElement();
            } catch (Exception xe) {
                LOG.log(Level.WARNING, "XML_WRITE_EXC", xe);
                throw f;
            }
        }
    }
	
	static class Soap12FaultOutInterceptorInternal extends AbstractSoapInterceptor {
        static final Soap12FaultOutInterceptorInternal INSTANCE = new Soap12FaultOutInterceptorInternal();
        
        public Soap12FaultOutInterceptorInternal() {
            super(Phase.MARSHAL);
        }
        public void handleMessage(SoapMessage message) throws Fault {
            LOG.info(getClass() + (String) message.get(SoapMessage.CONTENT_TYPE));
            
            XMLStreamWriter writer = message.getContent(XMLStreamWriter.class);
            Fault f = (Fault)message.getContent(Exception.class);
            message.put(org.apache.cxf.message.Message.RESPONSE_CODE, f.getStatusCode());
    
            SoapFault fault = SoapFault.createFault(f, message.getVersion());       

            try {
                Map<String, String> namespaces = fault.getNamespaces();
                for (Map.Entry<String, String> e : namespaces.entrySet()) {
                    writer.writeNamespace(e.getKey(), e.getValue());
                }

                String ns = message.getVersion().getNamespace();
                String defaultPrefix = writer.getPrefix(ns);
                if (defaultPrefix == null) {
                    defaultPrefix = StaxUtils.getUniquePrefix(writer, ns, false);
                    writer.writeStartElement(defaultPrefix, "Fault", ns);
                    writer.writeNamespace(defaultPrefix, ns);
                } else {
                    writer.writeStartElement(defaultPrefix, "Fault", ns);
                }

                writer.writeStartElement(defaultPrefix, "Code", ns);
                writer.writeStartElement(defaultPrefix, "Value", ns);
           
                writer.writeCharacters(fault.getCodeString(getFaultCodePrefix(writer, fault.getFaultCode()), defaultPrefix));
                writer.writeEndElement();

                if (fault.getSubCode() != null) {
                    writer.writeStartElement(defaultPrefix, "Subcode", ns);
                    writer.writeStartElement(defaultPrefix, "Value", ns);
                    writer.writeCharacters(fault.getSubCodeString(getFaultCodePrefix(writer, fault.getSubCode()), defaultPrefix));                
                    writer.writeEndElement();
                    writer.writeEndElement();
                }
                writer.writeEndElement();

                writer.writeStartElement(defaultPrefix, "Reason", ns);
                writer.writeStartElement(defaultPrefix, "Text", ns);
                String lang = f.getLang();
                if (lang == null || lang.equalsIgnoreCase("")) {
                    lang = getLangCode();
                }
                writer.writeAttribute("xml", "http://www.w3.org/XML/1998/namespace", "lang", lang);
                writer.writeCharacters(getFaultMessage(message, fault));
                writer.writeEndElement();
                writer.writeEndElement();

                if (fault.getRole() != null) {
                    writer.writeStartElement(defaultPrefix, "Role", ns);
                    writer.writeCharacters(fault.getRole());
                    writer.writeEndElement();
                }

                prepareStackTrace(message, fault);
                
                if (fault.hasDetails()) {
                    Element detail = fault.getDetail();
                    writer.writeStartElement(defaultPrefix, "Detail", ns);

                    Node node = detail.getFirstChild();
                    while (node != null) {
                        StaxUtils.writeNode(node, writer, true);
                        node = node.getNextSibling();
                    }

                    // Details
                    writer.writeEndElement();
                }

                // Fault
                writer.writeEndElement();
            } catch (Exception xe) {
                LOG.log(Level.WARNING, "XML_WRITE_EXC", xe);
                throw f;
            }
        }

        private String getLangCode() {        
            String code = LOG.getResourceBundle().getLocale().getLanguage();
            if (StringUtils.isEmpty(code)) {
                return "en";
            }
            return code;
        }
    }

}
