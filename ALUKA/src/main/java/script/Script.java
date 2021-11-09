/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package script;

import enumeration.Roomevent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

/**
 *
 * @author miche
 */
public class Script {

    private ArrayList<Storytelling> script = null;
    private Vector<Roomevent> event;

    public Script() {
        script = new ArrayList<>();
        event = new Vector<>();
        event.add(Roomevent.start);
        event.add(Roomevent.afight);
        script.add(new Storytelling("Non e' una giornata normale,non e' una settimana normale,non e' neanche un mese normale. "
                + "Qui nel tuo piccolo borgo, chi e' rimasto fa solo incubi, di notte non si sente piu' un rumore, di giorno "
                + "si sente solo la paura per le strade. E' piu' di un mese che le persone spariscono dal piccolo borgo di "
                + "Vettaroccia, e quelli che vengono ritrovati forse e meglio che non fossero mai stati ritrovati. "
                + "Ma il tuo lavoro non e' solo quello di trovare i corpi, ma anche chi li ha uccisi in quel modo poiche' ultima guardia del borgo. ", event));
    }

    public ArrayList<Storytelling> getScript() {
        return this.script;
    }

    public void loadRoomScript(int idroom) {
        switch (idroom) {
            case 100:
                script100();
                break;
            case 101:
                script101();
                break;
            case 102:
                script102();
                break;
            case 103:
                script103();
                break;
            case 104:
                script104();
                break;
            case 105:
                script105();
                break;
            case 200:
                this.script200();
                break;
            case 201:
                this.script201();
                break;
            case 202:
                this.script202();
                break;
            case 203:
                script203();
                break;
            case 301:
                script301();
                break;
            case 302:
                script302();
                break;
            case 303:
                script303();
                break;
            case 500:
                script500();
                break;
            case 501:
                script501();
                break;
            case 502:
                script502();
                break;
            case 503:
                script503();
                break;
            case 504:
                script504();
                break;
            case 505:
                script505();
                break;
            case 600:
                script600();
                break;
            case 601:
                script601();
                break;
            case 602:
                script602();
                break;
            case 603:
                script603();
                break;
            case 604:
                script604();
                break;
            case 605:
                script605();
                break;
            case 606:
                script606();
                break;
            case 700:
                script700();
                break;
            case 701:
                script701();
                break;
            case 702:
                script702();
                break;
            case 703:
                script703();
                break;
        }
    }

    public String getRoomScript(Roomevent event1, Roomevent event2) {
        Storytelling temp = null;
        Iterator ite = this.script.iterator();
        while (ite.hasNext()) {
            temp = (Storytelling) ite.next();
            if (temp.getRoomevent().get(0) == event1) {
                if (temp.getRoomevent().get(1) == event2) {
                    return temp.getStorytelling();
                }
            }
        }
        return null;
    }

