package com.example.dm2.examendavidsegundaevaluacion;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.sax.StartElementListener;
import android.util.Xml;

import org.xml.sax.Attributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dm2 on 17/12/2015.
 */
public class RssParserSax2
{
    private URL rssUrl;
    private Tiempo tiempoActual;

    public RssParserSax2(String url)
    {
        try
        {
            this.rssUrl = new URL(url);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public List<Tiempo> parse()
    {



        final List<Tiempo> tiempo = new ArrayList<Tiempo>();

        RootElement root = new RootElement("datos");
        Element pronostico_horas = root.getChild("pronostico_horas");
        Element hora = pronostico_horas.getChild("hora");




        hora.setStartElementListener(new StartElementListener() {
            public void start(Attributes attrs) {
                tiempoActual = new Tiempo();

            }
        });

        hora.setEndElementListener(new EndElementListener() {
            public void end() {
                tiempo.add(tiempoActual);
            }
        });



        hora.getChild("temperatura").setEndTextElementListener(
                new EndTextElementListener() {
                    public void end(String body) {
                        tiempoActual.setTemperatura(body);
                    }
                });


        hora.getChild("hora_datos").setEndTextElementListener(
                new EndTextElementListener() {
                    public void end(String body) {
                        tiempoActual.setHora(body);
                    }
                });



        try
        {
            /*
            Xml.parse(this.getInputStream(),
                    Xml.Encoding.ISO_8859_1,
                    root.getContentHandler());
            */

            Xml.parse(this.getInputStream(),
                    Xml.Encoding.UTF_8,
                    root.getContentHandler());

        }
        catch (Exception ex)

        {
            throw new RuntimeException(ex);
        }

        return tiempo;
    }

    private InputStream getInputStream()
    {
        try
        {
            return rssUrl.openConnection().getInputStream();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}


