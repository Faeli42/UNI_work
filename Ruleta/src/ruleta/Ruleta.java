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
        System.out.printf("%nPravidla s??zen?? %n"
                                  + "Existuje n??kolik typ?? ruletov??ch s??zek:%n"
                                  + "1 - P??esn?? (v??hry 1:35)%n"
                                  + "??eton lze vsadit pouze na jednotliv?? ????slo, v??etn?? nuly (0).%n"
                                  + "2 - Rozd??len?? s??zka (v??hry 1:17)%n"
                                  + "??eton lze vsadit na jak??koliv dv?? soused??c?? ????sla, i kolem (0).%n"
                                  + "3 - S??zka na uli??ku (v??hry 1:11)%n"
                                  + "S??zky na uli??ku pokr??vaj?? t??i ????sla. Lze um??stit tak?? na 0, 1 a 2; d??le 0, 2 a 3.%n"
                                  + "4 - Rohov?? s??zka (v??hry 1:8)%n"
                                  + "Rohov?? s??zky pokr??vaj?? ??ty??i ????sla (??tverec). M????ete tak?? vsadit na 0, 1, 2 a 3.%n"
                                  + "5 - S??zka na ??adu (v??hry 1:5)%n"
                                  + "Pokr??v?? dv?? s??zky na uli??ku, tj. ??est r??zn??ch ????sel ve dvou ??ad??ch t??ech ????sel%n"
                                  + "6 - S??zka na sloupec (v??hry 1:2)%n"
                                  + "S??zka polo??en?? do jednoho z t??chto sloupc?? znamen?? dvan??ct ????sel ve sloupci (krom?? 0).%n"
                                  + "7 - S??zka na tucet (v??hry 1:2)%n"
                                  + "S??zka ozna??uje dvan??ct ????sel (1-12, 13-24, 25-36).%n"
                                  + "8 - S??zky ??ern??/??erven??, Sud??/Lich?? a N??zk??/Vysok?? (v??hry 1:1)%n"
                                  + "Pokr??v?? polovinu ????sel ruletov??ho stolu (krom?? 0).%n%n");
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
    } //po zvoleni KONEC HRY vyp????e stav konta
}