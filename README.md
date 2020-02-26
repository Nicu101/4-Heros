# 4-Heros
The Great Wizard is looking over the adventures of the new heros. The terrain range from volcanic to forrests and angels are present everywhere in this land.
=Scenariu=

Suntem într-un joc MMO-style. Eroii noștri își petrec viața într-un univers 2D, pe care îl explorează și în care se dezvoltă.

După cum este cunoscut din jocurile MMO și RPG, personajele noastre sunt diverse, fiecare aparținând unei anumite clase și având un anumit set de abilități la care se pricepe cel mai bine și pe care le poate dezvolta cel mai repede; le vom analiza în detaliu mai jos.

La începutul jocului, eroii noștri sunt plasați pe hartă în locuri bine definite. Desfășurarea jocului presupune existența unor runde cu durată unitate în care toți eroii vor executa câte o mișcare bine definită pe hartă.  

Când doi eroi ajung în același loc, ei se vor lupta. În cadrul unei runde, fiecare erou combatant își va folosi toate abilitățile disponibile împotriva adversarului, o singură dată. După luptă, ei își vor vedea de drum începând cu runda următoare. 

După nenumăratele lupte petrecute în ținutul fantastic, luptătorii au început să evolueze, să învețe din greșeli și să îsi perfecționeze **Strategiile**.

Din senin apar **Îngeri** la final de runde, iar aceștia îi pot ajuta sau încurca pe luptătorii noștri bravi. Există îngeri buni care le vor reface viața sau le vor crește rata de damage, dar și îngeri răi care le vor scădea viața sau damage-ul, chiar omorând anumiți jucatori.

Un alt element important este faptul că, auzind de luptele din acest ținut, un **Mare Magician**, a hotărât să nu mai lase luptătorii de capul lor. El dorește să **Observe** când un jucator moare/reînvie, când un înger apare sau când acesta ajută/încurcă pe cineva.

Misiunea voastră este să remodelați jocul pentru a suporta și aceste noi caracteristici.

==Hartă==

Harta pe care se desfășoară jocul este un careu 2D (dreptunghi), compus din locații ("pătrățele") de dimensiune unitate. Fiecare locație are un anumit tip, ceea ce îi conferă un set de proprietăți; tipurile de locații disponibile sunt:
  * Land
  * Volcanic
  * Desert
  * Woods

==Eroi==

Există mai multe tipuri de eroi în LOOP:

  * Knight
  * Pyromancer
  * Rogue
  * Wizard

Toți eroii au două proprietăți de bază și anume un număr de hit points(HP, "viață") și un număr de puncte de experiență (XP). Pe lângă acestea, există și un mecanism de level-up în funcție de experiența câștigată în urmă luptelor. 

=== Experiență și level up ===

Toate personajele au **XP iniţial = 0**, corespunzător nivelului 0. În momentul în care un personaj câştigă o luptă (își omoară adversarul), XP-ul său va creşte după următoarea formulă:

''XP_Winner = XP_Winner + max(0, 200 - (Level_winner - Level_loser) * 40)''

După cum se poate observa şi din formulă, câştigarea unei lupte cu un adversar care este cu cel puţin 5 nivele mai mic nu va aduce nici un plus de XP, dar câștigul în fața unui oponent de nivel mai mare poate aduce chiar mai mult de 200XP.

**Observație**: Se poate întâmpla ca eroii să se omoare reciproc. În acest caz, ambii vor primi XP-ul corespunzător. 

Creșterea în nivel are loc în momentul în care se depășește un prag de XP, ce se va calcula după următoarea formulă:

''XP_level_up = 250 + Level_curent * 50''

Spre exemplu, dacă în urma primei runde un personaj câștigă ''360 XP'', acesta va avansa direct la nivelul 3: 
   * pentru tranziția ''nivel 0 -> nivel 1'', are de atins pragul de ''250 + 0 * 50 = 250 XP'' 
   * pentru tranziția ''nivel 1 -> nivel 2'', are de atins pragul de ''250 + 1 * 50 = 300 XP'' 
   * pentru tranziția ''nivel 2 -> nivel 3'', are de atins pragul de ''250 + 2 * 50 = 350 XP'' 
   * pentru tranziția ''nivel 3 -> nivel 4'', are de atins pragul de ''250 + 3 * 50 = 400 XP'', iar cu ''360 XP'' se va opri la nivelul 3.

