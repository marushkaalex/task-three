package com.epam.am.xml.model;

public class Paper {
    private String title;
    private Type type;
    private boolean isMonthly;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isMonthly() {
        return isMonthly;
    }

    public void setMonthly(boolean monthly) {
        isMonthly = monthly;
    }

    public enum Type {
        NEWSPAPER, MAGAZINE, BOOKLET
    }

    public static class Chars {
        private boolean isColored;
        private int pageCount;
        private String index;
        private boolean isGlossy;

        public boolean isColored() {
            return isColored;
        }

        public void setColored(boolean colored) {
            isColored = colored;
        }

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public boolean isGlossy() {
            return isGlossy;
        }

        public void setGlossy(boolean glossy) {
            isGlossy = glossy;
        }
    }
}
