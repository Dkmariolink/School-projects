// Justin Wright
// 4-17-16
// Program - Justin's Banking Program

/*

***GENERAL IDEA***
This programs primary function is to act as an ATM machine/bank for a user. In the initial menu, the user will be given the option of
creating an account, to login with said created account, to view promotions, and to quit the entire program. If the user logins, they are
presented the banking menu, which they will then be given the option of depositing money, withdrawing, checking total balance, view
transaction history, and quit to the main menu. I did additional research and decided the best way to carry a running total through
multiple void functions was to use a class, and I used class atm.
We were given a couple options for extra credit, such as implementing the option of
creating more than one user, and to store transactions to a file. I attempted both, but couldn't get a solid grasp on it, so I
did not implement those into this program.

***ALGORITHM***
1. Include fstream, ctime, string, and iomanip for additional c++ functionality
2. Declare prototypes
3. Create a class in order to carry running total through void functions
4.Create the main menu function and implement it into main by using a switch statement, this is the initial menu for the user.
5.Create functions for login, create account, view promotions. Login and create account both use files, ofstream for create account
since its used to save information into the file, ifstream for login as it reads off the file. The file is named "accounts.txt"
6.Create the banking menu function and implement it into the login function by using a switch statement. This is so that it only
opens if the user successfully logins by using an if/else statement.
7. Create functions for deposit, withdraw, check balance, and view history. By using the class atm, I could carry balance (bal)
through all the void functions.
8. All deposits, withdraw, and check balance requests need to be stored in a file named "transHistory.txt" to be used in view history
function.
9. A nested while loop in order to keep the user inside the banking menu after completing an action.
10. Lastly, create a time function that will be used to show the time a user does an action inside the view trans history menu option.

***Menu loop example***

MAIN MENU
if user logins, transfer to...
BANKING MENU
if user presses 'q' or 'Q', transfer to...
MAIN MENU
if user presses 'q' or 'Q' in main menu, program terminates
*/

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <ctime>
#include <string>
#include <iomanip>




using namespace std;

    char mainMenu();
    void createAccount(); // User creates own username/password to and is stored in accounts.txt
    char bankingMenu(); // Separate menu if user logins
    string Date_Time(); // Time gathering for transactions
   class atm{ // Class is used in order to carry the variable bal through void functions
    long double bal=0; // Balance, running total used in functions
    public :
    void deposit(void); // Will add money to the bal variable
    void withdraw(void); // Will subtract withdraw from bal
    void login(void); // Login achieved by using the accounts.txt file to check for username/password match
    void displayBalance(); // Display total balance
    void ShowTransHist();}; // Gets transaction history from the file path string transhistory.txt and display
