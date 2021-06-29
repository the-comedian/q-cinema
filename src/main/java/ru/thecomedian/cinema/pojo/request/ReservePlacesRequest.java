package ru.thecomedian.cinema.pojo.request;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.List;

/**
 * Класс для резерва места кинотеатра
 */

public class ReservePlacesRequest {

    @Schema(required = true, example = "[1, 2, 3]", description = "Список идентификаторов мест для бронирования")
    private List<Long> placeIdList;

    public ReservePlacesRequest() {
    }

    public List<Long> getPlaceIdList() {
        return placeIdList;
    }

    public void setPlaceIdList(List<Long> placeIdList) {
        this.placeIdList = placeIdList;
    }
}
