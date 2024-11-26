# TicketGuru: Testausdokumentaatio

Tässä dokumentaatiossa kerrotaan tarkemmin testattavat kohteet ja mitä kaikkia toimintoja testit kattoivat. Dokumentaatiossa käydään läpi myös se, miten testit toteutettiin sekä testituloksissa on kerrottu saatiinko kaikki testit suoritettua onnistuneesti Javan Test Runnerissa.

### Testattavat kohteet (JUnit-yksikkötestit)

### AppUserService-luokka:

Testit keskittyvät AppUserService-luokan toimintoihin eli käyttäjien luomiseen, päivittämiseen, hakemiseen ja poistamiseen. Tavoitteena on varmistaa, että AppUserService toimii odotetusti ja että mahdolliset virhetilanteet käsitellään oikein.

#### Testit kattavat seuraavat toiminnot:

- **getAllUsers:** Hakee kaikki käyttäjät.
- **getUserById:** Hakee käyttäjän ID:n perusteella. Jos ID:tä ei löydy, palauttaa tyhjän.
- **createUser:** Luo uuden käyttäjän. Jos käyttäjänimi on jo olemassa, heittää virheilmoituksen.
- **updateUser:** Päivittää olemassa olevan käyttäjän tiedot. Jos käyttäjää ei ole olemassa, palauttaa tyhjän.
- **deleteUser:** Poistaa käyttäjän. Jos käyttäjää ei ole, palauttaa false.

### SaleService-luokka:

Testit keskittyvät SaleService-luokan toimintoihin eli myyntitapahtumien käsittelyyn, kuten myynnin luomiseen, päivittämiseen, hakemiseen ja poistamiseen. Tavoitteena on varmistaa, että SaleService toimii odotetusti, ja että virhetilanteet käsitellään oikein.

#### Testit kattavat seuraavat toiminnot:

- **createSale:** Luo uuden myynnin käyttäen annettuja tietoja. Jos käyttäjää ei löydy, heittää virheilmoituksen.
- **updateSale:** Päivittää olemassa olevan myynnin tietoja. Jos myyntiä ei löydy, heittää virheilmoituksen.
- **getSaleById:** Hakee myynnin ID:n perusteella. Jos ID:tä ei löydy, heittää virheilmoituksen.
- **deleteSale:** Poistaa myynnin ID:n perusteella. Jos myyntiä ei löydy, heittää virheilmoituksen.

#### Miten testit toteutettiin?

Testeissä keskittyttiin yksittäisten metodien käyttäytymiseen. Yksikkötesteissä palvelun toiminnallisuus on eristettyä ja testejä suoritetaan ilman, että tarvitaan tietokantaa tai muita ulkoisia resursseja.

Testien suorittamiseen on käytetty mock-objekteja ja Mockito-kirjastoa, joiden avulla voidaan simuloida ulkoisia riippuvuuksia, kuten tietokantakutsuja ja salasanan enkoodauksen logiikkaa.

#### Tulokset:

Kaikki testit saatiin suoritettua onnistuneesti, joten AppUserService ja SaleService-luokat toimivat odotetulla tavalla. 

### Testattavat kohteet (Integraatiotestit)

### TicketResource-luokka:

Testit keskittyvät lipunhallinnan toimintoihin eli lippujen hakemiseen ja lipun merkitsemiseen käytetyksi. Tämä on ensimmäinen luokka, jolle kirjoitettiin integraatiotesti, joten luokka sisältää myös testin Liquibase-konfiguraatioiden tarkastamiseen. Tavoitteena on varmistaa, että liput voidaan hakea oikein ja että lipun käyttötilan päivittäminen toimii odotetusti.

#### Testit kattavat seuraavat toiminnot:

- **testLiquibaseInitialization:** Varmistaa, että Liquibase-konfiguraatiot on suoritettu ja tietokantayhteys on olemassa.
- **testGetAllTickets:** Hakee kaikki liput ja tarkistaa, että ne palautetaan oikein.
- **testFetchTicketByTicketNumber:** Hakee lipun lippunumeron perusteella ja tarkistaa sen tiedot.
- **testGetTicketsByEventId:** Hakee liput tapahtuman ID:n perusteella.
- **shouldReturnNotFoundForInvalidTicketNumber:** Yrittää hakea lippua virheellisellä numerolla.
**testMarkTicketAsUsedByNumber:** Merkitsee lipun käytetyksi ja tarkistaa, että se on päivittynyt oikein.

### SaleResource-luokka:

Testit keskittyvät Sale-resurssin integraatioon, joka käsittelee myyntitapahtumia, kuten myynnin luomista, hakemista, poistamista ja hakemista eri hakuehdoilla. Tavoitteena on varmistaa, että API toimii odotetusti ja virhetilanteet käsitellään oikein.

#### Testit kattavat seuraavat toiminnot:

