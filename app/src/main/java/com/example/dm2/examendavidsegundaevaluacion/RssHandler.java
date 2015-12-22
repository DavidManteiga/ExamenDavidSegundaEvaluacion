package com.example.dm2.examendavidsegundaevaluacion;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dm2 on 17/12/2015.
 */
public class RssHandler extends DefaultHandler {
    private List<Tiempo> tiempos;
    private Tiempo tiempoActual;
    private StringBuilder sbTexto;

    public List<Tiempo> getTiempos(){
        return tiempos;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        super.characters(ch, start, length);

        if (this.tiempoActual != null)
            //           builder.append(ch, start, length);
            sbTexto.append(ch, start, length);

    }

    @Override
    public void endElement(String uri, String localName, String name)
            throws SAXException {

        super.endElement(uri, localName, name);

        if (this.tiempoActual != null) {


            if (localName.equals("temperatura")) {
                tiempoActual.setTemperatura(sbTexto.toString());
            }else if (localName.equals("hora")) {
                tiempoActual.setHora(sbTexto.toString());
            }

            sbTexto.setLength(0);
        }
    }

    @Override
    public void startDocument() throws SAXException {

        super.startDocument();

        tiempos = new ArrayList<Tiempo>();
        sbTexto = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName,
                             String name, Attributes attributes) throws SAXException {

        super.startElement(uri, localName, name, attributes);

        if (localName.equals("datos")) {
            tiempoActual = new Tiempo();
        }
    }


}

