Program to platforma handlowa dla pasjonatów kolarstwa. Jest to wersja rozwojowa. Kod pisany w języku JAVA za pomocą 
IDE ItelliJIDEA, oparty na szkielecie SpringBoot, korzystając z domyślnej konfiguracji. Bazy danych na serwerze własnym, 
użyty silnik szablonów to Thymeleaf.

Na dzień dzisiejszy zaimplementowano następujące funkcjonalności:
- rejestracja, logowanie (wraz z podziałem na admin/user, domyślnie: guest), baza użytkowników zapisana na serwerze, 
      //todo: walidacja i szyfrowanie haseł, wyświetlanie i akceptacja regulaminu
- wystawianie i przeglądanie ogłoszeń dla kategorii "rowery", przypisanie ogłoszenia do użytkownika, ogłoszenia zapisane
  na serwerze;
      //todo: kolejne kategorie, ładowanie grafiki, walidacja danych wpisanych przez użytkownika
      
Pozostają do zrobinia: logika innych kategorii, ładny front-end oraz deployment aplikacji na serwer.
