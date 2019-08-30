# EveTrader
"EVE Online Trader"

Desktop application that allows EVE Online player to search for profitable buy/sell orders in certain regions.
Not intended for mass use, as it's getting less useful with each user due to it's nature.

## Supported languages
- English
- Russian

English is used by default.

Чтобы включить русский язык добавьте *\[-Duser.language=ru -Duser.country=RU]* к параметрам запуска.<br />
Например: **java -Duser.language=ru -Duser.country=RU -jar ./JET-1.8.jar**

## Tools used
- JavaFX
- Spring (Core, AOP, REST)
- Guava EventBus
- Apache HttpComponents
- Ebean ORM
- H2 DB

## Build requirements for Java 11 version
JDK 11 or higher;
[tools](https://github.com/tddts/tools) dependency version 2.0;
[sprix](https://github.com/tddts/sprix) dependency version 11.0;

## Download
[Java 11 version](https://github.com/tddts/EveTrader/raw/11/bin/eve-trader-11.0.jar)
