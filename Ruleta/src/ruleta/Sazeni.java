package ruleta;
/**
 * @author mahdal
 */
public class Sazeni {
    
    public static void Sazeni(){
       System.out.printf("Nyni si zvolte typ sazeni:%n"
                + "1 = presne , 2 = rozdelena sazka , 3 = sazka na ulicku%n"
                + "4 = rohova sazka , 5 = sazka na radu , 6 = sazka na sloupec%n"
                + "7 = sazka na tucet , 8 = padesat na padesat%n");
       int volba = Ruleta.scan.nextInt();
       System.out.println("------------------------------------------------------------");
       switch(volba){
       case 1: Sazeni.SazkaPresna();break;
       case 2: Sazeni.SazkaRozdelena();break;
       case 3: Sazeni.SazkaNaUlicku();break;
       case 4: Sazeni.SazkaRohova();break;
       case 5: Sazeni.SazkaNaRadu();break;
       case 6: Sazeni.SazkaNaSloupec();break;
       case 7: Sazeni.SazkaNaTucet();break;
       case 8: Sazeni.SazkaNaPadesat();break;
       default: System.out.println("Chyba! Cislo musi byt mezi 1 a 8.");break;}
       System.out.println("------------------------------------------------------------");
    } // uzivatel se sem dostane po volbe "VSADIT" v cyklu hry, voli si zde mezi jednotlivymi sazkami
    