**Observație**: La avansarea în nivel a unui erou, acesta va reveni la 100% HP.

==Abilități==

Fiecare tip de erou are un anumit set de abilităti, ai căror parametri sau ale căror efecte depind de **terenul pe care se desfăsoară luptă (land modifiers)** și de **personajul asupra căruia actionează (race modifier)** 
 **(Atenție! Amplificatorii sunt multiplicativi, indiferent dacă abilitatea dă flat damage sau procent din stats-urile adversarului!)** Mai exact, mai mulți aplificatori sunt legați în calcule prin operația de înmulțire, și nu prin operația de adunare. 

**Observație**: La nivelul 0 toate abilităţile au doar efectul de bază!

Detaliem în continuare.

===Pyromancer===

Abil în manevrarea focului.

HP: 500 initial, +50 per nivel.

**Fireblast** - damage mare în runda curentă.

  * Damage: 350, +50/level

^ **Victimă**   ^  **Modificator**  ^
|  Rogue        |  -20%                   |
|  Knight       |  +20%                   |
|  Pyromancer   |  -10%                   |
|  Wizard       |  +5%                    |

**Ignite** - damage în runda curentă + damage mai mic în umătoarele 2 runde.

  * base damage: 150, +20/level
  * 50 damage per rundă, +30/level.

Modificatorii de mai jos se aplică atât pentru base damage, cât și pentru damage periodic:

^ **Victimă** ^  **Modificator**  ^
|  Rogue       |  -20%                   |
|  Knight      |  +20%                   |
|  Pyromancer  |  -10%                   |
|  Wizard      |  +5%                    |

Pe terenul de tip Volcanic Pyromancer-ul se hrăneşte cu energia mediului înconjurător iar abilităţile sale dau cu 25% mai mult damage.

===Knight===

HP: 900 initial, +80/level.

**Execute** - damage în runda curentă sau, dacă adversarul are un număr de HP mai mic decât o anumită limită, va fi ucis instantaneu **(damage-ul dat este egal cu HP-ul adversarului)**.

  * base damage:200, +30/level
  * HP limit: 20% * viața teoretic maximă a victimei la nivelul ei; +1% /level, până la un maxim de 40%

Damage-ul (nu și limita de viață) se modifică în funcție de victimă ca mai jos:

^ **Victimă** ^  **Modificator**  ^
|  Rogue      |  +15%                   |
|  Knight     |  +0%                    |
|  Pyromancer  |  +10%                   |
|  Wizard    |  -20%                   |

**Slam** - damage + incapacitarea adversarului (imposibilitate de mișcare) pentru următoarea rundă.

  * base damage: 100 base damage. +40 /level

^ **Victimă** ^  **Modificator**  ^
|  Rogue      |  -20%                   |
|  Knight     |  +20%                   |
|  Pyromancer  |  -10%                   |
|  Wizard    |  +5%                   |

Knight-ul este expert în lupta corp la corp. Pe terenul de tip land el este mai puternic decât celelalte clase fiind specializat pe lupta în câmp deschis și primește 15% bonus damage.
===Wizard===

HP: 400 initial, +30 per nivel.

Wizard-ul are o capacitate mentală superioară care îi permite să reziste în mediul de deșert prin meditație și imbunătățirea abilităților. Pe terenul de tip **Desert** abilitățile sale sunt cu **10%** mai puternice.

**Drain** - scade din viața adversarului proporțional cu cât are deja.

  * procent: 20%, +5% /level
  * HP de bază: ''min(0.3 * OPPONENT_MAX_HP, OPPONENT_CURRENT_HP)''
  * => ''damage = procent * min(0.3 * OPPONENT_MAX_HP, OPPONENT_CURRENT_HP)''

