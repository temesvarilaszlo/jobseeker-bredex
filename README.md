# Továbbfejlesztési lehetőségek

* Authentication & Authorization: Implementálni a kliens API kulcsainak biztonságos kezelését, valamint bevezetni egy autentikációs és autorizációs rendszert.
* HTTPS használata az adatok titkosításához az átvitel során.
* Bevezetni egy rate limiting mechanizmust a túlterhelés elkerülése érdekében.
* Implementálni API verziózást, hogy biztosítsuk a visszafelé kompatibilitást a kliensalkalmazások számára.

# Konfigurálás és futtatás

* Java 11 telepítése
* .jar fájl letöltése
* Abban a mappában, ahol a .jar fájl van, a terminál megnyitása és az alábbi parancs kiadása:

```
java -jar jobseeker-0.0.1-SNAPSHOT.jar
```
* A szerver alapértelmezetten a localhost:8080 porton fog futni
* A --server.port kapcsolóval a portot ki lehet választani
```
java -jar jobseeker-0.0.1-SNAPSHOT.jar --server.port=8081
```
* Ezen lépések után az alkalmazás a kiválasztott porton fut és használhatóak a végpontok