    private void script100() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Sei nell'unica piazza del borgo, prima era brulicante di gente, ma ora e' patricamente vuota. "
                + "Da qui puoi andare al tempio seguendo la strada a nord,dal fabbro andando e a ovest,al del mercato andando a est e andando a sud ti"
                + "dirigi verso il maniero al vecchio maniero armai vuoto che si trova fuori il borgo. ", event));
    }

    private void script101() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Lungo il vicoletto le persiane delle finestre sono tutte chiuse,non incontri nessuno."
                + " Rapidamente arrivi alla piccola fucina di Gregorio, un tuo vecchio amico. L'odore dell'acciaio rovente si sente "
                + "anche da lontano, e ti riempie il naso. La fucina da fuori e' un piccolo edificio in pietra con tetto spiovente "
                + "in tegole, senti distintamente il rumore delle martellate di Gregorio. Dentro e' piena di strani strumenti "
                + "sembrano quasi strumenti di tortura, e li all'incudine c'e' Gregorio, il fabbro, che martella una qualcosa di rovente. "
                + "Da qui puoi solo andare verso est", event));
    }

    private void script102() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("La strada verso il tempio e' la piu' bella del paese. Ci sono aiuole con fiori, alberi"
                + " e la strada e' sempre pulita. Il tempio non e' molto grande e' solo una sala per le preghiere e di lato la casa"
                + " del sacerdote degli dei Padre Antonio, lo stesso sacerdote che quando eri piccolo ti tirava le orecchie. Il tempio"
                + " e' in spessa pietra fredda e un piccolo collonato e' posto davanti la grande porta d'ingresso del tempio."
                + " Dentro e' proprio come te lo ricordavi, gli arazzi che raffigurano gli dei sono sempre li, appesi ai muri, pieni di polvere."
                + " Solo che il tempio e' praticamente vuoto, c'e' solo Padre Antonio che pulisce con una scopa il pavimento. "
                + "Da qui puoi solo andare verso sud.", event));
    }

    private void script103() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il mercato sembra sempre quello, tante piccole bottege e tante bancarelle, la "
                + "differenza e' che ora ogni donna e' accompagnata da un uomo armato. Da qui puoi andare da Drogheda l'armaiola a nord "
                + " o est dalla giovane alchimista Elsbeth o andare alla piazza principale andando ad ovest.", event));
    }

    private void script104() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Dal centro del mercato vai verso nord e arrivi alla bottega dell'armaiolo ora gestita da Drogheda,"
                + " ragazza mezzorca che hai sempre trovato molto carina. Da fuori la bottega riesci a vedere armi e armature appese"
                + " sui muri.Entranto Drogheda ti saluta con un'occhiolino. Da qui puoi solo tornare a sud verso il mercato.", event));
    }

    private void script105() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Dal centro del mercato vai verso est, entri in una stradina e arrivi dall'alchimista."
                + " La bottega all'esterno e' molto anonima, ma dentro c'e' un fortissimo odore di tutto, spezie varie e altre cose"
                + " che non hai mai chiesto a tuo padre. E li dietro il bancone c'e' Elsbeth tua sorella maggiore, che ha continuato"
                + " l'attivita' di famiglia, che ti saluta affettuosamente anche se con uno sguardo un po' preoccupato per la situazione."
                + " Da qui puoi andare ad ovest verso il mercato.",
                event));
    }

    private void script200() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Oltreppasando il cancello stranamente aperto del maniero ti trovi su un vialetto ormai quasi"
                + " completamente ricoperto dalla vegetazione selvatica.Guardando davanti a te vedi l'imponente maniero,e la sua grossa porta"
                + " di ingesso nera come la pece ti avvicini,il portone sembra quasi una grossa bocca pronta per "
                + " mangiarti,giri il pomello ormai ricoperto da una patina di ruggine ma la porta non si apre"
                + " e probabilmente chiusa a chiave o bloccata.Da qui puoi andare, a ovest verso quello che sembra"
                + " ormai il giardino abbandonato a est verso quello che sembra il cimitero di famiglia o tornare indietro verso nord.", event));

        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Oltreppasando il cancello stranamente aperto del maniero ti trovi su un vialetto ormai quasi"
                + " completamente ricoperto dalla vegetazione selvatica.Guardando davanti a te vedi l'imponente maniero,e la sua grossa porta"
                + " di ingesso nera ora aperta.Da qui puoi andare a sud verso il maniero, a est verso quello che sembra"
                + " ormai il giardino abbandonato a ovest verso quello che sembra il cimitero di famiglia o tornare indietro verso nord.", event));
    }

    private void script201() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Arrivi in giardino, le piante crescono libere, nessuno le cura piu' da anni."
                + " Le gramigne crescono indisturbate, i roseti infestano la parte basse delle mura che circondano il"
                + " maniero. Un cattivo adore si sente nell'aria, pensi che provenga dall'acqua stagnate delle fontana"
                + " al centro del giardino, ma un fruscio ti avverte dell'iminente pericolo, due uomini anzi due"
                + " cadaveri camminanti con vesti e carni logore e in palese decomposizione si stanno dirigendo verso di te.", event));

        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Arrivi in giardino, le piante crescono libere, nessuno le cura piu' da anni."
                + " Le gramigne crescono indisturbate, i roseti infestano la parte basse delle mura che circondano il"
                + " maniero. La fontana al centro del giardino e' ormai casa di un piccolo stagno. Le vetrate"
                + " che danno sul giardino sono troppo alte per poterti arrampicare.Da qui puoi solo andare verso est,"
                + " al cancello del maniero.", event));
    }

    private void script202() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Ti dirigi verso il cimitero di famiglia, molte lapidi sono presenti, e le"
                + " incisioni su di esse sono troppo logore per essere lette.Un brivido sulla schieda ti va rizzare i peli,"
                + " da dietro un albero uno scheletro viene verso di te con passo lento e deciso, i suoi occhi vuoti ti"
                + " fissano", event));

        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Ti dirigi verso il cimitero di famiglia, molte lapidi sono presenti, e le"
                + " incisioni su di esse sono troppo logore per essere lette, ora che nessun pericolo ti minaccia"
                + " puoi esplorare meglio il cimitero. Una piccola lapide bianca attira la tua attenzione, quello"
                + " che riesci a leggere e' solo un nome: Lindsey. Vero est, seguendo con l'occhio,un breve sentiero"
                + " ti porta ad una piccola struttura senza finestre in marmo, ormai giallo con una porta nera. Guardando verso il maniero"
                + " noti una porticina in leegno che ondeggia mossa dal vento.Da qui puoi andare verso sud ed entrare nel maniero,"
                + " verso est dirigendoti verso quello che hai capito essere una cripta o tornare indietro andando verso ovest.", event));
    }

    private void script203() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Andando verso la cripta, i suoi detagli ti sembrano sempre piu' chiari,"
                + " riesci a distinguere le collone poste agli angoli della piccola struttura, i gargoyle su questultime"
                + " che guardano verso il sentiero mentre hanno un dito sulla bocca. La forma della cripta e' ottogonale"
                + " ed e' completamente realizata con un marmo giallo, a risaltare all'occhio e' la porta nera in ferro"
                + " battuto, che proprio come pensavi e' chiusa. Da qui puoi solo tornare indietro verso ovest.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Andando verso la cripta, i suoi detagli ti sembrano sempre piu' chiari,"
                + " riesci a distinguere le collone poste agli angoli della piccola struttura, i gargoyle su questultime"
                + " che guardano verso il sentiero mentre hanno un dito sulla bocca. La forma della cripta e' ottogonale"
                + " ed e' completamente realizata con un marmo giallo, a risaltare all'occhio e' la porta nera in ferro"
                + " battuto, ora apeta. Da qui puoi  tornare indietro verso ovest o entrare nella cripta andando verso est.", event));
    }

    private void script301() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Entrando nella cripta lasci la porta per far entrare la luce, quando davanti a te"
                + " uno scheletro con due luci rosse scarlatte che ti fissa, alza un braccio e con l'indice ti chiede"
                + " di andartene, dietro lo scheletro vedi una scala che scende.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Entrando nella cripta lasci la porta per far entrare la luce, ora che il"
                + " guardiano della cripta e' stato promosso a morto completo puoi osservare cio' che ti circonda."
                + " Fiaccole ancora accese illuminano di giallo la stanza, e il fatto che sono accese ti turba un po'."
                + " Sui fianchi delle pareti sarcofagi con coperchio che raffigurano delle persone sono presenti, e"
                + " in fondo alla stanza una scala a chiocciola scende, facendo salire un brutto presentimento."
                + " Da qui puoi uscire andando verso ovest o scendere le scale andanto verso sud.", event));
    }

    private void script302() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Arrivi al secondo piano della cripta, il tuo sesto senso aveva ragione,"
                + " come poggi il piede sul pianerottolo di questo piano, i sarcofagi in marmo si aprono, mani scheletriche"
                + " preanuncia quello che avvera'. Due scheletri ti puntano.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Questo piano e' identico a quello superiore, i sarcofagi sono vuoti, non c'e' niente di"
                + " interessante. Da qui puoi andare verso nord e risalire le scale, o"
                + " andare verso sud e scenderle ancora.", event));
    }

    private void script303() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Arrivi al terzo piano, l'aria inizia a farsi pesante, le torce fanno meno luce"
                + " cio' che vedi davanti a te pero' non puoi confonderlo, e hai gia' capito tutto. Un grosso sarcofago"
                + " dall'altra parte della stanza si apre facendo cadere il coperchio, rumorosamente, per terra, infrangedosi."
                + " Quello che cammina verso di te e' uno scheletro armato di spadone e coperto con una spessa armatura.", event));

        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Questa stanza non ha niente di speciale, tranne per un lucicchio"
                + " proveniente da dentro il sarcofago. Da qui puoi solo salire le scale andando verso nord.", event));
        this.event = new Vector();
        this.event.add(Roomevent.chest);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il sarcofago e' pieno di polvere ma dentro sembra esserci qualcosa", event));
    }

    private void script500() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Sei nell'ingresso del maniero, per un secondo ti si gela il sangue nelle vene,"
                + " un uomo ,bianco come la neve,davanti a te fa un inchino e ti saluta educatamente, e ti dice il padrone di casa ora non"
                + " puo' riceverti, ma che si prendera' lui cura di te. Neanche per un secondo pensi che ti voglia offrire"
                + " un te' con biscotti.", event));
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Sei nell'ingresso del maniero, le pareti un tempo bianche ora sono ricoperte"
                + " da uno strato nero di muffa, i grossi quadri raffiguranti imprese belliche sono un po'"
                + " sbiaditi, la polvere regna sovrana sui mobili, che nei tempi d'oro di questo maniero dovevano"
                + " avere un bellissino colore noce. Il grosso portone nero stranamente visto dall'interno"
                + " del maniero e' bianco,accanto al portone un piccolo riquadro attira la tua attenzione."
                + " Da qui puoi solo andare a sud verso la sala"
                + " da pranzo.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Sei nell'ingresso del maniero, le pareti un tempo bianche ora sono ricoperte"
                + " da uno strato nero di muffa, i grossi quadri raffiguranti imprese belliche sono un po'"
                + " sbiaditi, la polvere regna sovrana sui mobili, che nei tempi d'oro di questo maniero dovevano"
                + " avere un bellissino colore noce. Il grosso portone nero stranamente visto dall'interno"
                + " del maniero e' bianco, il riquadro aperto e' vuoto. Da qui puoi andare a sud verso la sala"
                + " da pranzo, o a nord ed uscire dal maniero.", event));
        this.event = new Vector();
        this.event.add(Roomevent.chest);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il piccolo riquadro in legno raffigura uno scoiattolo che custodisce le"
                + " sue ghiande. Al lato c'e' una piccola fessura che permette di aprire lo spertellino.", event));
    }

    private void script501() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Il salone da pranzo e' sfarzoso, un grosso tavolo coperto da una tovaglia fa"
                + " da padrone alla sala. Due donne, una bianca come la neve con un vestito da camerira,l'altra non sembra neanche"
                + " piu' una donna la pelle verde,logora e cadente ti fa capire al volo che non e' piu' viva."
                + " La cameriera pallida apre la bocca mostrandoti le zanne, mentre l'altra si muove verso di te."
                + " logora", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il salone da pranzo e' sfarzoso, un grosso tavolo coperto da una tovaglia fa"
                + " da padrone alla sala. Sul soffitto c'e' un grosso lampadario di cristallo, coperto da ragnatele."
                + " Le pareti sono coperte da quadri raffriguranti eventi mondani come balli in maschera, rappresentazioni"
                + " teatrali e altre cose. Ma non c'e' niente di utile. Da qui puoi andare a nord verso l'ingresso del maniero,"
                + " a ovest verso delle scale che portano al secondo piano o a est verso la cucina ", event));

    }

    private void script502() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Quello che vedi intorno a te sono tegami ricoperti di polvere, scaffali vuoti,"
                + " ragnatele negli angoli, cucine a legna ormai da molto non piu' utilizzate e un uomo con un bel vestito"
                + " nero che ti fa con la mano cenno di avicinarti. 'Non mordo, non mordo tranquillo vieni qui.',"
                + " tu ti avvicini un po' mantenendo un certo spazio tra te e l'uomo in modo tale da poter reagire nel caso"
                + " ti attacchi. L'uomo ti saluta e si presenta, dice che il suo nome e' Azazel."
                + " Non chiede il tuo nome e non"
                + " sembra interesargli. Dice che e' un mercante, un mercante speciale, ma lui non e' interessato"
                + " all'oro, vuole altro. Ti dice che in cambio di spendide magie del sangue ti chiede giusto"
                + " un po' della tua vita.Da qui puoi andare verso ovest , verso la sala da pranzo, verso nord ,verso un"
                + " dormitorio per la servitu', verso sud, verso uno sgabuzino e a est verso il cimitero.", event));
    }

    private void script503() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Ti trovi davanti scale in legno scuro, coperte con un tappeto rosso, pensi che"
                + " almeno prima fosse stato rosso,dato che ora e' piu' grigiastro. Ci sono molti quadri sulle pareti,"
                + " ritratti piu' che quadri, capisci che probabilmente erano i ritratti singoli dei membri della"
                + " famiglia che viveva qui, hai l'impressione che lo spazio non e' cosatante tra un quadro"
                + " e l'altro. Poi capisci che"
                + " probabilmente manca un quadro. Da qui puoi andare a nord al secondo piano o a sud verso la"
                + " sala da pranzo.", event));
    }

    private void script504() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Un semplice sgabuzino con scope e oggetti per pulire."
                + "Sul pavimento noti una botola, privi ad aprirla ma e' chiusa a chiave."
                + "Da qui puoi solo andare verso nord, verso la cucina.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Un semplice sgabuzino con scope e oggetti per pulire."
                + "Sul pavimento la botola ora e' aperta. Il buio che sale non ti da nessun buon presentimento."
                + "Da qui puoi andare verso nord, verso la cucina, o scendere le scale andando verso sud.", event));
    }

    private void script505() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("A prima vista non e' una stanza molto importante, ci sono un paio di letti"
                + " delle cassepanche, delle poltrone e qualche appendi abito e una piccola libreria probabilmente"
                + " qui la servitu' si riposava.Non fai neanche in tempo a guardarti in torno che una donna e"
                + " un uomo bianchi come la neve, vestiti da cuoco, con le vesti sporche di sangue si alzano"
                + " dalle poltrone dicendo:'Presto il padrone avra' la cena.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("A prima vista non e' una stanza molto importante, ci sono un paio di letti"
                + " delle cassepanche, delle poltrone e qualche appendi abito e una piccola libreria probabilmente"
                + " qui la servitu' si riposava.Da qui poi solo andare a sud verso la cucina.", event));
    }

    private void script600() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Ti trovi davanti scale in legno scuro, coperte con un tappeto rosso, pensi che"
                + " almeno prima fosse stato rosso,dato che ora e' piu' grigiastro. Ci sono molti quadri sulle pareti,"
                + " ritratti piu' che quadri, capisci che probabilmente erano i ritratti singoli dei membri della"
                + " famiglia che viveva qui, hai l'impressione che lo spazio non e' cosatante tra un quadro"
                + " e l'altro. Poi capisci che"
                + " probabilmente manca un quadro. Da qui puoi andare a nord al primo piano o a sud verso lo"
                + " studio del padrone di casa o a est in un corridoio.", event));
    }

    private void script601() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Un piccolo letto ricoperto da ragniatele padroneggia nella stanza, ci sono dei giocattoli per terra,"
                + " un soldatino di legno, una trottola e altri gioccatoli. Una piccola libreria e' posta difornte"
                + " al letto. La finiestra della stanza e' coperta anche questa da polvere ma un vetro e rotto"
                + " e da quel buco vedi il tuo villaggio, che sembra quasi un quadro. Quanto ti giri noti"
                + " che il letto ha una forma strana, ci deve essere qualcosa sotto le coperte, dopo"
                + " quello che hai visto in questo maniero non sai piu' cosa aspettarti. Ti avvicini con cautela al letto,"
                + " allunghi una mano al letto, mentre l'altra la tieni pronta per combattere. Alzi velocemente"
                + " le coperte e sotto trovi uno scheletro, e' piccolo, probabilmente e' del bambino che dormiva"
                + " in questa stanza.Ai piedi del letto c'e' una cassapanca. Da qui poi andare a sud nel corridoio.", event));
    }

    private void script602() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Sul lettono una ragazza vestita con una lunga veste logora e di colore marrone chiaro"
                + " piange mentre accarezza una bambola, con delle mani ricoperte di squame. Quando si accorge di te,stranamente non ti salta addosso come tutti,"
                + " ti chiede di chiudere la porta dietro di te, tu lo fai, ma non sai perche', forse perche' non te lo aspettavi."
                + " La donna ti dice che una grande male e' sepolto sotto questa casa. Ti chiede di distruggere"
                + " questo male e far si che la tragedia che ha colpito questa famiglia possa finalmente essere dimenticata."
                + " Detto cio' si alza in pidiedi, ti chiede scusa, ma la sua fame gli impone di attaccarti.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("La stanza ,ora vuota, ha al centro un piccolo letto pulito e lindo,"
                + " tutta la stanza e' pulita, solo la piccola bambola di pezza dai capelli rossi e' fuori posto, li sul"
                + " letto dove la giovane balia l'ha lasciata. Ci sono molti giocattoli, e tanti libri di fiabe per bambini."
                + " Accanto al letto c'e' un vaso con dei fiori ormai secchi. Da qui puoi andare sul nel corridoio.", event));
    }

    private void script603() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Un lungo corridoio, con dei tappeti per terra, ci sono dei quadri appesi"
                + " sulle pareti. Da qui puoi andare a nord nella stanza del bambino, a sud nella stanza "
                + " dei padroni della magione e a est verso la camera della bambina e a ovest verso le scale.", event));
    }

    private void script604() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Una grossa scrivania bianca e' posta sotto la finiestra,ci"
                + " sono molti documenti firmati Barone Celtor, il calamaio ormai contiene inchiostro secco,"
                + " tra le tante carte risalta all'occhio un piccolo riquadro di una bambina dai capelli rossi."
                + " La scrivania contiene molti cassetti. Ai lati della stanza grandi librerie vuote coprono le pareti."
                + " Alzando lo sguardo vedi una botola, provi ad aprirla, e' aperta."
                + " Da qui puoi andare a nord verso le scale, o a sud e salire in soffitta."
                + "", event));
        this.event = new Vector();
        this.event.add(Roomevent.chest);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Apri i primi due cassetti della scrivania, che risultano vuoti. Il terzo cassetto e'"
                + " chiuso a chiave ma dato ormai l'eta' della scrivania il legno e' ormai logoro, faccendo con un po' di"
                + " forza riesci a rompere il legno intorno alla serratura aprendo il cassetto. Dentro trovi qualcosa di interessante.", event));
    }

    private void script605() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Entri nella camera da letto dei signori del maniero."
                + " Trovi una cameriera dalla pelle bianca che sistema le lenzuola, mentre una donna"
                + " dalla pelle bianca come il latte e i capelli rossi come il sangue con un"
                + " abito nero da lutto ti punta un dito e ti dice che non gli impedirai di"
                + " completare il loro piano.", event));

        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Entri nella camera da letti dei signori del maniero."
                + " C'e' un grosso letto ben curato, difronte ad esso, un grosso armadio in mogano"
                + " e in un angolo della stanza c'e' la sedia sulla quale sedeva la donna."
                + " Da qui puoi andare a nord verso il corridoio.", event));
    }

    private void script606() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("La soffitta e' piena di scatole e ragnatela. La luce del sole"
                + " entra da una piccola finestra rotonda. Da quella piccola finestra si puo' vedere al"
                + " di la del maniero, La catena montuosa del re nano Dolgrin che circonda la valle."
                + " Un baule in legno con rifiniture di ferro attira la tua attenzione."
                + " Da qui puoi solo tornare nello studio andando verso nord.", event));
    }

    private void script700() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("C'e' la fioca luce di piccole torce che illumina la stanza."
                + " Lungo le pareti degli scaffali sono pieni di cibo ormai avariato da molto tempo,"
                + " anche le marmelate ormai non sono piu' buone. L'aria e' pesante e il tanfo del sangue"
                + " marcio ti riempie le narici. Da qui puoi andare a ovest verso quella che dovrebbe"
                + " essere la cantina dei vini o risalire la scaletta andando verso est.", event));
    }

    private void script701() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Difronte la porta una piccola candela illumina la piccola"
                + " stanza. Sugli scaffali qualche bottiglia rotto rivela il contenuto, ormai trasformato"
                + " in muffa e funghi, mentre ti giri intorno per capire cosa fare tre le varie bottiglie"
                + " di vino e un piccola botte di birra nanica, noti per terra una piccola striscia"
                + " di sangue che arriva alla parete a sud. Al lato dello scaffale del vino un piccolo"
                + " passaggio ti permette di passare. Da qui puoi andare a sud verso quella stanza"
                + " sconosciuta o andavere verso est alla dispenza.", event));
    }

    private void script702() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("Entri in un grande salone. Qui le torce illunimano di giallo, un pavimento"
                + " ricoperto di sangue, al centro, delle candele sono poste ad intervalli regolari in un cerchio"
                + " composto da teschi. Ti tremano un po' le gambe quando una voce maschile ti parla. Ti chiede come"
                + " hai fatto ad arrivare fin qui, e quanti della sua progenie hai ucciso, quello che hai identificato"
                + " grazie ai quadri come il barone dice che non importa e presto li riportera' tutti in vita utilizzando"
                + " le anime di quelli che vivono nel villaggio. Prima di attaccarti dice ' Il sangue non e' acqua'."
                + "", event));
        this.event = new Vector();
        this.event.add(Roomevent.closedor);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Entri in un grande salone. Qui le torce illunimano di giallo, un pavimento"
                + " ricoperto di sangue, al centro, delle candele sono poste ad intervalli regolari in un cerchio"
                + " composto da teschi. Ormai il cerchio alchemico non servira' piu a nessuno. Mentre cammini per la stanza"
                + " osservando innoridito pile di ossa appogiate alle pareti, trovi un piccolo foro a forma di serratura"
                + " nella parete a sud ,alle spalle del piccolo trono, provi a guardarci attraverso ma non riesci a vedere"
                + " niente. Chissa' dove sara' la chiave che va inserita li. Da qui puoi andare verso nord, verso la cantina dei vini."
                + " del trono", event));
        this.event = new Vector();
        this.event.add(Roomevent.chest);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il piccolo trono in legno ha vicino dei libri scritti in rune. al lato di una"
                + " lato del trono, appesa con un chiudo vi e' una vecchia chiave.", event));
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Il grande salone, illuminato da torce, e' pieno delle ossa sporche di sangue delle"
                + " persone uccise da quel folle. Da qui puoi andare verso nord, verso la cantina dei vini, o andare verso sud"
                + " in quella stanza segreta.", event));

    }

    private void script703() {
        this.script = new ArrayList<>();
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.bfight);
        this.script.add(new Storytelling("La stanza illuminata da una piccola fiammella. Alla base della parete difronte a te"
                + " una strana creatura ricoperta di stracci siede per terra. Ti vede entrare e quando capisce che non sei"
                + " il barone inizia a ridere, quando apre la bocca per ridere le rughe del volto si distendono, e"
                + " nell debole luce intrvedi lunghe zanne, la creatura e' molto magra, pelle ed ossa, ma il suo sguardo"
                + " malvagio e freddo ti fa capire che e' questo il male che quella giovane vampira voleva che ucidessi."
                + " Mentre si alza l'essere continua contina a ridere ed inizia a parlare. 'Se tu sei qui vuol dire che"
                + " quel debole schiocco e' morto, il barone pensava di diventare un vampiro e di riportare in vita la figlia,"
                + " si credeva cosi intelligente e furbo, e nel mentre ha perso anche il primo della sua progenie, ma ora e' morto."
                + " Il maleficio e' rotto, sono libero, e tu sarai il mio nuovo primo pasto'. E detto cio' scatta verso di te.", event));
       
        this.event = new Vector();
        this.event.add(Roomevent.noone);
        this.event.add(Roomevent.afight);
        this.script.add(new Storytelling("Hai compiuto il tuo dovere di guardia. Uccisa quella bestia esci fuori dal maniero e torni"
                + " nel tuo villaggio, ti dirigi subito da padre Antonio per raccontargli di quello che hai fatto e scoperto."
                + " Raccontatogli quello avvenuto si complimenta con te, e dice che il maniero va bruciato fino alle fontamenta con tutto"
                + " quello che c'e' dentro. Ci vogliono un paio di giorni per preparare il necessario, e un solo secondo per dare"
                + " fuoco a quel maniero. Vedendo salire il fumo nero dalla magione capisci che finalmente e' tutto finito. Fine.", event));
    }
}
