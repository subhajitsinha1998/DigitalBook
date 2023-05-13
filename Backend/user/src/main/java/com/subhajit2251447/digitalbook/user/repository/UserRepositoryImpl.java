// package com.subhajit2251447.digitalbook.user.repository;

// import java.util.ArrayList;

// import org.springframework.stereotype.Repository;

// import com.subhajit2251447.digitalbook.user.model.UserDetails;

// @Repository
// public class UserRepositoryImpl implements UserRepository {

//     public ArrayList<UserDetails> users = new ArrayList<UserDetails>();
//     private static int id = 1;

//     public int save(UserDetails user){
//         user.setId(UserRepositoryImpl.id);
//         users.add(user);
//         UserRepositoryImpl.id+=1;
//         return user.getId();
//     }

//     public UserDetails fetch(int id){
//         for (UserDetails user : users) {
//             if(user.getId()==id){
//                 return user;
//             }
//         }
//         return null;
//     }
    
// }
