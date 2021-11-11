package rso.football.igralci.api.v1.dtos;

public class UploadIgralciResponse {

    private Integer numberOfFaces;
    private String message;

    public Integer getNumberOfFaces() {
        return numberOfFaces;
    }

    public void setNumberOfFaces(Integer numberOfFaces) {
        this.numberOfFaces = numberOfFaces;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}