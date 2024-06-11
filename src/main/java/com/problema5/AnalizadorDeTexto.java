package com.problema5;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class AnalizadorDeTexto {

    public static void main(String[] args) {
        String nombreArchivo = "src\\main\\java\\com\\problema5\\texto.txt";
        
        
        StringBuilder contenido = new StringBuilder();
        
      
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(" ");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        String texto = contenido.toString().trim();
        
        
        int totalPalabras = contarPalabras(texto);
        int totalOraciones = contarOraciones(texto);
        String palabraMasFrecuente = encontrarPalabraMasFrecuente(texto);
        double longitudPromedioPalabras = calcularLongitudPromedioPalabras(texto, totalPalabras);

       
        System.out.println("Número total de palabras: " + totalPalabras);
        System.out.println("Número total de oraciones: " + totalOraciones);
        System.out.println("Palabra más frecuente: " + palabraMasFrecuente);
        System.out.println("Longitud promedio de las palabras: " + longitudPromedioPalabras);
    }


    private static int contarPalabras(String texto) {
        StringTokenizer tokenizer = new StringTokenizer(texto);
        return tokenizer.countTokens();
    }

    private static int contarOraciones(String texto) {
        String[] oraciones = texto.split("[.!?]+");
        return oraciones.length;
    }

    private static String encontrarPalabraMasFrecuente(String texto) {
        String[] palabras = texto.toLowerCase().split("\\W+");
        Map<String, Integer> conteoPalabras = new HashMap<>();
        
        for (String palabra : palabras) {
            if (!palabra.isEmpty()) {
                conteoPalabras.put(palabra, conteoPalabras.getOrDefault(palabra, 0) + 1);
            }
        }
        
        String palabraMasFrecuente = null;
        int maxConteo = 0;
        for (Map.Entry<String, Integer> entrada : conteoPalabras.entrySet()) {
            if (entrada.getValue() > maxConteo) {
                palabraMasFrecuente = entrada.getKey();
                maxConteo = entrada.getValue();
            }
        }
        
        return palabraMasFrecuente;
    }

    private static double calcularLongitudPromedioPalabras(String texto, int totalPalabras) {
        String[] palabras = texto.split("\\W+");
        int longitudTotal = 0;
        
        for (String palabra : palabras) {
            longitudTotal += palabra.length();
        }
        
        return totalPalabras > 0 ? (double) longitudTotal / totalPalabras : 0.0;
    }
}
