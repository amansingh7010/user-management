package ca.unb.usermanagement.payload.response;

import java.util.List;

public class Response implements ResponseFactory {

    @Override
    public ListResponse createListResponse(List<?> list) {
        return new ListResponse(list);
    }

    @Override
    public MessageResponse createMessageResponse(String message) {
        return new MessageResponse(message);
    }

    @Override
    public UserInfoResponse createUserInfoResponse(Long id, String username, String email, List<String> roles) {
        return new UserInfoResponse(id, username, email, roles);
    }

}
