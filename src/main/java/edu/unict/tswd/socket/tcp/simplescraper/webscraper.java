package edu.unict.tswd.socket.tcp.simplescraper;
// Credits https://www.infoworld.com/article/2853780/socket-programming-for-scalable-systems.html

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class webscraper {
    public static void main( String[] args )
    {
        if( args.length < 2 )
        {
            System.out.println( "Usage: webscraper <server> <path>" );
            System.exit( 0 );
        }
        String server = args[ 0 ];
        String path = args[ 1 ];

        System.out.println( "Loading contents of URL: " + server + path);

        try
        {
            // Connect to the server
            Socket socket = new Socket( server, 80 );

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

            // Follow the HTTP protocol of GET <path> HTTP/1.1
            System.out.print( "GET " + path + " HTTP/1.1\r\n" );
            out.print( "GET " + path + " HTTP/1.1\r\n" );
            // add header host
            System.out.print( "Host: " + server+"\r\n");
            out.print( "Host: " + server+"\r\n");
            out.print("\r\n");
            System.out.println( "Get Response");
            // Read data from the server until we finish reading the document
            String line = in.readLine();
            while( line != null )
            {
                System.out.println( line );
                line = in.readLine();
            }

            // Close our streams
            in.close();
            out.close();
            socket.close();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
