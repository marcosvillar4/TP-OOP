import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(String content){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            if (content != null){
                writer.write(content + ";\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFile(){
        ArrayList<String> puntajes = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = reader.readLine()) != null){
                puntajes.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Archivo no existe. Creando archivo...");
            writeFile(null);
        } finally {
            System.out.println("Archivo leido");
        }

        return puntajes;
    }
}