int main() // Not much in my int main besides the main menu switch statement
{

    char choice;
    atm account;
    while (choice != 'q' || choice != 'Q') // Main menu will loop as long as user does not choose to quit
    {
        choice = mainMenu();
        if (choice == 'q' || choice == 'Q'){
         cout << "Thanks for stopping by Justin's Bank!";   break;} // I use break a lot in this program. Great for terminating/going back a menu
        switch (choice)
        {
        case 'l':
        case 'L':
            account.login(); // Leads to banking menu
            break;
        case 'c':
        case 'C':
            createAccount();
            break;
        case 'v':
        case 'V':
            cout << "Thank you for using our bank and Future Computer Programmer ATM Machine! \nFor your continued support, we are offering 3% cash back on all debit purchases." << endl;
        default:
            cout << "Invalid choice!" << endl;
        }
    }
    return 0;
}
char mainMenu() // Function to display the main menu, not banking menu
{
    char choice;
    cout << "********** MAIN MENU ********** " << endl << endl;
    cout << "l(L) -> Login to Banking Menu" << endl;
    cout << "c -> Create New Account" << endl;
    cout << "v -> View Promotions" << endl;
    cout << "q -> Quit the Program" << endl << endl;
    cout << "******************************* " << endl;
    cout << "Enter your choice:" << endl;
    cin >> choice;
    return choice;

}
void createAccount() // Takes and stores users login information, username and password. The instructions did not state the need to create multiple accounts and store them
{
   string username;
   string password;
   cout << "Please create a username" << endl;
   cin >> username;
   cout << "Please create a password" << endl;
   cin >> password;
   ofstream createaccount; // ofstream to save information
   createaccount.open("accounts.txt"); // Will store the users information into accounts.txt file
   createaccount << username << " " << password << endl;
   createaccount.close();
   cout << "Account created!" << endl;

}
void atm :: login(void) // Takes information stored in create account. Most loaded function, also holds banking menu.
{
   string username;
   string password;
   char choice;
   atm decision; // atm class
   ifstream savedaccount; // ifstream to read information from file
   savedaccount.open("accounts.txt"); // accounts.txt will verify user information.
   cout << "Enter your username:" << endl;
   cin >> username;
   cout << "Enter your password:" << endl;
   cin >> password;
   string username2, password2;
   savedaccount >> username2 >> password2;
   savedaccount.close();
   if (username != username2 || password != password2)
    cout << "Incorrect login! Create new account or try again." << endl; // If user fails to login
    while(username == username2 && password == password2) // Achieved by using accounts.txt match
    {
        choice = bankingMenu(); // Placed function call here so that banking menu will only appear if user logins.
        if (choice == 'q' || choice =='Q') break; // Banking menu will loop as long as choice is not to return to main menu.
        switch (choice){


case 'd':
case 'D':
    decision.deposit();
    break;
case 'w':
case 'W':
    decision.withdraw();
    break;
case 'b':
case 'B':
    decision.displayBalance();
    break;
case 'r':
case 'R':
    decision.ShowTransHist();
    break;
default:
    cout << "Invalid choice!" << endl;

        }
}
}
char bankingMenu() // Banking menu, only opens if user achieves login.
{
    char choice;
    cout << "********* BANKING MENU ********" << endl << endl;
    cout << "d. Deposit Money" << endl;
    cout << "w. Withdraw Money" << endl;
    cout << "b. Check Balance" << endl;
    cout << "r. Review Account Activities History" << endl;
    cout << "q. Return to Main Menu" << endl << endl;
    cout << "*******************************" << endl;
    cout << "Enter your choice:" << endl;
    cin >> choice;
    return choice;
    }
void atm :: deposit(void) // Takes bal variable from bank class and keeps it as a constant total, deposit adds to bal
{
   long double deposit;
   string time = Date_Time(); // So that date for each deposit is saved into transhistory.txt, Date_Time is called and assigned to time
   cout << "Enter how much you would like to deposit: ";
   cin >> deposit;
   bal = deposit+bal;
   cout << "You have deposited: $" << setprecision(10) << deposit << endl; // setprecision is used so that the user can enter lots of money
   if (deposit >= 100000)
    cout << "Now that is a lot of money!" << endl; // Easter egg
    ofstream file;
    file.open("transHistory.txt", std::ios_base::app); // std::ios_base::app used in order to keep more than one user input in file
    file << time << "Deposit $" << setprecision(10) << deposit << endl;

}
void atm :: withdraw(void) // Similar to deposit, but subtracts from bal.
{
    long double withdraw;
    string time = Date_Time();
    cout << "Enter how much you would like to withdraw: ";
    cin >> withdraw;
    if (bal > withdraw || bal == withdraw){
    bal= bal - withdraw;
    cout << "You have withdrawn: $" << setprecision(10) << withdraw << endl;}
    else if (bal < withdraw)
    cout << "You don't have enough funds to cover your withdraw request!" << endl;
    ofstream file;
    file.open("transHistory.txt", std::ios_base::app);
    file << time << "Withdraw $" << setprecision(10) << withdraw << endl;
}
void atm :: displayBalance() // Deposits and withdrawls are taken into account, and the constant total bal is displayed here.
{
    string time = Date_Time();
    cout << "Your current balance is: $" << setprecision(10) << bal << endl;
    if (bal >= 1000000)
        cout << "Wow, you're a high roller!" << endl; // More easter eggs, I have fun with this!
        ofstream file;
        file.open("transHistory.txt", std::ios_base::app);
        file << time << "Check Balance $" << setprecision(10) << bal << endl;

}
void atm :: ShowTransHist() //Gets the transaction history from the file path string and display
{
   ifstream file;
   string history;
   file.open("transHistory.txt"); // Displays transaction history
   if (file.is_open()){
 while (file >> history) { // Reads all lines in the file by looping
    cout<< history << " ";// Spacing is off


 }
}
file.close();

}
string Date_Time() // Function to get date and time
{
    time_t now = time(0);
    string dt=ctime(&now);
     return dt;

}
