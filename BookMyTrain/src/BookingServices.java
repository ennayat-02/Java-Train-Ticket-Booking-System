import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingServices {
    private List<Train> trainList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();

    public BookingServices() {
        trainList.add(new Train(101, "Rajdhani Express", "Delhi", "Nagpur", 100));
        trainList.add(new Train(102, "Shatabdi Express", "Delhi", "Mumbai", 60));
        trainList.add(new Train(103, "Durontp Express", "Agra", "Delhi", 70));
        trainList.add(new Train(104, "Vande Bharat", "Delhi", "Goa", 100));
        trainList.add(new Train(105, "Intercity", "Kolkata", "Manali", 90));
        trainList.add(new Train(106, "Tejas", "Delhi", "Bengaluru", 90));

    }

    //add filer of train date also as HMW
    public List<Train> searchTrain(String source, String destination) {
        List<Train> res = new ArrayList<>();
        for (Train train : trainList) {
            if (train.getSource().equalsIgnoreCase(source) && train.getDestination().equalsIgnoreCase(destination)) {
                res.add(train);
            }
        }
        return res;
    }

    public Ticket bookTicket(User user, int trainId, int seatCount)
    {
        for (Train train : trainList) {
            if (train.getTrainID() == trainId) {
                if (train.bookSeats(seatCount)) {
                    Ticket ticket = new Ticket(user, train, seatCount);
                    ticketList.add(ticket);
                    return ticket;
                } else {
                    System.out.println("Not Enough Seats");
                    return null;
                }
            }
        }
        System.out.println("Train Id not found");
        return null;
    }


    public List<Ticket>getTicketByUser(User user)
    {
        List<Ticket>res=new ArrayList<>();
        for(Ticket ticket:ticketList){
            if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                res.add(ticket);
            }
        }
        return res;
    }

    public boolean cancelTicket(int ticketId,User user)
    {
        Iterator<Ticket> iterator=ticketList.listIterator();
        while(iterator.hasNext()){
            Ticket ticket=iterator.next();
            if(ticket.getTicketId()==ticketId && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                Train train=ticket.getTrain();
                train.cancelSeats(ticket.getSeatBooked());
                iterator.remove();
                System.out.println(("Ticket"+ticketId+"cancelled Success"));
                return true;
            }

        }
        System.out.println("Ticket Not Found");
        return false;
    }

    public void listallTrains()
    {
        System.out.println("List of all Trains:");
        for(Train train:trainList)
        {
            System.out.println(train);
        }
    }

}

