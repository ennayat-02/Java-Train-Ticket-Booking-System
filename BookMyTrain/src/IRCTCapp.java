import java.util.List;
import java.util.Scanner;

public class IRCTCapp
{
    private final Scanner scanner=new Scanner(System.in);
    private final UserService userService=new UserService();
    private final BookingServices bookingServices=new BookingServices();

    public static void main(String[] args) {
     new IRCTCapp().start();
    }
    public void start()
    {
        while (true){
            System.out.println("-------Welcome to TBS-------");
            if(!userService.isLoggedIN()){
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter Choice:");
                int choice=scanner.nextInt();

                switch (choice){
                    case 1 ->register();
                    case 2 ->login();
                    case 3 ->exitApp();
                    default -> System.out.println("Invalid Choice");
                }

            }
            else {
                showUsermenu();
            }

        }
    }

    public  void register()
    {
        System.out.print("Enter Username:");
        String username=scanner.next();
        System.out.print("Enter Password:");
        String password=scanner.next();
        System.out.print("Enter full name:");
        scanner.nextLine();
        String fullName=scanner.nextLine();
        System.out.print("Enter your Contact:");
        String contact=scanner.next();

        userService.registerUser(username,password,fullName,contact);
    }

    public void login()
    {
        System.out.print("Enter Username:");
        String username=scanner.next();
        System.out.print("Enter Password:");
        String password=scanner.next();
        userService.loginUser(username,password);
    }

    private void showUsermenu()
    {  while (userService.isLoggedIN())
        {
            System.out.println("\n-----User Menu-----");
            System.out.println("1. Search Trains");
            System.out.println("2. Book Tickets");
            System.out.println("3. View Tickets");
            System.out.println("4. Cancel Tickets");
            System.out.println("5. View all Trains");
            System.out.println("6. logout");
            System.out.print("Enter Choice:");
            int choice=scanner.nextInt();

            switch (choice){
                case 1 ->searchTrain();
                case 2 ->bookTicket();
                case 3 ->viewTickets();
                case 4 ->cancelTickets();
                case 5 ->bookingServices.listallTrains();
                case 6 ->userService.logoutUser();
                default -> System.out.println("Invalid Choice");
            }




        }
    }

    private void searchTrain()
    {
        System.out.print("Enter source Station:");
        String source=scanner.next();
        System.out.print("Enter Destination Station:");
        String destination=scanner.next();

        List<Train> trains=bookingServices.searchTrain(source,destination);
        if(trains.isEmpty())
        {
            System.out.println("No Trains found bw"+source+"and"+destination);
        }
        System.out.println("Train found:");
        for(Train train:trains)
        {
            System.out.println(train);
        }
        System.out.print("Do you want to book ticket? (yes/no):");
        String choice= scanner.next();

        if(choice.equalsIgnoreCase("yes"))
        {
            System.out.print("Enter train id to Book:");
            int trainID=scanner.nextInt();
            System.out.print("Enter number of Seats to book:");
            int seats=scanner.nextInt();


            Ticket ticket=bookingServices.bookTicket(userService.getCurrentUser(),trainID,seats);
            if(ticket!=null){
                System.out.println("Booking Sucessfull!");
                System.out.println(ticket);
            }

        }
        else
        {
            System.out.println("Returning to User menu....");
        }
    }

    private void bookTicket()
    {
        System.out.println("Enter source Station:");
        String source=scanner.next();
        System.out.println("Enter Destination Station:");
        String destination=scanner.next();

        List<Train>trains=bookingServices.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No Trains Available for booking");
            return;
        }
        System.out.println("Available Trains");
        for(Train train:trains)
        {
            System.out.println(train);
        }
        System.out.println("Enter train id to Book");
        int trainID=scanner.nextInt();
        System.out.println("Enter number of Seats to book");
        int seats=scanner.nextInt();


        Ticket ticket=bookingServices.bookTicket(userService.getCurrentUser(),trainID,seats);
        if(ticket!=null){
            System.out.println("Booking Sucessfull!");
            System.out.println(ticket);
        }
    }

    private void viewTickets()
    {
        List<Ticket>ticketByUser=bookingServices.getTicketByUser(userService.getCurrentUser());
        if(ticketByUser.isEmpty()){
            System.out.println("no ticket found!");
        }
        else{
            System.out.println("Your Tickets:");
            for(Ticket ticket:ticketByUser){
                System.out.println(ticket);
            }
        }
    }

    private void cancelTickets()
    {
        System.out.println("Enter Ticket Id: to cancel:");
        int ticketID=scanner.nextInt();
        bookingServices.cancelTicket(ticketID,userService.getCurrentUser());
    }

    private void exitApp(){
        System.out.println("Thank you for using APP");
        System.exit(0);
    }
}
