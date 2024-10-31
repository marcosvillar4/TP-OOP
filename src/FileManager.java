import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private String filePath;
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void writeFile(String filePath, String content){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter("puntajes.txt"));
            if (content != null){
                ArrayList<String> temp = readFile(filePath);
                StringBuilder prevContent = new StringBuilder();
                for (String s : temp) {
                    prevContent.append(s);
                }
                writer.write( prevContent + ";\n" + content + ";\n");
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> readFile(String filePath){
        ArrayList<String> puntajes = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("puntajes.txt"));
            String line;
            while((line = reader.readLine()) != null){
                puntajes.add(line);
            }
        } catch (IOException e) {
            System.out.println("Archivo no existe. Creando archivo...");
            writeFile(filePath, null);
        } finally {
            System.out.println("Archivo leido");
            return puntajes;
        }
    }
}
