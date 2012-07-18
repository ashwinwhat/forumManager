import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainConsole implements IConsole{
    @Override
    public void writeLine(String message) {
        System.out.println(message);
    }

    @Override
    public String readLine() {
        String message = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            message = reader.readLine();
        }catch (IOException error){
            error.printStackTrace();
        }
        return message;
    }
}
