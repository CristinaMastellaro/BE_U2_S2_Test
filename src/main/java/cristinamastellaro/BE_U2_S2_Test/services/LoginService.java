package cristinamastellaro.BE_U2_S2_Test.services;

import cristinamastellaro.BE_U2_S2_Test.entities.Employee;
import cristinamastellaro.BE_U2_S2_Test.exceptions.UnauthorizedException;
import cristinamastellaro.BE_U2_S2_Test.payloads.LoginDTO;
import cristinamastellaro.BE_U2_S2_Test.security.JWTTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {
    @Autowired
    private EmployeeService eSer;
    @Autowired
    private JWTTools jwtTools;

    public String checkInfoAndGenerateToken(LoginDTO payload) {
        Employee found = eSer.findEmployeeByEmail(payload.email());
        if (found.getPassword().equals(payload.password())) {
            return jwtTools.createToken(found.getId());
        } else {
            throw new UnauthorizedException();
        }
    }
}
