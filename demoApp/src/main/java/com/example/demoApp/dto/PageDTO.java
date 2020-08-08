package com.example.demoApp.dto;

import org.springframework.data.domain.Page;

import java.util.List;

// Class to handle pagination data for the front-end
public class PageDTO {

    private List content;
    private int totalPages;
    private Long totalElements;

    private int number;
    private int numberOfElements;
    private boolean hasNext;
    private boolean hasPrevious;

    public PageDTO() {
    }

    public PageDTO(Page pageObj) {
        this.content = pageObj.getContent();
        this.totalElements = pageObj.getTotalElements();
        this.totalPages = pageObj.getTotalPages();
        this.number = pageObj.getNumber();
        this.numberOfElements = pageObj.getNumberOfElements();
        this.hasNext = pageObj.hasNext();
        this.hasPrevious = pageObj.hasPrevious();

    }

    public List getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return hasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        this.hasPrevious = hasPrevious;
    }
}