    public static void SazkaPresna(){
        System.out.println("Zvolili jste si PRESNOU sazku. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0)/*kontrola vsazene hodnoty*/{
            System.out.println("Na jake cislo si prejete vsadit (0-36)?");
            int nacislo = Ruleta.scan.nextInt();
            int ruleta = Ruleta();
            if(nacislo >= 0 && nacislo <= 36)/*vsadit od 1 do 36*/{
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(nacislo==ruleta){
                    Ruleta.penize += 35*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 35*sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
                if(Ruleta.penize!=0)/*opakovat?*/{
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaPresna();}
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
            System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaRozdelena(){
        System.out.println("Zvolili jste si ROZDELENOU sazku. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0)/*kontrola vsazene hodnoty*/{
            System.out.println("Na jaka dve sousedni cisla si prejete vsadit, piste nejdrive nizsi z nich (0-36)?");
            int nacislo = Ruleta.scan.nextInt();
            int nacislo2 = Ruleta.scan.nextInt();
            if((nacislo >= 0 && nacislo <=36 && nacislo2 >= 0 && nacislo2 <=36)/*vsadit od 1 do 36*/ 
                    &&(
                    (nacislo==0 && (nacislo2==1 || nacislo2==2 || nacislo2==3))) // vsadit spravne
                    || (nacislo%3!=0 && (nacislo2==(nacislo+3) || nacislo2==(nacislo+1)))
                    || (nacislo%3==0 && (nacislo2==(nacislo+3)))
                      )
            {
                int ruleta = Ruleta();
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(nacislo == ruleta || nacislo2 == ruleta)/*vyhra*/{
                    Ruleta.penize += 17*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 17*sazka +" korun.");}
                else /*prohra*/{
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
                if(Ruleta.penize!=0)/*dotaz na opakovani*/{
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaRozdelena();}
                }  
            }
            else{
                System.out.println("Takto nelze vsadit!"); // nebyly splneny podminky pro danou sazku
            }
           
        }
        else{
            System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaNaUlicku(){
        System.out.println("Zvolili jste sazku NA ULICKU. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
            System.out.println("Zvolte nejnizsi cislo ulicky (0,1,4,7,10,13,16,19,22,25,28,31,34)");
            int nacislo = Ruleta.scan.nextInt();
            if(nacislo >= 0 && nacislo <= 36)/*vsadit od 1 do 36*/{
                boolean vyhra = false;
                int ruleta = Ruleta();
                if(nacislo==0){
                    System.out.println("Vyberete moznost: 1 = 0,1,2 nebo 2 = 0,2,3");
                    int nula = Ruleta.scan.nextInt();
                    switch(nula){
                        case 1: if(ruleta==0 || ruleta==1 || ruleta==2){vyhra = true;}; break;
                        case 2: if(ruleta==0 || ruleta==2 || ruleta==3){vyhra = true;}; break;
                        default: System.out.println("Neplatna volba!");
                    }
                }
                else if(nacislo%3==1 && nacislo < 35){
                     if(ruleta==nacislo || ruleta == (nacislo+1) || ruleta == (nacislo+2)){vyhra = true;}
                }       
                else {
                    System.out.println("Toto neni zacatek ulicky!");
                }
                if(nacislo==0 || (nacislo%3==1 && nacislo < 35)){
                    System.out.println("Na rulete padlo: "+ruleta+".");
                    if(vyhra==true){
                        Ruleta.penize += 11*sazka;
                        System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 11*sazka +" korun.");
                    }
                    else {
                        Ruleta.penize -= sazka;
                        System.out.println("Nevyhral jsi.");
                    }
                    if(Ruleta.penize!=0){  System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaNaUlicku();}
                    }
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
            System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaRohova(){
        System.out.println("Zvolili jste si ROHOVOU SAZKU. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
            System.out.println("Napiste nejnizsi cislo ctverce, na ktery si prejete vsadit (0 = 0,1,2,3)?");
            int nacislo= Ruleta.scan.nextInt();
            if(nacislo>=0 && nacislo<=35 && (nacislo==0 ||nacislo%3!=0)){
                int ruleta = Ruleta();
                boolean vyhra = false;
                if(nacislo==0 && (ruleta==0 || ruleta==1 || ruleta==2 || ruleta==3)){vyhra=true;}
                if(nacislo!=0 && (ruleta==nacislo || ruleta == (nacislo+1) || ruleta == (nacislo+3) || ruleta == (nacislo+4))){vyhra=true;}
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(vyhra==true){
                    Ruleta.penize += 35*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 8*sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
            System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaNaRadu(){
        System.out.println("Zvolili jste sazku NA RADU. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
            System.out.println("Zvolte nejnizsi cislo rady (0,1,4,7,10,13,16,19,22,25,28,31)");
            int nacislo = Ruleta.scan.nextInt();
            if(nacislo>=0 && nacislo <=36){
                boolean vyhra = false;
                int ruleta = Ruleta();
                if(nacislo==0){
                    System.out.println("Vyberete moznost: 1 = 0,1,2,4,5,6 nebo 2 = 0,2,3,4,5,6");
                    int nula = Ruleta.scan.nextInt();
                    switch(nula){
                        case 1: if(ruleta>=0 && ruleta <=6 && ruleta != 3){vyhra = true;}; break;
                        case 2: if(ruleta>=0 && ruleta <=6 && ruleta != 1){vyhra = true;}; break;
                        default: System.out.println("Neplatna volba!"); Sazeni(); break;
                    }
                }
                else if(nacislo%3==1 && nacislo < 35){
                    if(ruleta>=nacislo && ruleta<=(nacislo+5)){vyhra = true;}
                }
                else {
                System.out.println("Toto neni zacatek ulicky!"); Sazeni();
                } //konec podminek pro sazku, zacatek vyhodnoceni
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(vyhra==true){
                    Ruleta.penize += 5*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 5*sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
                if(Ruleta.penize!=0){
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaNaRadu();}
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
            System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaNaSloupec(){
        System.out.println("Zvolili jste si sazku NA SLOUPEC. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
            System.out.println("Preji si vsadit na sloupec zacinajici cislem: (1 nebo 2 nebo 3)");
            int nacislo = Ruleta.scan.nextInt();
            if(nacislo>=1 && nacislo <=3){
                boolean vyhra = false;
                int ruleta = Ruleta();
                if(nacislo==1){if(ruleta%3==1){vyhra=true;}}
                else if(nacislo==2){if(ruleta%3==2){vyhra=true;}}
                else if(nacislo==3){if(ruleta%3==0 && ruleta!=0){vyhra=true;}}
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(vyhra==true){
                    Ruleta.penize += 2*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 2*sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
                if(Ruleta.penize!=0){
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaNaSloupec();}
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
           System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaNaTucet(){
        System.out.println("Zvolili jste si sazku NA TUCET. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
            System.out.println("Na ktery tucet si prejete vsadit: 1 = 1-12 , 2 = 13-24 , 3 = 25-36 ?");
            int nacislo = Ruleta.scan.nextInt();
            if(nacislo>=1 && nacislo <=3){
                boolean vyhra = false;
                int ruleta = Ruleta();
                if(nacislo==1){if(ruleta>=1 && ruleta<=12){vyhra=true;}}
                else if(nacislo==2){if(ruleta>=13 && ruleta<=24){vyhra=true;}}
                else if(nacislo==3){if(ruleta>=25 && ruleta<=36){vyhra=true;}}
                else {System.out.println("Neplatna volba!"); Sazeni();}
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(vyhra==true){
                    Ruleta.penize += 2*sazka;
                    System.out.println("Vyhral jste, na Vase konto bylo pricteno "+ 2*sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral jsi.");
                }
                if(Ruleta.penize!=0){
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaNaTucet();}
                }
            }
            else{
                System.out.println("Takto nelze vsadit!");
            }
        }
        else{
           System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
        }
    }
    public static void SazkaNaPadesat(){
        System.out.println("Zvolil(a) jste si sazku 50/50. Kolik si prejete vsadit (1-"+Ruleta.penize+")?");
        int sazka = Ruleta.scan.nextInt();
        if(sazka<=Ruleta.penize && sazka > 0){
             System.out.printf("Na co si prejete vsadit: %n"
                     + "1 = na cervenou barvu , 2 = na cernou barvu%n"
                     + "3 = na nizkou (1-18), 4 = na vysokou (19-36)%n"
                     + "5 = na lichou , 6 = na sudou %n");
             int nacislo = Ruleta.scan.nextInt();
             if(nacislo>=1 && nacislo<=6){
                boolean vyhra = false;
                int ruleta = Ruleta();
                switch(nacislo){
                    case 1: if(ruleta>0 && (((ruleta<10 || (ruleta > 18 && ruleta < 28)) && ruleta%2==1) || ((ruleta>28 || (ruleta > 10 && ruleta < 19)) && ruleta%2==0))){vyhra=true;}break;
                    case 2: if(ruleta>0 && (((ruleta<10 || (ruleta > 18 && ruleta < 28)) && ruleta%2==1) || ((ruleta>28 || (ruleta > 10 && ruleta < 19)) && ruleta%2==0))){vyhra=false;} else if(ruleta!=0){vyhra=true;}break;
                    case 3: if(ruleta>0 && ruleta<19){vyhra=true;}break;
                    case 4: if(ruleta>19){vyhra=true;}break;
                    case 5: if(ruleta>0 && ruleta%2==1){vyhra=true;}break;
                    case 6: if(ruleta>0 && ruleta%2==0){vyhra=true;}break;
                }
                System.out.println("Na rulete padlo: "+ruleta+".");
                if(vyhra==true){
                    Ruleta.penize += sazka;
                    System.out.println("Vyhral(a) jste, na Vase konto bylo pricteno "+ sazka +" korun.");
                }
                else {
                    Ruleta.penize -= sazka;
                    System.out.println("Nevyhral(a) jste.");
                }
                if(Ruleta.penize!=0){  
                    System.out.println("Prejete si zopakovat tento typ sazky? 1 = ANO , jine cislo = NE");
                    int znovu = Ruleta.scan.nextInt();
                    if(znovu==1){SazkaNaPadesat();}
                }
            }
            else{
                 System.out.println("Takto nelze vsadit!");
            }
        }
        else{
                    System.out.println("Neplatna volba. Hodnota sazky musi byt vetsi nez nula a ne vetsi nez Vas zustatek!");
                }
    } // barva, licha/suda, vysoka/nizka
    
    public static int Ruleta(){return (int) (Math.random()*37);} //generuje nahodna cisla od 0 do 36   
} /*
 * ve vsech metodach je osetreno, aby uzivatel nemohl vsadit vice penez nez ma, a aby vsadil minimalne 1
 * ve vsech metodach je osetreno, aby uzivatel mohl vsadit pouze v souladu s pravidly
 * pri poruseni se vypisuje prislusne varovani a uzivatel je navracen do cyklu hry 
 */