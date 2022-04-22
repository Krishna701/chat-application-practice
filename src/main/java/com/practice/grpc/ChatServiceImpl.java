package com.practice.grpc;

import com.practice.*;
import com.practice.model.User;
import com.practice.services.AdminServices;
import com.practice.services.UsersService;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;

public class ChatServiceImpl extends ChatApplicationPracticeServiceGrpc.ChatApplicationPracticeServiceImplBase {
    private final AdminServices adminServices;
    private final UsersService usersService;

    public ChatServiceImpl(AdminServices adminServices, UsersService usersService) {
        this.adminServices = adminServices;
        this.usersService = usersService;
    }

    @Override
    public void adminLogin(AdminLoginRequest request, StreamObserver<AdminLoginResponse> responseObserver) {
        String adminUserId = request.getAdminUserId();
        String adminUserName = request.getAdminUserName(); // frontend sends the credentials
        boolean adminLogin = adminServices.getLoginResponse(adminUserId,adminUserName);

        ArrayList<FetchUsers> resultlist = new ArrayList<>();
        AdminLoginResponse.Builder response = AdminLoginResponse.newBuilder();
        if(adminLogin){ //if admin login == true then only fetch
            ArrayList<User> allUserList = usersService.getAllUsers();


            allUserList.forEach(user -> {
                FetchUsers fetch = FetchUsers.newBuilder()
                        .setUserId(user.getUserId())
                        .setUserName(user.getUserName())
                        .build();
                resultlist.add(fetch);
            });
        }
        response.addAllAdminUserList(resultlist);
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }
    @Override
    public void join(JoinUserRequest request, StreamObserver<JoinUserResponse> responseObserver){
        String userName = request.getUserName();
        JoinUserResponse.Builder response = JoinUserResponse.newBuilder();
        boolean getLoginResponse = usersService.getJoinUserResponse(userName);
        if(getLoginResponse){
            String userId = usersService.addUser(userName);
            response.setUserId(userId).setUserName(userName);
        }

        responseObserver.onNext(response.build());
        responseObserver.onCompleted();
    }



}
