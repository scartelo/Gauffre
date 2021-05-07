/*package Structures;
import java.io.*;
import java.nio.file.*;
import java.util.*;
public class Saves {
    int nb_saves;
    String path;

    public Saves(){
        final String dir = System.getProperty("user.dir");
        String home = System.getProperty("user.dir");
        path=home + File.separator +"res" +File.separator+"Saves";
        File directory=new File(path);
        nb_saves=directory.list().length;
        System.out.println(nb_saves);
    }
    public void read_save(int n_save) {
        String save_path = path + File.separator + "save_" + n_save + ".txt";
        Path p=Paths.get(save_path);

        File save=new File("save_1.txt");
        Scanner myReader = new Scanner(save);
        try {
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch(FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
    public void write_save(Historique H,int l, int c){
        int new_save=nb_saves+1;
        String save_path=path+File.separator+"save_"+new_save+".txt";
    }
}
*/