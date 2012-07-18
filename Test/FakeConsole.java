import java.util.ArrayList;
import java.util.List;

public class FakeConsole implements IConsole{
    private StringBuilder outputs = new StringBuilder();
    private List<String> inputs = new ArrayList<String>();

    @Override
    public void writeLine(String message) {
        outputs.append(message);
    }

    @Override
    public String readLine() {
        if(inputs.isEmpty()){
            throw new IllegalStateException();
        }
        return inputs.remove(0);
    }

    public void addInputs(String... inputs){
        for (String input : inputs) {
            this.inputs.add(input);
        }
    }

    public String getOutput(){
        return outputs.toString();
    }
}
