// simple code for ATM Interface
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMINTERFACE extends JFrame implements ActionListener 
{
    private JLabel bal_Label;
    private double acc_Balance;
    private BankAccount bankAccount;

    public ATMINTERFACE(BankAccount account) {
        setTitle("SIMPLE ATM");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        bankAccount = account;
        acc_Balance = bankAccount.getBalance();

        // Create components for ATM interface
        JPanel opt_Panel = new JPanel(new GridLayout(3, 1, 8, 8));
        JButton withdraw_button = new JButton("WITHDRAW");
        JButton deposit_button = new JButton("DEPOSIT");
        JButton check_balance_button = new JButton("CHECK BALANCE");

        withdraw_button.addActionListener(this);
        deposit_button.addActionListener(this);
        check_balance_button.addActionListener(this);

        opt_Panel.add(withdraw_button);
        opt_Panel.add(deposit_button);
        opt_Panel.add(check_balance_button);

        bal_Label = new JLabel("Your Account Balance is : RS " + acc_Balance, JLabel.CENTER);

        add(opt_Panel, BorderLayout.CENTER);
        add(bal_Label, BorderLayout.NORTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String Command = e.getActionCommand();
        switch (Command)
         {
            case "WITHDRAW":
                withdraw();
                break;
            case "DEPOSIT":
                deposit();
                break;
            case "CHECK BALANCE":
                display();
                break;
        }
    }

    // method for performing withdrawal operations
    private void withdraw() 
    {
        String Amount = JOptionPane.showInputDialog(this, "Enter the amount to withdraw:");
        if (Amount != null) {
            try {
                double amount = Double.parseDouble(Amount);
                if (amount > 0 && amount <= acc_Balance) 
                {
                    bankAccount.withdraw(amount);
                    acc_Balance = bankAccount.getBalance();
                    JOptionPane.showMessageDialog(this, "Withdrawal Successful");
                } 
                else if (amount > acc_Balance) 
                {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
                 else 
                {
                    JOptionPane.showMessageDialog(this, "Invalid amount");
                }
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
            }
        }
    }


    // method for performing deposit operations
    private void deposit() 
    {
        String Amount = JOptionPane.showInputDialog(this, "Enter the amount to deposit:");
        if (Amount != null) 
        {
            try {
                double amount = Double.parseDouble(Amount);
                if (amount > 0) 
                {
                    bankAccount.deposit(amount);
                    acc_Balance = bankAccount.getBalance();
                    JOptionPane.showMessageDialog(this, "Deposit Successful");
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Invalid amount");
                }
                updateBalanceLabel();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter a valid number.");
            }
        }
    }

    // method for diplay balance
    private void display() 
    {
        JOptionPane.showMessageDialog(this, "Current balance: RS " + acc_Balance);
    }

    private void updateBalanceLabel() 
    {
        bal_Label.setText("Account Balance: RS " + acc_Balance);
    }

    public static void main(String[] args) 
    {
        BankAccount bankAccount = new BankAccount(0.00);
        SwingUtilities.invokeLater(() -> new ATMINTERFACE(bankAccount));
    }
}
class BankAccount 
{
    private double balance;
    public BankAccount(double initial_balance) 
    {
        balance = initial_balance;
    }

    public double getBalance()
    {
        return balance;
    }

    public void deposit(double amount) 
    {
        balance += amount;
    }

    public void withdraw(double amount) 
    {
        if (amount <= balance) 
        {
            balance -= amount;
        } 
        else 
        {
            System.out.println("Insufficient balance");
        }
    }
}
