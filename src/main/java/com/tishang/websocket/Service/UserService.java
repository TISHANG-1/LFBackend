package com.tishang.websocket.Service;

import com.tishang.websocket.Enum.Status;
import com.tishang.websocket.dto.User;
import com.tishang.websocket.dto.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository repository ;
    public void  saveUser(User user){
        user.setStatus(Status.ONLINE) ;
        repository.save(user) ;
    }


    public  void disconnect(User user){
         var storedUser = repository.findById(user.getNickName()).orElse(null) ;
         if(storedUser != null){
              storedUser.setStatus(Status.OFFLINE);
              repository.save(storedUser);
         }
    }

    public List<User> findConnectedUser(){
      return repository.findAllByStatus(Status.ONLINE);
    }

}
