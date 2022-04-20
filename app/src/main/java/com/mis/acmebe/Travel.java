package com.mis.acmebe;

import java.util.Objects;

public class Travel {
    private String start;
    private String end;
    private String comments;
    private Integer rate;
    private Integer scale;
    private Integer price;
    private String origin;
    private String destiny;


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public void setDestiny(String destiny) {
        this.destiny = destiny;
    }

    public Travel() {
    }

    public Travel(String start, String end, String comments, Integer rate, Integer scale, Integer price, String origin, String destiny) {
        this.start = start;
        this.end = end;
        this.comments = comments;
        this.rate = rate;
        this.scale = scale;
        this.price = price;
        this.origin = origin;
        this.destiny = destiny;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Travel travel = (Travel) o;

        if (start != null ? !start.equals(travel.start) : travel.start != null) return false;
        if (end != null ? !end.equals(travel.end) : travel.end != null) return false;
        if (comments != null ? !comments.equals(travel.comments) : travel.comments != null)
            return false;
        if (rate != null ? !rate.equals(travel.rate) : travel.rate != null) return false;
        if (scale != null ? !scale.equals(travel.scale) : travel.scale != null) return false;
        if (price != null ? !price.equals(travel.price) : travel.price != null) return false;
        if (origin != null ? !origin.equals(travel.origin) : travel.origin != null) return false;
        return destiny != null ? destiny.equals(travel.destiny) : travel.destiny == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (comments != null ? comments.hashCode() : 0);
        result = 31 * result + (rate != null ? rate.hashCode() : 0);
        result = 31 * result + (scale != null ? scale.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (destiny != null ? destiny.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", comments='" + comments + '\'' +
                ", rate=" + rate +
                ", scale=" + scale +
                ", price=" + price +
                ", origin='" + origin + '\'' +
                ", destiny='" + destiny + '\'' +
                '}';
    }
}
