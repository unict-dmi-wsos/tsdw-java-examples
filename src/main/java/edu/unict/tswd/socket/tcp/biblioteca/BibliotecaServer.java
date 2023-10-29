package edu.unict.tswd.socket.tcp.biblioteca;

/*
    Realizzare un server che tiene traccia della disponibilità di 10 libri, che possono essere disponibili o in prestito;
    riceve delle richieste da parte dei client del tipo "titolo del libro"
    e risponde "Disponibile", "In prestito" o "Inesistente" a seconda del titolo richiesto.
*/

import java.net.*;
import java.util.Map;
import java.io.*;

public class BibliotecaServer {
    public static final int PORT = 8080;
    private static final Map<String, String> books = Map.of("1984", "Disponibile", 
                                                    "Il signore degli anelli", "In prestito", 
                                                    "Harry Potter", "Disponibile", 
                                                    "Il codice da Vinci", "In prestito", 
                                                    "Il nome della rosa", "Disponibile", 
                                                    "Il piccolo principe", "In prestito", 
                                                    "Il vecchio e il mare", "In prestito", 
                                                    "Il gattopardo", "Disponibile", 
                                                    "Il barone rampante", "Disponibile", 
                                                    "Il fu Mattia Pascal", "In prestito");

    public static void main(String[] args) throws IOException {
        ServerSocket socket = null;
        try{
            socket = new ServerSocket(BibliotecaServer.PORT);
        } catch(IOException e){
            System.out.println("ServerSocket failed");

        }
        PrintWriter out = null;
        BufferedReader in = null;
        Socket clientSocket = null;
        
        try{
            clientSocket = socket.accept();

            System.out.println("Connetion " + clientSocket + " accepted");

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //recv
            out = new PrintWriter(clientSocket.getOutputStream(), true); // send

            String buffer = in.readLine(); // name of the book
            System.out.println("Book requested: " + buffer);

            if(books.get(buffer) == null)
                out.println("Non disponibile");
            else
                out.println(books.get(buffer));
        } catch (IOException e){
            System.out.println("Accept failed");
            System.exit(1);
        }

        try{
            in.close();
            out.close();
            clientSocket.close();
        } catch(IOException e){
            System.out.println("Close failed");
        }
    }
}
