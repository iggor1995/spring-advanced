package soap;

import com.springadv.soap.GetUserRequest;
import com.springadv.soap.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by Igor_Lapin on 5/18/2018.
 */
@Endpoint
public class UserEndpoint {
    private static final String NAMESPACE_URI = "http://springadv.com/soap";

    @Autowired
    UserUtility userUtility;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getUser(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userUtility.getUser(request.getUserId()));
        return response;
    }
}
