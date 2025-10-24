public class Train {
    private int trainID;
    private String name;
    private String source;
    private String destination;
    private int totalSeats;
    private int availSeats;


    public Train(int trainID, String name, String source, String destination, int totalSeats) {
        this.trainID = trainID;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availSeats = totalSeats;//when train is empty so totalSeats=availSeats

    }

    public int getTrainID() {
        return trainID;
    }

    public void setTrainID(int trainID) {
        this.trainID = trainID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailSeats() {
        return availSeats;
    }

    public void setAvailSeats(int availSeats) {
        this.availSeats = availSeats;
    }

    public boolean bookSeats(int count){
        if(count<=availSeats){
            availSeats-=count;
            return true;
        }
        return false;
    }

    public void cancelSeats(int count){
        availSeats+=count;
    }



    @Override
    public String toString() {
        return trainID+"|"+name+"|"+source+"->"+destination+ "| Seats Available:"+availSeats;
    }
}
