package App;

import java.util.ArrayList;
import java.util.HashMap;

public class FriendsActivityLogic implements Runnable {
    private static HashMap<String,String> friends;
    private static volatile boolean flag =false;
    private static volatile FriendsActivityLogic friendsActivityLogic = null;
    private FriendsActivityLogic(){}
    @Override
    public void run() {

    }
     synchronized public void setRecentlyPlayed(String songName,String friendName){
         System.out.println("1");
         friends =(HashMap<String, String>) Songs.reafFromFile("src\\App\\songs\\friends.bin");
                if(friends == null) friends = new HashMap<>();
                if(!friends.containsKey(friendName))
                    friends.put(friendName,songName);
                else friends.replace(friendName,songName);
                if(friends.containsKey(MainFrame.name)) friends.remove(MainFrame.name);
                for(String n : friends.keySet()) System.out.println(n);
                Songs.writeToFile(friends,"src\\App\\songs\\friends.bin");
         System.out.println("2");
                FriendsActivityGUI.getInstance().setFriends(getFriends());
         System.out.println("rad shod");
                // flag = true;
                //System.out.println(flag);




    }
     public HashMap<String,String> getFriends(){
        return friends;
    }
     public static FriendsActivityLogic getLogicInstance(){
        if(friendsActivityLogic == null) friendsActivityLogic = new FriendsActivityLogic();
        return friendsActivityLogic;
    }
}
