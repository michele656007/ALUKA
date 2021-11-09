/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import interfaceclass.InterfaceParser;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Parser implements InterfaceParser {
    
    private Vector<Word> token;
    private final Set<Word> dictionary;
    private Word word;
    private String phrase;
    
    public Parser() {
        
        dictionary = new HashSet<>();
        writeDictionary();
    }
    
    public int runParsing(String phrase) {
        
        token = new Vector<>();
        this.phrase = phrase;
        int i = 0;
        
        if (this.phrase.isEmpty() || this.phrase.isBlank()) {
            return 0;
        } else {
            this.phrase = this.phrase.replace(",", " ");
            this.phrase = this.cleanupString(this.phrase, " su ");
            this.phrase = this.cleanupString(this.phrase, " tra ");
            this.phrase = this.cleanupString(this.phrase, " verso ");
            this.phrase = this.cleanupString(this.phrase, " con ");
            this.phrase = this.cleanupString(this.phrase, " sul ");
            this.phrase = this.cleanupString(this.phrase, " a ");
            this.phrase = this.phrase.trim();
            do {
                
                this.phrase = this.tokenizationPhrase(this.phrase);
                
                if (token.isEmpty()) {
                    return -1;
                }                
                if (i > token.size()) {
                    return -3;
                }
                i++;
                
                if (this.phrase == null) {
                    break;
                }
            } while ((!this.phrase.isEmpty() && !this.phrase.isBlank()) || (i == token.size()));
            
            this.phrase = phrase;
            this.phrase = this.phrase.replace(",", " ");
            
            
            switch (token.size()) {
                case 0:
                    return -3;
                case 1:
                    if (this.checkWord(token.get(0).getword(), "direction")) {
                        return 1;
                    }
                    
                    if (this.checkWord(token.get(0).getword(), "spel")) {
                        return 1;
                    }
                    
                    if (this.checkWord(token.get(0).getword(), "miracle")) {
                        return 1;
                    }
                    
                    if (this.checkWord(token.get(0).getword(), "verb")) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "command")) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "features")) {
                        return 1;
                    }
                    break;
                case 2:
                    //System.out.println("sono qui caso 2");
                    if (this.checkWord(token.get(0).getword(), "command") && (this.checkWord(token.get(1).getword(), "thing")
                            || this.checkWord(token.get(1).getword(), "spel") || this.checkWord(token.get(1).getword(), "miracle"))) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "verb") && ((this.checkWord(token.get(1).getword(), "direction")
                            || this.checkWord(token.get(1).getword(), "thing") || this.checkWord(token.get(1).getword(), "enemy")
                            || this.checkWord(token.get(1).getword(), "spel") || this.checkWord(token.get(1).getword(), "miracle"))
                            || this.checkWord(token.get(1).getword(), "png"))) {
                        return 1;
                    }
                    if ((this.checkWord(token.get(0).getword(), "spel") || this.checkWord(token.get(0).getword(), "miracle"))
                            && (this.checkWord(token.get(1).getword(), "enemy"))) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "features") && this.checkWord(token.get(1).getword(), "features")) {
                        return 1;
                    }
                    break;
                case 3:
                    if (this.checkWord(token.get(0).getword(), "verb")
                            && (this.checkWord(token.get(1).getword(), "spel")
                            || this.checkWord(token.get(1).getword(), "miracle")
                            || this.checkWord(token.get(1).getword(), "thing"))
                            && this.checkWord(token.get(2).getword(), "enemy")) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "verb")
                            && (this.checkWord(token.get(2).getword(), "spel")
                            || this.checkWord(token.get(2).getword(), "miracle")
                            || this.checkWord(token.get(2).getword(), "thing"))
                            && this.checkWord(token.get(1).getword(), "enemy")) {
                        return 1;
                    }
                    if (this.checkWord(token.get(0).getword(), "features") && this.checkWord(token.get(1).getword(), "features")
                            && this.checkWord(token.get(2).getword(), "features")) {
                        return 1;
                    }
                    break;
                default:
                    Boolean check = false;
                    if (this.checkWord(token.get(0).getword(), "verb")) {
                        
                        for (i = 1; i < token.size(); i++) {
                            check = this.checkWord(token.get(i).getword(), "thing");
                        }
                    }
                    if (this.checkWord(token.get(0).getword(), "features")) {
                        for (i = 1; i < token.size(); i++) {
                            check = this.checkWord(token.get(i).getword(), "features");
                        }
                    }
                    if (check) {
                        return 1;
                    } else {
                        return -1;
                    }
                
            }
        }
        return -3;
    }
    
    public Vector<Word> takeToken() {
        return token;
    }
    
    private String tokenizationPhrase(String phrase) {
        Iterator<Word> it = this.dictionary.iterator();
        Word temp = null;
        Vector<String> alias = null;
        //System.out.println("Sono in tokenization");
        while (it.hasNext()) {
            temp = it.next();
            
            if (phrase.indexOf(temp.getword()) == 0) {
                if (phrase.length() > temp.getword().length()) {
                    if (phrase.charAt(temp.getword().length()) == ' ') {
                        
                        this.token.add(temp);
                        return this.cleanupString(phrase, temp.getword());
                    }
                }
                if (phrase.length() == temp.getword().length()) {
                    
                    this.token.add(temp);
                    return this.cleanupString(phrase, temp.getword());
                }
            } else {
                alias = temp.getAlias();
                for (int i = 0; i < alias.size(); i++) {
                    if (phrase.indexOf(alias.get(i)) == 0) {
                        if (phrase.length() > alias.get(i).length()) {
                            if (phrase.charAt(alias.get(i).length()) == ' ') {
                                //System.out.println("temp alias:" + alias.get(i));
                                this.token.add(temp);
                                return this.cleanupString(phrase, alias.get(i));
                            }
                        }
                        if (phrase.length() == alias.get(i).length()) {
                            this.token.add(temp);
                            return this.cleanupString(phrase, alias.get(i));
                        }
                    }
                }
            }
        }
        return phrase;
    }
    
    private Boolean checkWord(String word, String typology) {
        
        Iterator<Word> it = this.dictionary.iterator();
        Word temp;
        while (it.hasNext()) {
            temp = it.next();
            if (temp.getTypology().equals(typology)) {
                if (temp.equals(word)) {
                    return true;
                } else {
                    if (temp.checkAlias(word)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    private String cleanupString(String string, String delete) {
        
        String delete_s = delete;
        String start_s = null;
        String end_s = null;
        int indexofdelete = 0;
        int delete_len = delete_s.length();
        if (string.indexOf(delete_s) < 0) {
            
            return string;
        }
        indexofdelete = string.indexOf(delete);
        start_s = string.substring(0, indexofdelete);
        string = string.substring(string.indexOf(delete));
        //System.out.println("start" + start_s);
        end_s = string.substring(delete_len);
        //System.out.println("end" + end_s);
        if ((start_s.isEmpty() || start_s.isBlank()) && (end_s.isBlank() || end_s.isEmpty())) {
            return null;
        }
        if (start_s.isEmpty() || start_s.isBlank()) {
            return end_s.strip() + " ";
        }
        if (end_s.isBlank() || end_s.isEmpty()) {
            return start_s.strip() + " ";
        }
        return start_s.strip() + " " + end_s.strip();
        
    }
    
    public Word getWord(String name) {
        if (!this.dictionary.isEmpty()) {
            Iterator ite = dictionary.iterator();
            while (ite.hasNext()) {
                Word word = (Word) ite.next();
                if (word != null) {
                    if (word.getword().equals(name)) {
                        return word;
                    }
                }
            }
        }
        return null;
    }
    
    private void writeDictionary() {
        
        word = new Word("nord", "direction");
        this.dictionary.add(word);
        word = new Word("sud", "direction");
        this.dictionary.add(word);
        word = new Word("est", "direction");
        this.dictionary.add(word);
        word = new Word("ovest", "direction");
        this.dictionary.add(word);
        
        this.dictionary.add(word);
        word = new Word("prendi", "verb");
        word.addAlias("prendere");
        word.addAlias("raccogli");
        this.dictionary.add(word);
        word = new Word("lascia", "verb");
        word.addAlias("lasciare");
        word.addAlias("butta");
        this.dictionary.add(word);
        word = new Word("guarda", "verb");
        word.addAlias("guardare");
        word.addAlias("osserva");
        word.addAlias("osservare");
        this.dictionary.add(word);
        word = new Word("leggi", "verb");
        word.addAlias("leggere");
        this.dictionary.add(word);
        word = new Word("compra", "verb");
        word.addAlias("comprare");
        word.addAlias("acquista");
        this.dictionary.add(word);
        word = new Word("vendi", "verb");
        word.addAlias("vendere");
        this.dictionary.add(word);
        word = new Word("attacca", "verb");
        word.addAlias("attaccare");
        word.addAlias("colpire");
        word.addAlias("colpisci");
        this.dictionary.add(word);
        word = new Word("parla", "verb");
        word.addAlias("parlare");
        this.dictionary.add(word);
        word = new Word("chiedi", "verb");
        word.addAlias("chiedere");
        word.addAlias("domandare");
        word.addAlias("domanda");
        this.dictionary.add(word);
        word = new Word("usa", "verb");
        word.addAlias("usare");
        this.dictionary.add(word);
        word = new Word("equipaggia", "verb");
        word.addAlias("equip");
        this.dictionary.add(word);
        word = new Word("potenzia", "verb");
        word.addAlias("migliora");
        this.dictionary.add(word);

        //things
        word = new Word("armatura di pelle", "thing");
        word.addSecontTypology("armor");
        this.dictionary.add(word);
        word = new Word("armatura di ferro", "thing");
        word.addSecontTypology("armor");
        this.dictionary.add(word);
        word = new Word("armatura di acciaio", "thing");
        word.addAlias("armatura d'acciaio");
        word.addSecontTypology("armor");
        this.dictionary.add(word);
        
        word = new Word("scudo di legno", "thing");
        word.addSecontTypology("shield");
        this.dictionary.add(word);
        word = new Word("scudo di ferro", "thing");
        word.addSecontTypology("shield");
        this.dictionary.add(word);
        word = new Word("scudo di acciaio", "thing");
        word.addAlias("scudo d'acciaio");
        word.addSecontTypology("shield");
        this.dictionary.add(word);
        
        word = new Word("spada corta", "thing");
        word.addSecontTypology("weapon");
        this.dictionary.add(word);
        word = new Word("stella del mattino", "thing");
        word.addSecontTypology("weapon");
        this.dictionary.add(word);
        word = new Word("spada lunga", "thing");
        word.addSecontTypology("weapon");
        this.dictionary.add(word);
        word = new Word("martello da guerra", "thing");
        word.addSecontTypology("weapon");
        this.dictionary.add(word);
        word = new Word("spadone", "thing");
        word.addSecontTypology("weapon");
        this.dictionary.add(word);
        
        word = new Word("cura piccola", "thing");
        word.addSecontTypology("potionhp");
        this.dictionary.add(word);
        word = new Word("cura media", "thing");
        word.addSecontTypology("potionhp");
        this.dictionary.add(word);
        word = new Word("cura grande", "thing");
        word.addSecontTypology("potionhp");
        this.dictionary.add(word);
        word = new Word("traquillante", "thing");
        word.addSecontTypology("potionsa");
        this.dictionary.add(word);
        word = new Word("calmante", "thing");
        word.addSecontTypology("potionsa");
        this.dictionary.add(word);
        
        word = new Word("chiave bianca", "thing");
        word.addSecontTypology("key");
        this.dictionary.add(word);
        word = new Word("chiave grigia", "thing");
        word.addSecontTypology("key");
        this.dictionary.add(word);
        word = new Word("chiave nera", "thing");
        word.addSecontTypology("key");
        this.dictionary.add(word);
        word = new Word("chiave vecchia", "thing");
        word.addSecontTypology("key");
        this.dictionary.add(word);
        word = new Word("diario blu", "thing");
        word.addSecontTypology("book");
        this.dictionary.add(word);
        word = new Word("libro nero", "thing");
        word.addSecontTypology("book");
        this.dictionary.add(word);

        //spel
        word = new Word("dardo di sangue", "spel");
        this.dictionary.add(word);
        word = new Word("mani brucianti", "spel");
        this.dictionary.add(word);
        word = new Word("sangue rovente", "spel");
        this.dictionary.add(word);
        word = new Word("tocco del vampiro", "spel");
        this.dictionary.add(word);
        word = new Word("fuoco oscuro", "spel");
        this.dictionary.add(word);
        word = new Word("brimston", "spel");
        this.dictionary.add(word);
        //miracle
        word = new Word("calore", "miracle");
        this.dictionary.add(word);
        word = new Word("intelligenza dell'acquila", "miracle");
        this.dictionary.add(word);
        word = new Word("forza del toro", "miracle");
        this.dictionary.add(word);
        word = new Word("destrezza del gatto", "miracle");
        this.dictionary.add(word);
        word = new Word("fiamma", "miracle");
        this.dictionary.add(word);
        word = new Word("deusvult", "miracle");
        this.dictionary.add(word);
        
        word = new Word("gregorio", "png");
        this.dictionary.add(word);
        word = new Word("padre antonio", "png");
        word.addAlias("antonio");
        this.dictionary.add(word);
        word = new Word("elsbeth", "png");
        this.dictionary.add(word);
        word = new Word("drogheda", "png");
        this.dictionary.add(word);
        word = new Word("azazel", "png");
        this.dictionary.add(word);

        //chest 
        word = new Word("riquadro", "thing");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        word = new Word("scrivania", "thing");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        word = new Word("baule", "thing");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        word = new Word("trono", "thing");
        word.addAlias("trono piccolo");
        word.addAlias("piccolo trono");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        word = new Word("sarcofago", "thing");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        word = new Word("cassapanca", "thing");
        word.addSecontTypology("chest");
        this.dictionary.add(word);
        //argomenti
        word = new Word("magia del sangue", "thing");
        word.addAlias("sangue oscuro");
        this.dictionary.add(word);
        word = new Word("miracolo", "thing");
        word.addAlias("miracoli");
        this.dictionary.add(word);
        word = new Word("omicidio", "thing");
        word.addAlias("omicidi");
        this.dictionary.add(word);
        word = new Word("vampiro", "thing");
        word.addAlias("vampiri");
        this.dictionary.add(word);

        //nemici
        word = new Word("giardiniere1", "enemy");
        this.dictionary.add(word);
        word = new Word("giardiniere2", "enemy");
        this.dictionary.add(word);
        word = new Word("scheletro", "enemy");
        this.dictionary.add(word);
        word = new Word("cameriera", "enemy");
        this.dictionary.add(word);
        word = new Word("cameriera2", "enemy");
        this.dictionary.add(word);
        word = new Word("balia", "enemy");
        this.dictionary.add(word);
        word = new Word("cuoco", "enemy");
        this.dictionary.add(word);
        word = new Word("cuoca", "enemy");
        this.dictionary.add(word);
        word = new Word("tata", "enemy");
        this.dictionary.add(word);
        word = new Word("baronessa", "enemy");
        this.dictionary.add(word);
        word = new Word("magiordomo", "enemy");
        this.dictionary.add(word);
        //command
        word = new Word("bag", "command");
        this.dictionary.add(word);
        word = new Word("levelup", "command");
        this.dictionary.add(word);
        word = new Word("scheda pg", "command");
        this.dictionary.add(word);
        word = new Word("esamina", "command");
        word.addAlias("esaminare");
        this.dictionary.add(word);
        word = new Word("salva", "command");
        this.dictionary.add(word);
        word = new Word("esci", "command");
        this.dictionary.add(word);
        word = new Word("carica", "command");
        this.dictionary.add(word);

        //caratteristiche
        word = new Word("str", "features");
        this.dictionary.add(word);
        word = new Word("fed", "features");
        this.dictionary.add(word);
        word = new Word("cos", "features");
        this.dictionary.add(word);
        word = new Word("sma", "features");
        this.dictionary.add(word);
        word = new Word("des", "features");
        this.dictionary.add(word); 
        word = new Word("empty", "empty");
        this.dictionary.add(word);
        
    }
}
