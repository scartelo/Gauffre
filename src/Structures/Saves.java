package Structures;
import java.io.*;


public class Saves {
    int nb_saves;
    String path;

    public Saves(){
        final String dir = System.getProperty("user.dir");
        String home = System.getProperty("user.dir");
        path=home + File.separator +"res" +File.separator+"Saves";
        File directory=new File(path);
        nb_saves=directory.list().length;
    }
    public void read_save(int n_save) {
        String save_path = path + File.separator + "save_" + n_save + ".txt";
        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(save_path)));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

         */
    }
    public void write_save(Historique H,int l, int c){
        int new_save=nb_saves+1;
        String save_path=path+File.separator+"save_"+new_save+".txt";
    }
}
