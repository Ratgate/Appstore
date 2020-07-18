# Appstore

Gra dla jednej lub wielu osób

Przed rozgrywką trzeba podać liczbę osób (dla gry jednoonsobowej jest to domyślnie 1) i każdy gracz musi podać swoje unikatowe imię

Każdy dzień składa się z tury każdego gracza.
W każdej turze gracz może:

1 - Podpisać umowę na realizację dostępnego projektu z listy dostępnych projektów

2 - Poszukać nowych projektów, szukanie każdego gracza lub sprzedawcy przybliża pojawienie się nowego projektu o 1/5

3 - Programować wraz ze swoim zespołem programistów, gdzie każdy postęp w każdej dziedzinie generuje punkt testów a gracz bez zespołu nie może programować skomplikowanych

4 - Testować wraz ze swoją ekipą testerską, gdzie każdy tester obniża punkt testów projektu zależnie od siły testowania (obecnie 3)

5 - Oddać projekt klientowi, co przeniesie projekt do listy finalizowanych projektów

6 - Rekrutować nowego pracownika albo z listy dostępnych pracowników albo wydać pieniądze na dodanie nowego pracownika do listy z możliwością jego natychmiastowej rekrutacji

7 - Zwolnić pracownika

8 - Użerać się z biurokracją, gdzie nie poświęcenie temu 2 dni w miesiącu doprowadzi gracza do przegranej

9 - nic nie robić cały dzień

W trakcie swojej tury gracz może nie tracąc tury
1 - sprawdzić stan realizacji poszczególnych projektów
2 - przejrzeć dostępne projekty
3 - przejrzeć dostępnych pracowników
4 - sprawdzić stan konta

Gra trwa tak długo, aż wszyscy gracze odpadną lub pierwsza osoba 
odbierze całą zapłatę za 3 skomplikowane projekty w ramach których nie wykonał żadnych prac programistycznych ani testerskich, z których co najmniej 1 został pozyskany przez zatrudnionego sprzedawcę



Brakujące/niedokończone elementy:



Oddawanie skończonych projektów i oczekiwanie na zapłate za nie lub na problemy wynikające z braku testów

Przez cały okres od oddania projektu aż do otrzymania zapłaty każdy punkt testowy będzie generował chyba 2% szansy na to, że coś się popsuje. Zależnie od klienta będzie to miało różne efekty

Każdy projekt ma klienta ale brakuje obsługi klientów przy oddawaniu projektów

Klient
- wyluzowanych - 30% szans na opóźnienie płatności o tydzień, ale też 20% szans na uniknięcie kary za opóźnienie, jeżeli nie jest większe niż tydzień, oddanie niedziałającego projektu nie powoduje dodatkowych problemów
- wymagających - 0% szans na opóźnienie płatności, 0% szans na uniknięcie kary, oddanie niedziałającego projektu to 50% szans na utratę kontraktu bez zwrotu kosztów
- skrwl - 30% szans na opóźnienie płatności o tydzień, 5% szans na opóźnienie płatności o miesiąc, 0% szans na uniknięcie kary, 100% szans na utratę kontraktu po oddaniu niedziałającego projektu, 1% szans nie nieuzyskanie płatności nigdy

Obecnie niemal wszędzie umiejętności programistyczne są przedstawione jako niewiele mówiące tablice 0 i 1. Brakuje im oznaczeń

Skills
-Mobile
-Frontend
-Back
-Front-end
-Wordpress
-Prestashop

Podwykonawcy są niewytłumaczeni, dlatego jeszczę przemyślię czy zostaną wprowadzeni

Podwykonawcy
-najlepszy uczeń - najdroższy, ale robi na czas i bez błędów
-średni uczeń - robi na czas, ale jest 10% ryzyka, że trzeba będzie po nim poprawiać 
-koleś, który wie wszystko najlepiej - najtańszy, 20% ryzyka że się spóźni i 20% ryzyka że będzie trzeba po nim poprawiać.
Na początku każdej rozgrywki wylosuj zestaw umiejętności dla każdego z nich.


Brakujące akcje w turze każdego gracza
-oddać gotowy projekt klientowi - to wymaga utworzenie w graczu nowej listy projektów finalizowanych, gdzie czekają od oddania do zapłaty i patrzy się, czy punkty testów coś zepsują


Warunki zwycięstwa
pełnej zapłaty za 3 duże projekty, w ramach których właściciel firmy nie wykonał żadnych prac programistycznych ani testerskich, z których co najmniej 1 został pozyskany przez zatrudnionego sprzedawcę

Funkcjonalności dodatkowe
-Dodaj obsługę projektów, które mają kilka etapów (10%) - częściej musisz poświęcać czas na oddanie prac, ale częściej dostajesz pieniądze.

Testowanie
-Nie było to wyjaśnione, więc każdy punkt testowania daje 2% szansu dziennie na to, że oddawany projekt nie będzie działał
-konsekwencje niedziałającego projektu

Dodanie zabezpieczeń idiotproof
- wszędzie gdzie oczekuje się liczb trzeba sprawdzać, czy dostaje się liczby
- Można poblokować niektóre aktywności gdy są one niepotrzebne np. biurokrację kiedy nie ma na to potrzeby w miesiącu

Zobaczyćczy następujące tury zmniejszają deadline.
