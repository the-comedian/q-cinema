package ru.thecomedian.cinema.resource;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import ru.thecomedian.cinema.pojo.request.ReservePlacesRequest;
import ru.thecomedian.cinema.service.CinemaService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cinema-resource")
@Tag(name = "Сервисы для бронирования мест в кинотеатре")
public class CinemaResource {

    @Inject
    CinemaService cinemaService;

    @GET
    @Path("loadCinemaList")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Получить список кинотеатров")
    public Response loadCinemaList() throws Exception {
        return Response.ok(cinemaService.loadCinemaList()).build();
    }

    @GET
    @Path("loadHallList/{cinemaId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Получить список залов в кинотеатре")
    public Response loadHallList(@PathParam("cinemaId") Long cinemaId) throws Exception {
        return Response.ok(cinemaService.loadHallList(cinemaId)).build();
    }

    @GET
    @Path("loadPlaceList/{hallId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Получить список мест в зале кинотеатра")
    public Response loadPlaceList(@PathParam("hallId") Long hallId) throws Exception {
        return Response.ok(cinemaService.loadPlaceList(hallId)).build();
    }

    @POST
    @Path("reservePlaces")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Забронировать список мест")
    public Response reservePlaces(ReservePlacesRequest reservePlacesRequest) throws Exception {
        return Response.ok(cinemaService.reservePlaces(reservePlacesRequest.getPlaceIdList())).build();
    }


}
