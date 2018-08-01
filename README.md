# Személyre szabható online társasjátékok

Feladat:

_A dolgozat egy olyan webalkalmazást mutat be, amelyen keresztül a felhasználók különféle társasjátékokkal játszhatnak. Az alkalmazás egyediségét az adja, hogy a játékosok a szabályokat meg is tudják változtatni. A játékosok alapvetően egymás ellen játszanak. Rendezhetnek mérkőzéseket, rangadókat, amely alapján az online felületen megtekinthetők a játékosok ranglistája, illetve különféle statisztikái._

## Bevezetés

_(1-2 oldal)_

* Itt kell hangsúlyozni, hogy miért van igény a dolgozatban bemutatott webalkalmazás elkészítésére. (Elég csak a végén írni róla 1-2 oldalt.)
* Motiváció a dolgozat megírásával kapcsolatban

## Online táblás játékok

_(4-5 oldal)_

Általános leírás a témakörről

Felsorolni, hogy milyen hasonló alkalmazások vannak.
* Mi az ami tetszik/nem tetszik azokban?
* Miben lesz az aktuális más/több?
* Milyen technológiával készültek az alkalmazások?

## Táblás játékok specifikációja

_(6-8 oldal)_

* Felsorolni a játékokat, és külön alpontokban leírni a szabályrendszerüket formálisan.
* Itt kellene megadni, hogy mi és milyen formában lehet bennük személyre szabható.
* Szabályok, szabály változatok
* Matematikai jellegű leírások kellenének ide
* Ellenőrzések leírása elvi szinten (például indexelési módok, tábla reprezentáció)

## Tervezés

_(8-10 oldal)_

* Architektúra áttekintése

### Adatbázis

* Séma leírása

### API

* API-kat definiálni, amin majd kommunikálni lehet a backend résszel (Ez lenne a REST API megadása gyakorlatilag)
* A szinkronizációs protokollokat is definiálni kellene
* Készíteni kellene hozzá szekvencia-diagramokat

### Backend

* Az alkalmazáslogika
* Authentikáció

### Frontend

* Definiálni, hogy milyen lapokból áll majd az alkalmazás
* Leírni a játéktér megadási módját, adatszerkezeteket

### Játékszabályok ellenőrzése

* Valid lépések ellenőrzése mindkét oldalon
* Játéklogika elkészítésének tervei

## Implementáció

_(6-8 oldal)_

* Ide jöhetnek maguk a kódok
* Meg kell indokolni, hogy miért pont a felhasznált technológiákra esett a választás
* JWT használata
* Ebbe a fejezetbe kerülhetnek majd a kódpéldák. Az elejébe annyira nem kellene majd.
* A játékállapot kezelésének konkrét implementációja

## Tesztek, eredmények

_(4-5 oldal)_

* Elkészült dolgok értékelése
* Érdekesebb esetekre példák képernyőképekkel
* Teljesítmény, terhelhetőség vizsgálata
* Egységtesztekre vonatkozóan legalább javaslatok

## Összefoglalás

_(1-2 oldal)_

1-2 oldal. Elég csak a végén megcsinálni.

## Hivatkozások

_(1-2 oldal)_

* Java
* Java Spring (Spring in Action, több helyre is behivatkozható)
* Angular
* JWT
* Táblás játékok implementációjával kapcsolatban leírások (akár szabálygyűjtemény)


