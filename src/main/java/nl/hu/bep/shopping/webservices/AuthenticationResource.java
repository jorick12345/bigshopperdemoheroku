package nl.hu.bep.shopping.webservices;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import nl.hu.bep.shopping.model.MyUser;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap;
import java.util.Calendar;


@Path("authenticatie")
public class AuthenticationResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticateUser(LogonRequest logonRequest){
        try{
            String role = MyUser.validateLogin(logonRequest.username, logonRequest.password);
            if (role == null) throw new IllegalArgumentException("No user found");

            String token = createToken(logonRequest.username, role);

            return Response.ok(new AbstractMap.SimpleEntry<>("JWT", token)).build();

        }catch (JwtException | IllegalArgumentException e){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
    final static public Key key = MacProvider.generateKey();

    private String createToken(String name,String role) throws JwtException{
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE,30);

        return Jwts.builder()
                .setSubject(name)
                .setExpiration(expiration.getTime())
                .claim("role",role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }



}
