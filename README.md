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


Currently, the Employees tab is fully operational.

Adding, deleting, editing and all type information windows "do you recommend adding?" , "employee added", "confirm removal" are asa logical as in the case of the employee panel.
[![supplier-ss1.png](https://i.postimg.cc/zGMscmMG/supplier-ss1.png)](https://postimg.cc/Yv6DvPM5)
[![supplier-ss2.png](https://i.postimg.cc/bNfKXcq6/supplier-ss2.png)](https://postimg.cc/0rZtm4Mm)
[![supplier-ss3.png](https://i.postimg.cc/kgnz4Q6K/supplier-ss3.png)](https://postimg.cc/4mSB2h1x)

However, the suppliers' panel has the option of ordering goods. After displaying the list of suppliers and selecting the appropriate supplier, we can click the "order" button.

[![supplier-ss4.png](https://i.postimg.cc/V6vQGjBX/supplier-ss4.png)](https://postimg.cc/67kmT4PQ)
A window for placing orders is loaded. We display a list of goods that has a specific seller in its offer.

We select the product, give the number of pieces (currently only full numbers), and then press the "add to order" button.
It is worth noting that the total amount of the order changes on a regular basis after adding the product to the order.
[![supplier-ss5.png](https://i.postimg.cc/MGvJt7YV/supplier-ss5.png)](https://postimg.cc/qtVFvCSv)


After adding several products from different suppliers, press the "show current order" button. We are loading a window with a list of products contracted from the MySQL table "unfinished_order".
[![supplier-ss6.png](https://i.postimg.cc/QNSSN2DN/supplier-ss6.png)](https://postimg.cc/c6gfX56P)


The products can be quickly and easily removed from our current order using the "delete" button.
[![supplier-ss7.png](https://i.postimg.cc/59PqW856/supplier-ss7.png)](https://postimg.cc/14qNpnjQ)


If the order is ready, press the "place an order" button. At the moment, a table is automatically created that has a unique number generated in the name and all products that were previously in the unfinished_order table are removed to prepare tables for the next order.
[![supplier-ss8.png](https://i.postimg.cc/7Y03Gj8T/supplier-ss8.png)](https://postimg.cc/PPfp03qt)


Currently, the program does not have a loading window ready for suppliers, therefore I attach a screen with MySQL to show it.
[![supplier-ss9.png](https://i.postimg.cc/GphGf2Bk/supplier-ss9.png)](https://postimg.cc/Sn3n2mfs)


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
