package edu.unict.tswd.socket.tcp.finger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class finger {
    public static void main( String[] args )
    {
        if( args.length < 2 )
        {
            System.out.println( "Usage: finger <server> <name>" );
            System.exit( 0 );
        }
        String server = args[ 0 ];
        String name = args[ 1 ];

        System.out.println( "Loading contents of URL: " + server + name);

        try
        {
            // Connect to the server
            Socket socket = new Socket(server,79);

            // Create input and output streams to read from and write to the server
            PrintStream out = new PrintStream( socket.getOutputStream() );
            BufferedReader in = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );

     
            out.print( name+"\r\n" );
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
