
/* rename the file to User.java, and don't forget to run this file in the package */
package homework;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Arrays; 
import java.util.ArrayList;
//* 
/**
 *
 * @author ati
 */
/*  02. legyen User  osztály, amiben van privát név és privát passwd, 
    toString() metódusa, és két getter metódusa */ 
public class User implements Serializable{
    private String user_name; 
    private String user_pass; 
    @Override 
    public String toString(){
        return user_name +" "+user_pass;
    }
    public String getUserName() {
        return user_name; 
    }
    public String getUserPass() {
        return user_pass; 
    }
    User (String user_name, String user_pass){
        this.user_name = user_name; 
        this.user_pass = user_pass; 
    }

    /**
     * @param args the command line arguments
     */

      static File userSavedFile=new File("users.txt");

      public static void main(String[] args) {
        //01/a létrehoz egy listát négy névvel
        String[] user_names = new String[] {"Szabo", "Kis", "Nagy","Szasz"}; 
       //01/b létrehoz egy listát három jelszóval
        String[] user_passwd = new String[] {"alma", "barack", "dinnye"}; 
        
           
        List<User> userList = new ArrayList<User>();
        for (int i=0; i<10; i++){
            int random_number_one = 0+(int)(Math.random()*(4-0)) ;
            String randomUser;
            randomUser = user_names[random_number_one];
            String randomPass;         
            int random_number_two = 0+(int)(Math.random()*(3-0));
            randomPass = user_passwd[random_number_two];  
            //* 05 írja ki a felhasználók listáját konzolra
            User new_user = new User(randomUser.toString(), randomPass.toString());
            System.out.println(new_user.user_name +" "+new_user.user_pass);
            
            //* 04 írja ki a felhasználókat tartalmazó objektumok listáját objektumfolyamba 
            
        }
        try{
        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(userSavedFile)); 
        for (User user:userList)
            oos.writeObject(user); 
        oos.close(); 
        }
        catch(IOException e) {
            System.out.println("I/O error: "+e.getMessage());
    }
        for (User user : userList) {
      System.out.println(user.toString());
    }
    
    userList.clear();
    System.out.println("--x---x----x----x--");
         
    List <User> readUserList = new ArrayList<User>();
    try {
      ObjectInputStream ois=new ObjectInputStream(new FileInputStream (userSavedFile));
      User u = new User(null, null);
      for(int i=1; i<=10; i++)
      {
        u = (User)ois.readObject();
        readUserList.add(u);
      }
      ois.close();
    }
    catch(ClassNotFoundException e) {
      System.out.println("Wrong Class!");
    }
     catch(IOException e) {
     System.out.println("I/O error: "+e.getMessage());
    }
    
    readUserList.stream().forEach((readUser) -> {
        System.out.println(readUser.toString());
    });
    }
}
