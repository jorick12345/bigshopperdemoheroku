package nl.hu.bep.shopping.webservices;

import nl.hu.bep.shopping.model.*;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.naming.Name;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("list")
public class ListResource {

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public String getShoppingLists() {
//        List<ShoppingList> shoppingLists = Shop.getShop().getAllShoppingLists();
//        if (shoppingLists.isEmpty()) {
//            return Json.createObjectBuilder().add("error", "no lists present").build().toString();
//        }
//        //java stream example, see @getShoppingListByName for a step by step output example
//        JsonArrayBuilder jab = Json.createArrayBuilder();
//        shoppingLists.forEach(
//                sl -> jab.add(
//                        Json.createObjectBuilder()
//                                .add("id", sl.getName())
//                                .add("owner", sl.getOwner().getName())
//                ));
//        return jab.build().toString();
//    }
    @RolesAllowed("admin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getShoppingLists() {
        return  Response.ok(Shop.getShop().getAllShoppingLists()).build();

    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{name}")
    public Response getShoppingListByName(@PathParam("name") String name) {
        Shop shop = Shop.getShop();
        ShoppingList list = shop.getShoppingListByName(name);
        return Response.ok(list.getListItems()).build();

    }

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response voegShoppingListtoe(PostShoppingList request){
//
//
//    }

//    public Response addProduct(PostProduct request){
//        ShoppingList shoppingList =Shop.getShop().getShoppingListByName(request.getSh);
//    }
//    @POST
//    Produces(MediaType.APPLICATION_JSON)
//    public Response addShopper()



    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetBoodschappenLijst(BoodschappenlijstRequest boodschappenlijstRequest){

        ShoppingList shoppingList = Shop.getShop().getShoppingListByName(boodschappenlijstRequest.getName());
        shoppingList.clearItems();

        return Response.ok(shoppingList).build();


    }

}
