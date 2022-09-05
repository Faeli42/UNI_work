package ruleta;
import java.util.Scanner;
/*import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import java.util.logging.Level;
import java.util.logging.Logger;*/
/**
 * @author mahdal
 */
public class Ruleta {
    /**
     * @param args the command line arguments
     */
   public static int penize = 500;
   public static Scanner scan = new Scanner(System.in);
   public static int penizezacatek=penize;
 public static void main(String[] args){
    /* boolean totoznost=false;
     System.out.println("Prosim zadejte sve uzivatelske jmeno:");
     String jmeno = scan.next();
     try{
        DataInputStream dis = new DataInputStream(new FileInputStream("ucty.txt"));
        String s = dis.readUTF();
        while(s!=null){
            s = dis.readUTF(); 
            if(s.equals(jmeno)){totoznost=true;}
            if(!s.equals(jmeno)){break;}
        }
     }catch (IOException ex){}
     if(totoznost==true){
         System.out.println("Uspesne jste se prihlasil ke svemu uctu");
     }
     else{
         try {
             System.out.println("Tento ucet jeste neexistuje, bude proto vytvoren.");
             DataOutputStream dos;
             dos = new DataOutputStream(new FileOutputStream("ucty.txt"));
             dos.writeUTF(jmeno);
             }catch (IOException ex) {Logger.getLogger(Ruleta.class.getName()).log(Level.SEVERE, null, ex);} 
     }*/
    
    while(penize!=0){ // cyklus hry, bezi dokud ma uzivatel na konte penize
        
            System.out.println("Na svem konte mate aktualne "+penize+" korun.");
            System.out.println("Moznosti: 1 = VSADIT , 2 = PRAVIDLA , 3 = VZHLED RULETY , 4 = UKONCIT HRU");
            System.out.println("Prosim, napiste vasi volbu: ");
            int moznost = scan.nextInt(); // uzivatel voli, co si preje ve hre delat (VSADIT, ...)
            System.out.println("------------------------------------------------------------");
        if     (moznost == 1) {Sazeni.Sazeni();} //odkazuje na soubor sazeni, kde jsou metody
        else if(moznost == 2) {Pravidla();} //metoda pro vypis pravidel
        else if(moznost == 3) {VypisFullCloth();} // metoda pro vypis vzhledu rulety
        else if(moznost == 4) {KonecHry(); break;} // metoda pro vypis konce hry
        else{System.out.println("Neplatna volba! Musite zadat cislo zvolene moznosti.");
        System.out.println("------------------------------------------------------------");}
        }
    
    if(penize==0)/*uzivateli dosly ve hre penize*/{
            System.out.println("Prohral jste vsechny penize! Prosim, restartujte hru.");
        }
    }   
    public static void Pravidla(){
        System.out.printf("%nPravidla sázení %n"
                                  + "Existuje několik typů ruletových sázek:%n"
                                  + "1 - Přesné (výhry 1:35)%n"
                                  + "Žeton lze vsadit pouze na jednotlivé číslo, včetně nuly (0).%n"
                                  + "2 - Rozdělená sázka (výhry 1:17)%n"
                                  + "Žeton lze vsadit na jakákoliv dvě sousedící čísla, i kolem (0).%n"
                                  + "3 - Sázka na uličku (výhry 1:11)%n"
                                  + "Sázky na uličku pokrývají tři čísla. Lze umístit také na 0, 1 a 2; dále 0, 2 a 3.%n"
                                  + "4 - Rohová sázka (výhry 1:8)%n"
                                  + "Rohové sázky pokrývají čtyři čísla (čtverec). Můžete také vsadit na 0, 1, 2 a 3.%n"
                                  + "5 - Sázka na řadu (výhry 1:5)%n"
                                  + "Pokrývá dvě sázky na uličku, tj. šest různých čísel ve dvou řadách třech čísel%n"
                                  + "6 - Sázka na sloupec (výhry 1:2)%n"
                                  + "Sázka položená do jednoho z těchto sloupců znamená dvanáct čísel ve sloupci (kromě 0).%n"
                                  + "7 - Sázka na tucet (výhry 1:2)%n"
                                  + "Sázka označuje dvanáct čísel (1-12, 13-24, 25-36).%n"
                                  + "8 - Sázky Černá/Červená, Sudá/Lichá a Nízká/Vysoká (výhry 1:1)%n"
                                  + "Pokrývá polovinu čísel ruletového stolu (kromě 0).%n%n");
        System.out.println("------------------------------------------------------------");
    } // vypisuje na obrazovku pravidla
    public static void VypisFullCloth(){
        System.out.printf(
                  "    0                  b=cerna(black)%n"
                + "1r  2b  3r             r=cervena(red)%n"
                + "4b  5r  6b%n"
                + "7r  8b  9r%n"
                + "10b 11b 12r%n"
                + "13b 14r 15b%n"
                + "16r 17b 18r%n"
                + "19r 20b 21r%n"
                + "22b 23r 24b%n"
                + "25r 26b 27r%n"
                + "28b 29b 30r%n"
                + "31b 32r 33b%n"
                + "34r 35b 36r%n"
                + "------------------------------------------------------------%n");
    } // vypisuje vzhled rulety na obrazovku
    public static void KonecHry(){
        System.out.println("Konec hry.");
        if(penizezacatek>penize){
            System.out.println("Do hry jste vstupoval s "+penizezacatek+" korunami, na Vasem konte zbylo "+penize+".");
        }
        if(penizezacatek<penize){
        System.out.println("Vyhral jste celkem "+(penize-penizezacatek)+" na Vasem konte je nyni "+penize+"(puvodne "+penizezacatek+").");
        }
    } //po zvoleni KONEC HRY vypíše stav konta
}