package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.logging.LogManager;

public class Hello implements RequestHandler<Integer, String>{
    public static String executeShellCommands( String[] commands ) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder( commands );
            Process process = processBuilder.start( );
            BufferedReader reader = new BufferedReader( new InputStreamReader( process.getInputStream( ) ) );
            BufferedReader errorreader = new BufferedReader( new InputStreamReader( process.getErrorStream( ) ) );

            String line = reader.readLine( );
            String err = errorreader.readLine( );
            StringBuilder output = new StringBuilder();
            StringBuilder errors = new StringBuilder();

            while( line != null ) {
                output.append(line);
                line = reader.readLine( );
            }
            while( err != null ) {
                output.append(err);
                err = errorreader.readLine( );
            }

            reader.close( );
            process.waitFor();
            return output.toString();
        } catch( Exception exception ) {
            exception.printStackTrace( );
            return "error";
        }
    }
    public String handleRequest(Integer myCount, Context context) {
        return executeShellCommands(new String[]{"python","./main.py"});
    }

    public static void main(String[] args) {
        System.out.println(executeShellCommands(new String[]{"python","./python/main.py"}));
    }
}