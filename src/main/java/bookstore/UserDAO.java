package bookstore;

import com.google.common.collect.Lists;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<User> userList = new ArrayList<User>();

    public List<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        userList.add(user);
        serializeToFile(userList);
    }

    private void serializeToFile(List<User> userList) {
        String usersDataPath = this.getClass()
                .getClassLoader().getResource("usersData").getFile();

        try (FileOutputStream fileOutputStream = new FileOutputStream(usersDataPath) ;
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){

            objectOutputStream.writeObject(userList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

