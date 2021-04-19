package edu.cooper.ece366.DBconnection;

import java.util.List;

public interface DBstoreService {
    Integer storeUser(String ID,String nickname);
    Integer storeLobby(String ID, String code);
    Integer storeRestaurant(String ID, String name, String alias,  String phone,
                            String displayPhone, List<String> cuisine, String price, double rating);
}
