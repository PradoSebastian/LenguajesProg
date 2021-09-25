/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author aulasingenieria
 */
public class Regex {
    
    private ArrayList<Matcher> matchers;
    
    public static void main(String[] args) 
    {
        Regex r = new Regex();
        String nombreArchivo = "texto.txt";

        try
        {
            String cadena;
            FileReader f = null;
            try
            {
                f = new FileReader(nombreArchivo);
            }
            catch (FileNotFoundException ex)
            {
                Logger.getLogger(Regex.class.getName()).log(Level.SEVERE, null, ex);
            }

            BufferedReader b = new BufferedReader(f);
            boolean bandera = false;
            while ((cadena = b.readLine()) != null)
            {
                for (Matcher m : r.matchers) 
                {
                    if(r.validar(cadena, m))
                    {
                        System.out.println(cadena + ": valido");
                        bandera = true;
                        break;
                    }
                    
                }
                if(!bandera)
                {
                    System.out.println(cadena + ": No valido");
                }
                bandera = false;
            }
               
            b.close();
        }
        catch (IOException ex)
        {
            Logger.getLogger(Regex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Regex() 
    {
        this.matchers =new ArrayList<>();
        crearMatchers();
    }
    
    public void crearMatchers()
    {
        String s = "";
        //PalabraReservada
        Pattern pattern = Pattern.compile("^(def)|(if)|(else)|(for)|(while)$");
        Matcher matcher = pattern.matcher(s);
        matchers.add(matcher);
        //Booleano
        Pattern pattern1 = Pattern.compile("^(True)|(False)$");
        Matcher matcher1 = pattern1.matcher(s);
        matchers.add(matcher1);
        //Entero
        Pattern pattern2 = Pattern.compile("^([-+]?[\\d]+)$");
        Matcher matcher2 = pattern2.matcher(s);
        matchers.add(matcher2);
        //Flotante
        Pattern pattern3 = Pattern.compile("^([-+]?[\\d]*(\\.)[\\d]+)$");
        Matcher matcher3 = pattern3.matcher(s);
        matchers.add(matcher3);
        //Cadena de caracteres
        Pattern pattern4 = Pattern.compile("^(((\").+(\"))|((\').+(\')))$");
        Matcher matcher4 = pattern4.matcher(s);
        matchers.add(matcher4);
        //DD/MM/YYYY
        Pattern pattern5 = Pattern.compile("^((0?[1-9]|([1-2][\\d])|([3][0-1]))/((0?[1-9])|([1][0-2]))/[\\d]{4})$");
        Matcher matcher5 = pattern5.matcher(s);
        matchers.add(matcher5);
        //Variable
        Pattern pattern6 = Pattern.compile("^([a-zA-Z_][\\w]*)$");
        Matcher matcher6 = pattern6.matcher(s);
        matchers.add(matcher6);
        
    }
    
    public boolean validar(String s, Matcher m)
    {
        m.reset(s);
        return m.matches();
    }

    
    
    
    
}
