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

Projekt dostarczony do klienta czeka określony czas, by zostać sfinalizowany.
Oddany projekt, jeżeli przekroczył swój deadline, może w zależności od klienta nałożyć karę i zmniejszyć zapłatę.
W trakcie finalizacji, jeżeli projekt nie był dostatecznie testowany, może mieć problemy i zależnie od klienta, może zostać kontrakt zerwany bez pokrycia kosztów.
Jeżeli projekt przetrwa finalizację, zależnie od klienta, projekt zostaje zapłacony od razu lub zapłata może być przesunięta (ale tylko raz).

Gra trwa tak długo, aż wszyscy gracze odpadną lub pierwsza osoba 
odbierze całą zapłatę za 3 skomplikowane projekty w ramach których nie wykonał żadnych prac programistycznych ani testerskich, z których co najmniej 1 został pozyskany przez zatrudnionego sprzedawcę



Brakujące/niedokończone elementy:

Obecnie niemal wszędzie umiejętności programistyczne są przedstawione jako niewiele mówiące tablice 0 i 1. Brakuje im oznaczeń

Skills
-Mobile
-Frontend
-Back
-Front-end
-Wordpress
-Prestashop

Podwykonawcy są niewytłumaczeni, dlatego jeszczę przemyślię czy i jak zostaną wprowadzeni

Podwykonawcy
-najlepszy uczeń - najdroższy, ale robi na czas i bez błędów
-średni uczeń - robi na czas, ale jest 10% ryzyka, że trzeba będzie po nim poprawiać 
-koleś, który wie wszystko najlepiej - najtańszy, 20% ryzyka że się spóźni i 20% ryzyka że będzie trzeba po nim poprawiać.
Na początku każdej rozgrywki wylosuj zestaw umiejętności dla każdego z nich.

Funkcjonalności dodatkowe
-Dodaj obsługę projektów, które mają kilka etapów (10%) - częściej musisz poświęcać czas na oddanie prac, ale częściej dostajesz pieniądze.

Dodanie zabezpieczeń idiotproof
- wszędzie gdzie oczekuje się liczb trzeba sprawdzać, czy dostaje się liczby
- Można poblokować niektóre aktywności gdy są one niepotrzebne np. biurokrację kiedy nie ma na to potrzeby w miesiącu

Ulepszyć kod (Powtarzający się kod zastąpić instacjami jednego kodu, usunąć ostrzeżenia, zrefaktoryzować co trzeba e.t.c)
