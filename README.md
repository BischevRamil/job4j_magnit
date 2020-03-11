[![Build Status](https://travis-ci.org/BischevRamil/job4j_magnit.svg?branch=master)](https://travis-ci.org/BischevRamil/job4j_magnit)


[![codecov](https://codecov.io/gh/BischevRamil/job4j_magnit/branch/master/graph/badge.svg)](https://codecov.io/gh/BischevRamil/job4j_magnit)

# Magnit

В этом приложении выполнено тестовое задание в одну из фирм.
Производятся манипуляции над данными. 

1. Сначала в СУБД SQLite генерируется определенное количество записей.
2. Эти записи выводятся из табицы в структуру List<>.
3. Затем из List<> формируется XML-файл с помощью библиотеки JAXB.
4. Сформированный XML-файл конвертируется в HTML-файл с использованием схемы xsl.

Проект интегрирован в CI/CD Travis. Есть логирование ошибок с помощью Log4j. 