Modificatorii de mai jos se aplică asupra variabilei ''procent''. Ei sunt multiplicativi, i.e. dacă de exemplu avem un Wizard level 4 (=> ''20% + 5% * 4 = 40%'') și el aplică Drain asupra unui Rogue, avem un procent total de ''40% - (20% din 40%) = 40% - 8% = 32%'', deci formula de calcul de damage devine ''damage = 0.32 * min(0.3 * OPPONENT_MAX_HP, OPPONENT_CURRENT_HP)''.

^ **Victimă** ^  **Modificator**  ^
|  Rogue      |  -20%                   |
|  Knight     |  +20%                   |
|  Pyromancer  |  -10%                   |
|  Wizard    |  +5%                     |

**Deflect** - dă damage egal cu un procent **din damage-ul total (//fără race modifiers//) pe care îl primește de la adversar**

  * procent: 35%, +2% /level, până la un maxim de 70%
  * //nu are niciun efect asupra unui Wizard// (doi eroi de tip Wizard nu își dau reciprc/recursiv damage)

^ **Victimă** ^  **Modificator**  ^
|  Rogue      |  +20%                   |
|  Knight     |  +40%                   |
|  Pyromancer  |  +30%                   |
|  Wizard    |  N/A                     |

===Rogue===

Expert în sneak-attacks.

HP : 600 initial, +40 /level

**Backstab** - damage în runda curentă, cu posibilitate de critical hit.

  * base damage: 200, +20 dmg /level.
  * O dată la 3 lovituri (lovitură = aplicat Backstab, pe orice teren) Rogue-ul poate da **1.5x** damage, **doar dacă în acel moment se afla pe terenul de tip Woods**, altfel se reia ciclul.

^ **Victimă** ^  **Modificator**  ^
|  Rogue      |  +20%                   |
|  Knight     |  -10%                   |
|  Pyromancer  |  +25%                   |
|  Wizard    |  +25%                   |

**Paralysis** - damage prelungit + incapacitarea adversarului pentru un număr de runde

  * damage/rundă: 40, +10/level - se aplică în runda curentă (în care se desfășoară lupta) + rundele extra
  * număr de runde overtime: 3 (6, dacă lupta se desfășoară pe teren Woods)

^ **Victimă** ^  **Modificator damage**  ^
|  Rogue      |  -10%                   |
|  Knight     |  -20%                   |
|  Pyromancer |  +20%                   |
|  Wizard    |  +25%                   |

Datorită abilităților sale de camuflaj Rogue-ul este mai puternic pe terenul de tip pădure (**Woods**). Pe acest tip de teren Rogue-ul primește **15%** bonus damage.

==Mecanism de joc==

Jocul este bazat pe runde. Într-o rundă toate personajele execută câte o mișcare (bine determinată), iar dacă două ajung în aceeași locație, se luptă.

**Observație**: mutarea caracterelor se execută simultan; așadar, doi eroi se presupun a fi ajuns în aceeași locație doar dacă ei se află în același loc pe hartă **după executarea mișcării tuturor eroilor**. 

O luptă funcționează astfel: fiecare erou își va calcula parametrii propriilor abilități, în funcție de nivelul lor și de terenul pe care se află. Apoi fiecare abilitate va fi modificată în funcție de victimă. După aplicarea abilităților, eroii își vor calcula noile HP și XP după caz (XP-ul se va modifica doar dacă eroul a câștigat, respectiv dacă și-a omorât adversarul), după care runda se încheie.
**Atenție, în cazul în care doi eroi se întâlnesc pe aceeași poziție, se calculează în primul rând damage-ul primit de ce doi de la abilitățile overtime care îi afectează. Dacă unul din ei moare din cauza unei abilități overtime, lupta nu va mai avea loc.**

==Îngeri==
Pentru a face jocul mai interesant și pentru a ne apropia mai mult de un joc MMO, este nevoie să adăugăm noi funcționalități, iar una dintre acestea ar fi un //helper// care să apara random pe hartă și să ofere sprijin jucătorilor aflați în acea poziție.

Vom introduce un nou tip de personaj, și anume, un înger. El apare la finalul fiecărei runde într-o anumita poziție pe harta, iar dacă in acea căsuță se afla unul sau doi jucători, acesta/aceștia vor primi de la el fie HP, fie damage. Acești îngeri au diferite tipuri. Unii sunt buni si vor să ajute jucătorii, alții sunt răi și vor să îi încurce. Totodată, există și îngeri care vor omorî un jucător, dar și îngeri care vor readuce la viață un jucător.

În cele ce urmează vom detalia tipurile de îngeri întalniți și acțiunile pe care le vor executa.

Aceștia vor executa acțiuni diferite în funcție de clasa pe care le aplică, **la finalul rundei curente**.

**Îngerii vor aplica bonusurile doar dacă personajul este în viață. Doar Spawner va aplica bonusul unui jucător decedat.**

**DamageAngel**
  * Knight: crește modificatorii de damage cu 15%
  * Pyromancer: crește modificatorii de damage cu 20%
  * Rogue: crește modificatorii de damage cu 30%
  * Wizard: crește modificatorii de damage cu 40%

**DarkAngel**
  * Knight: scade HP-ul cu 40
  * Pyromancer: scade HP-ul cu 30
  * Rogue: scade HP-ul cu 10
  * Wizard: scade HP-ul cu 20

**Dracula**
  * Knight: scade modificatorii de damage cu 20% și HP-ul cu 60
  * Pyromancer: scade modificatorii de damage cu 30% și HP-ul cu 40
  * Rogue: scade modificatorii de damage cu 10% și HP-ul cu 35
  * Wizard: scade modificatorii de damage cu 40% și HP-ul cu 20

**GoodBoy**
  * Knight: crește modificatorii de damage cu 40% și HP-ul cu 20
  * Pyromancer: crește modificatorii de damage cu 50% și HP-ul cu 30
  * Rogue: crește modificatorii de damage cu 40% și HP-ul cu 40
  * Wizard: crește modificatorii de damage cu 30% și HP-ul cu 50

**LevelUpAngel**
  * Knight: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor și crește modificatorii cu 10%
  * Pyromancer: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor și crește modificatorii cu 20%
  * Rogue: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor și crește modificatorii cu 15%
  * Wizard: oferă XP cât are nevoie jucătorul pentru a avansa la nivelul următor și crește modificatorii cu 25%

**LifeGiver**
  * Knight: crește HP-ul cu 100
  * Pyromancer: crește HP-ul cu 80
  * Rogue: crește HP-ul cu 90
  * Wizard: crește HP-ul cu 120

**SmallAngel**
  * Knight: crește modificatorii de damage cu 10% și HP-ul cu 10
  * Pyromancer: crește modificatorii de damage cu 15% și HP-ul cu 15
  * Rogue: crește modificatorii de damage cu 5% și HP-ul cu 20
  * Wizard: crește modificatorii de damage cu 10% și HP-ul cu 25

**Spawner**
  * Knight: readuce jucătorul la viață și îi setează viața rămasă la 200
  * Pyromancer: readuce jucătorul la viață și îi setează viața rămasă la 150
  * Rogue: readuce jucătorul la viață și îi setează viața rămasă la 180
  * Wizard: readuce jucătorul la viață și îi setează viața rămasă la 120

**TheDoomer**
  * All: omoară un jucător

**LifeGiver**
  * Knight: crește XP-ul cu 45
  * Pyromancer: crește XP-ul cu 50
  * Rogue: crește XP-ul cu 40
  * Wizard: crește XP-ul cu 60
==Strategii==
După cum spuneam, fiecare tip de jucător și-a dezvoltat o strategie.

Ei au considerat că în anumite cazuri ar fi mai bine să scadă din HP, dar să dea mai mult damage, sau invers. Fiecare după cum s-a gândit că e mai bine. 

Astfel, fiecare clasă a venit cu următoarea logica de atac, **aplicată înainte de a face următoarea mișcare**.

**Knight**
  * dacă (1/3 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP), el va renunța la 1/5 din HP-ul curent și va crește coeficienții cu 50%
  * dacă CURRENT_HP < (1/3 * MAX_LEVEL_HP), el va renunța la 20% din coeficienți și va primi 1/4 din HP-ul curent

**Pyromancer**
  * dacă (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/3 * MAX_LEVEL_HP), el va renunța la 1/4 din HP-ul curent și va crește coeficienții cu 70%
  * dacă CURRENT_HP < (1/4 * MAX_LEVEL_HP), el va renunța la 30% din coeficienți și va primi 1/3 din HP-ul curent

**Rogue**
  * dacă (1/7 * MAX_LEVEL_HP) < CURRENT_HP < (1/5 * MAX_LEVEL_HP), el va renunța la 1/7 din HP-ul curent și va crește coeficienții cu 40%
  * dacă CURRENT_HP < (1/7 * MAX_LEVEL_HP), el va renunța la 10% din coeficienți și va primi 1/2 din HP-ul curent

**Wizard**
  * dacă (1/4 * MAX_LEVEL_HP) < CURRENT_HP < (1/2 * MAX_LEVEL_HP), el va renunța la 1/10 din HP-ul curent și va crește coeficienții cu 60%
  * dacă CURRENT_HP < (1/4 * MAX_LEVEL_HP), el va renunța la 20% din coeficienți și va primi 1/5 din HP-ul curent

==Marele Magician==
El este un personaj aparte în acest joc. Îl putem asemana unui admin.
Marele Magician a auzit de aceste lupte mirifice și a vrut să le urmărească îndeaproape.
Totuși, el este un magician ocupat cu vrăji și poțiuni, așa că nu își permite să ocupe tot timpul uitându-se cine se mai lupta cu cine.

El vrea sa studieze comportamentul **Îngerilor**, deoarece au ceva aparte ce ar putea fi folosit la următoarele vrăji. Acel praf magic lăsat în urma lor după ce dispar.

Pentru asta, el vrea să fie notificat, printr-un mecanism implementat de voi, cu poziția unui înger când acesta apare pe harta pentru a colecta praful magic.

Ar vrea să știe și cărui jucător i s-au oferit noi puteri în joc.

Iar cel mai important, pentru Magician, este să cunoască atât personajele care au fost ucise în lupta sau de către îngerii cei răi, cât și personajele care au fost readuse la viață de îngeri.


== Input ==

Pe prima linie a fișierului se află 2 numere N si M reprezentând dimensiunile terenului. Pe următoarele N linii se vor găsi N string-uri de lungime M fiecare, cu litere corespunzătoare numelor terenurilor (W,L,V,D).

Pe următoarea linie se va gasi un număr P reprezentând numărul de personaje. Pe următoarele P linii se vor găsi câte un string reprezentând rasa fiecărui personaj și poziția lui inițială (e.g. "W 0 1" înseamnă un Wizard poziționat pe rândul 0 coloana 1).

Pe următoarea linie se găsește R, numărul de runde. Fiecare rundă e descrisă de P litere, indicând direcția în care se mișcă fiecare personaj. Acestea pot lua una din următoarele valori:
  * 'U'(up <=> rând--)
  * 'D'(down <=> rând++)
  * 'L'(left <=> col--)
  * 'R'(right <=> col++)
  * '_' (fără mișcare)

Direcțiile sunt atribuite eroilor în ordinea în care au fost descrise personajele în fișierul de intrare.

După mișcările făcute de jucători am adăugat îngerii pentru fiecare runda, cu poziția unde vor apărea.

În exemplul de mai jos, avem 2 runde, deci încă 2 linii cu îngeri la finalul fișierului.
La finalul primei runde va apărea un LifeGiver la poziția (0,0), iar la finalul celei de-a doua rundă va apărea un LevelUpAngel la poziția (0,0).

Liniile sunt de forma (<numărul de îngeri din runda curentă> <tip înger 1, coordonata X, coordonata Y> <tip înger 2, coordonata X, coordonata Y>

Exemplu Input:

1 1

V

2

K 0 0

P 0 0

2

__

__

1 LifeGiver,0,0

1 LevelUpAngel,0,0
