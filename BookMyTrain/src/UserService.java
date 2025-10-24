import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<String,User> userMap=new HashMap<>();
    private User currentUser=null;
    public boolean registerUser(String username,String password,String fullname,String contact){
        if(userMap.containsKey(username)){
            System.out.println("username already exits,Please choose another");
            return false;
        }
        User user=new User(username,password,fullname,contact);
        userMap.put(username,user);
        System.out.println("Registration Sucessfull!");
        System.out.println("\n");
        return true;
    }

    public boolean loginUser(String username,String password){
        if(!userMap.containsKey(username)){
            System.out.println(" No Username found of this Username");
            return false;

        }
        User user=userMap.get(username);
        if(!user.getPassword().equals(password)){
            System.out.println("Incorrect Password");
            return false;
        }
        currentUser=user;
        System.out.println("Welcome"+" "+currentUser.getFullname()+"!");
        return true;
    }

    public void logoutUser(){
        if(currentUser!=null){
            System.out.println("logged Out"+currentUser.getFullname());
        }
        currentUser=null;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public Boolean isLoggedIN(){
        return currentUser!=null;
    }
}
