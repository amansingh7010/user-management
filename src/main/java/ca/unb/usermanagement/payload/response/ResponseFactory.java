package ca.unb.usermanagement.payload.response;

import java.util.List;

public interface ResponseFactory {
    ListResponse createListResponse(List<?> list);

    MessageResponse createMessageResponse(String message);

    UserInfoResponse createUserInfoResponse(Long id, String username, String email, List<String> roles);
}
