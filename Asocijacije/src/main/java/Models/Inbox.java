package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inbox {
   private HashMap <String, List<PrivateMessage>> messages = new HashMap<>();

   public void addNewMessage(String username,PrivateMessage privateMessage){
       if(messages.containsKey(username)){
           List<PrivateMessage> list_of_messages = messages.get(username);
           list_of_messages.add(privateMessage);
       }
       else {
           synchronized (messages){
               List<PrivateMessage> list_of_messages = new ArrayList<PrivateMessage>();
               list_of_messages.add(privateMessage);
               messages.put(username,list_of_messages);
           }
       }
   }

    public HashMap <String, List<PrivateMessage>> getAllMessages(){
        return messages;
    }

   public List<PrivateMessage> getAllMessagesWith(String username){
       if(messages.containsKey(username))
           return messages.get(username);
       else
           return null;
   }

   public void removeChat(String messageTo){
       synchronized (messages) {
           messages.remove(messageTo);
       }
   }

}