- **createSale:** Luo uuden myynnin annetuilla tiedoilla.
- **getAllSales:** Hakee kaikki myynnit.
- **getSaleById:** Hakee myynnin ID:n perusteella.
- **getSaleByIdNotFound:**  Käsittelee tapauksen, jossa myyntiä ei löydy annetulla ID:llä.
- **deleteSale:** Poistaa myynnin ID:n perusteella.
- **searchSalesByUserId:** Hakee myyntejä käyttäjän ID:n perusteella.
- **searchSalesByInvalidUserId:** Yrittää hakea myyntejä virheellisellä käyttäjätunnuksella.
- **searchSalesBySaleIds:** Hakee myyntejä useilla myynti-ID:illä
- **searchSalesByInvalidSaleIds:** Yrittää hakea myyntejä virheellisillä myynti-ID:illä.
- **searchSalesInvalidSaleIdFormat:** Yrittää hakea myyntejä virheellisesti muodostetuilla myynti-ID:illä.
- **searchSalesMissingParameters:** Yrittää hakea myyntejä virheellisesti ilman parametreja.

### AppUserResource-luokka:

Testit keskittyvät AppUserResource-luokan käyttäjätoimintojen integroituun testaamiseen, eli varmistetaan, että käyttäjien luominen, päivittäminen, hakeminen ja poistaminen toimivat oikein todellisessa ympäristössä. Testeissä varmistetaan, että järjestelmä käsittelee odotetusti sekä onnistuneita että virheellisiä pyyntöjä API-kutsujen kautta. Testeissä otetaan huomioon myös mahdolliset virhetilanteet, kuten puuttuvat käyttäjät ja virheelliset syötteet.

#### Testit kattavat seuraavat toiminnot:

- **getAllUsers:** Hakee kaikki käyttäjät ja varmistaa, että palautettu vastaus on taulukko.
- **getUserById:** Hakee käyttäjän ID:n perusteella ja tarkistaa, että oikea käyttäjä palautetaan.
- **createUser:** Luo uuden käyttäjän ja varmistaa, että käyttäjä luodaan oikein.
- **updateUser:** Päivittää olemassa olevan käyttäjän tiedot ja tarkistaa, että tiedot on päivitetty oikein.
- **deleteUser:** Poistaa käyttäjän ja varmistaa, että poistettu käyttäjä ei ole enää saatavilla.
- **Virheelliset pyynnöt:** Testataan virheellisiä pyyntöjä, kuten käyttäjän luominen puuttuvilla kentillä tai olemattoman käyttäjän poistaminen.

### EventResource-luokka:

Testit keskittyvät EventResource-luokan toimintoihin, jotka käsittelevät tapahtumien hakemista, luomista, päivittämistä ja virhetilanteita. Tavoitteena on varmistaa, että EventResource toimii odotetusti ja että tapahtumien käsittelyä varten tehdyt HTTP-pyynnöt tuottavat oikeat vastaukset.

#### Testit kattavat seuraavat toiminnot:

- **getAllEvents:** Hakee kaikki tapahtumat ja varmistaa, että vastaus sisältää tapahtumatiedot oikeassa muodossa.
- **getEventById:** Hakee tapahtuman ID:n perusteella ja varmistaa, että oikea tapahtuma palautetaan.
- **createEvent:** Luo uuden tapahtuman ja tarkistaa, että luotu tapahtuma vastaa odotettuja tietoja.
- **updateEvent:** Päivittää olemassa olevan tapahtuman tiedot ja varmistaa, että muutokset on tallennettu oikein.
- **testEventNotFound:** Käsittelee virhetilanteen, jossa pyydettyä tapahtumaa ei löydy annetulla ID:llä ja varmistaa, että virheviesti palautetaan oikein.

#### Miten testit toteutettiin?

Halusimme testata palvelujen toimintaa kokonaisuutena, sisältäen API-pyyntöjen lähettämisen ja tietokannan kanssa kommunikoimisen. Nämä testit eivät rajoitu vain yksittäisiin metodeihin, vaan ne varmistavat, että sovelluksen eri osat toimivat yhteen ja kommunikoivat oikein.

Testit suoritetaan MockMvc-kirjaston avulla, joka simuloi HTTP-pyyntöjä ja vastaanottaa vastauksia ilman, että tarvitaan oikeaa käyttäjää tai käyttöliittymää. MockMvc-objekti suorittaa API-pyynnöt ja tarkistaa vastauksia käyttäen HTTP-statuskoodeja ja JSON-vastausten sisältöä. Näin varmistetaan, että API-toiminnallisuus on oikea, ja että se palauttaa oikeat virheviestit virheellisissä tilanteissa.

Testin konfiguraatiot on suoritettu application-test.properties-tiedostossa, jossa on määritelty, että testeissä käytetään ajonaikaista H2-kantaa. Kaikkien toiminnallisuuksien onnistumiseksi testeihin on määritelty, että siitä huolimatta käytetään MariaDB-dialectiä. Tietokannan skeemaa hallinnoidaan Liquibasessa, jossa sijaitsee myös testeissä käytettävä testidata.

Testeissä ei testata lainkaan auktorisointia, ja se onkin kytketty pois päältä erillisessä TestSecurityConfig-luokassa, jota testit käyttävät.

#### Tulokset:

Kaikki testit saatiin suoritettua onnistuneesti, joten TicketResource, SaleResource, AppUserResource ja EventResource-luokat toimivat kaikki odotetulla sekä halutulla tavalla. 