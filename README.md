# an-application-to-manage-company-resources
 An application for making human resources, products, cash and commodities available in the company.


**Remember to change the username and password in db.DBConnector getConnectionWithoutDatabase () and 
getConnection () to the username and password for your MySQL to be able to run the program!**

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ENGLISH------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Application for managing the company, employees, documents and suppliers.
Program connected to MySQL version 8.0.15


Currently, the Employees tab is fully operational

After pressing View from the MySQL database all employees are pulled out
[![firma-1.png](https://i.postimg.cc/Y09x4JBg/firma-1.png)](https://postimg.cc/7GFzdt6Y)


After pressing Add, a new window opens, where we can enter the data of the new employee.
If all entered data is correct, we get a window in which we must confirm the willingness to add an employee to the database.
[![firma-2.png](https://i.postimg.cc/3wk99kL6/firma-2.png)](https://postimg.cc/94jGQQHt)

After selecting the employee and clicking the Delete option, we receive a window in which we must first provide the ID of the employee we chose
[![firma-3.png](https://i.postimg.cc/tgG7vfDj/firma-3.png)](https://postimg.cc/75XxfKps).
In addition, another window appears confirming removal of the employee from the database. Nobody would want to do it by accident.
[![firma-4.png](https://i.postimg.cc/3JH8TpmX/firma-4.png)](https://postimg.cc/5jnd5XPy)
If an employee is not selected, we will receive window information "No employee selected!".


After selecting an employee, and then clicking Edit, a new window opens in which the data is automatically set to the data of the selected employee. We can change them freely and save changes after confirming all queries.
[![firma-5.png](https://i.postimg.cc/3JrqmxTM/firma-5.png)](https://postimg.cc/Sj5VpqRV)
Result of display of all employees after editing:
[![firma-6.png](https://i.postimg.cc/KzwnzQxv/firma-6.png)](https://postimg.cc/1n0g7G8L)

The next steps will be the creation of a invoice management system in which I plan to create methods for creating invoices to PDF.

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------POLSKI--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Aplikacja do zarządania firma, pracownikami, dokumentami oraz dostawcami.
Program połączony z MySQL version 8.0.15

Aktualnie w pełni działająca jest zakładka Pracownicy

Po wciśnięciu Wyświetl z bazy danych MySQL zostają wyciągnięci wszyscy pracownicy.
[![firma-1.png](https://i.postimg.cc/Y09x4JBg/firma-1.png)](https://postimg.cc/7GFzdt6Y)

Po wciśnięciu Dodaj otwiera się nowe okno, gdzie możemy wprowadzić dane nowego pracownika.
Jeżeli wszystkie wprowadzone dane są poprawne otrzymujemy okno w którym musimy potwierdzić chęć dodania pracownika do bazy danych.
[![firma-2.png](https://i.postimg.cc/3wk99kL6/firma-2.png)](https://postimg.cc/94jGQQHt)

Po wybraniu pracownika i kliknięciu opcji Usuń otrzymujemy oknow w którym musimy podać najpierw ID pracownika, którego wybraliśmy.
[![firma-3.png](https://i.postimg.cc/tgG7vfDj/firma-3.png)](https://postimg.cc/75XxfKps)
Dodatkowo wyskakuje kolejne okno z potwierdzeniem usunięcia pracownika z bazy danych. Nikt nie chciałby przez przypadek tego zrobić.
[![firma-4.png](https://i.postimg.cc/3JH8TpmX/firma-4.png)](https://postimg.cc/5jnd5XPy)
Jeżeli nie zostanie wybrany pracownik to otrzymamy informacje okienkową "Nie wybrano pracownika!".

Po wybraniu pracownika, a następnie kliknięciu Edytuj otwiera się nowe okno w którym dane są automatycznie ustawiane na dane wybranego pracownika. Możemy je dowolnie zmieniać i zapisać zmiany po zatwierdzeniu wszystkich zapytań.
[![firma-5.png](https://i.postimg.cc/3JrqmxTM/firma-5.png)](https://postimg.cc/Sj5VpqRV)
Wynik wyświetlenia wszystkich pracowników po edytcji:
[![firma-6.png](https://i.postimg.cc/KzwnzQxv/firma-6.png)](https://postimg.cc/1n0g7G8L)


Następnymi krokami będzie utworzenie systemu zarządzania fakturami w których planuję stworzyć metody do tworzenia faktur do postaci PDF.
